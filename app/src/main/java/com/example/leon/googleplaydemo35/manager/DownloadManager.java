package com.example.leon.googleplaydemo35.manager;

/**
 * Created by Leon on 2017/3/30.
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.leon.googleplaydemo35.app.Constant;
import com.example.leon.googleplaydemo35.bean.DownloadInfo;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * DownloadManger承担下载apk的所有功能 单例模式
 *  1. DownloadInfo初始化
 */
public class DownloadManager {

    private static final String TAG = "DownloadManager";

    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装

    //下载apk的存放路径，当应用被卸载时，该路径下的文件也会被删除
    private static final String DOWNLOAD_DIRECTORY = Environment.getExternalStorageDirectory()
            + "/Android/data/com.example.leon.googleplaydemo35/apk/";

    private static DownloadManager sDownloadManager;

    private OkHttpClient mOkHttpClient;

    //保存对应包名的app的下载信息
    private HashMap<String, DownloadInfo> mStringDownloadInfoHashMap = new HashMap<String, DownloadInfo>();

    private DownloadManager(){
        mOkHttpClient = new OkHttpClient();//实现下载apk的功能
    }

    public static DownloadManager getInstance() {
        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager();
                }
            }
        }
        return sDownloadManager;
    }

    /**
     * 创建apk下载下来存放的文件夹
     */
    public void createDownloadDir() {
        File file = new File(DOWNLOAD_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
    }


    /**
     * 初始化对应包名packageName的app的下载信息DownloadInfo(下载状态，包名)
     * @param context
     * @param packageName
     * @return
     */
    public DownloadInfo initDownloadInfo(Context context, String packageName, int size, String downloadUrl) {
        //先检查缓存，如果有缓存，则直接返回
        if (mStringDownloadInfoHashMap.get(packageName) != null) {
            return mStringDownloadInfoHashMap.get(packageName);
        }

        DownloadInfo downloadInfo = new DownloadInfo();
        downloadInfo.setPackageName(packageName);//设置包名，标识数据是属于哪个apk的
        downloadInfo.setSize(size);//设置大小
        downloadInfo.setDownloadUrl(downloadUrl);//设置下载url

        //首先检查安装状态
        if (isInstalled(context, packageName)) {
            //更新status
            downloadInfo.setStatus(STATE_INSTALLED);
        } else if (isDownloaded(downloadInfo)) {//检查是否下载完成
            //更新status 已经下载完成
            downloadInfo.setStatus(STATE_DOWNLOADED);
        }
        else {
            downloadInfo.setStatus(STATE_UN_DOWNLOAD);//一般情况未下载
        }

        //保存到内存缓存
        mStringDownloadInfoHashMap.put(packageName, downloadInfo);

        return downloadInfo;
    }

    /**
     * 判断一个apk是否下载完成
     */
    private boolean isDownloaded(DownloadInfo downloadInfo) {
        //找到apk下载存放的路径，判断是否存在apk的文件，并且大小是否是apk完整的大小
        String path = DOWNLOAD_DIRECTORY + downloadInfo.getPackageName() + ".apk";//apk的完整路径
        downloadInfo.setFilePath(path);
        File file = new File(path);
        if (file.exists()) {
            //判断大小是否完整
            if (file.length() == downloadInfo.getSize()) {
                return true;//下载完成
            } else {
                //保存已经下载的进度
                downloadInfo.setProgress((int) file.length());//用作断点续传
                return false;//下载不完整
            }
        }
        //文件不存在，没有下载
        return false;
    }

    public boolean isInstalled(Context context, String packageName) {
        try {
            //获取对应包名的app的信息，如果抛出NameNotFoundException，说明apk没有安装
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;//没有抛出异常表示已经安装
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;//表示没有安装
        }
    }

    public void openApp(Context context, String packageName) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(launchIntentForPackage);
    }

    /**
     * 根据当前packageName对应的apk的状态来做不同的处理
     * @param context
     * @param packageName
     */
    public void handleDownloadAction(Context context, String packageName) {
        //获取到packageName对应apk的状态，不用再调用一次initDownloadInfo去初始化状态
        //从缓存中获取下载信息
        DownloadInfo downloadInfo = mStringDownloadInfoHashMap.get(packageName);
        switch (downloadInfo.getStatus()) {
            case STATE_INSTALLED:
                openApp(context, packageName);//如果是状态已经安装，用户点击之后就是打开应用
                break;
            case STATE_DOWNLOADED:
                installApk(context, downloadInfo);//已经下载完成，安装应用
                break;
            case STATE_UN_DOWNLOAD:
                downloadApk(downloadInfo);
                break;
        }

    }

    private void downloadApk(DownloadInfo downloadInfo) {
        //创建一个子线程下载
        new Thread(new DownloadTask(downloadInfo)).start();
    }

    private class DownloadTask implements Runnable {

        private DownloadInfo mDownloadInfo;

        public DownloadTask(DownloadInfo downloadInfo) {
            mDownloadInfo = downloadInfo;
        }

        //下载一个apk
        @Override
        public void run() {
            //拼接下载url  range断点续传的起始位置
            String url = Constant.URL_DOWNLOAD + mDownloadInfo.getDownloadUrl() + "&range=" + mDownloadInfo.getProgress();
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                //创建apk的文件
                File file = new File(mDownloadInfo.getFilePath());
                if (!file.exists()) {
                    file.createNewFile();
                }
                Request request = new Request.Builder().get().url(url).build();
                Response response = mOkHttpClient.newCall(request).execute();//执行请求，下载apk
                if (response.isSuccessful()) {
                    //将网络输入数据流写到本地
                    inputStream = response.body().byteStream();
                    //输入流写入文件
                    fileOutputStream = new FileOutputStream(file, true);//从文件后面追加数据
                    //读取字节数组，写入文件
                    byte[] buffer = new byte[1024];//1KB
                    int len = - 1;//读取字节流返回长度
                    while ((len = inputStream.read(buffer)) != - 1) {
                        //将读出buffer写入文件
                        fileOutputStream.write(buffer, 0, len);
                        //更新进度
                        int progress = mDownloadInfo.getProgress() + len;
                        mDownloadInfo.setProgress(progress);
                        Log.d(TAG, "run: " + mDownloadInfo.getProgress());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭流
                closeStream(inputStream);
                closeStream(fileOutputStream);

/*                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void installApk(Context context, DownloadInfo downloadInfo) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //apk数据
        File file = new File(downloadInfo.getFilePath());
        if (file.exists()) {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
