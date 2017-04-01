package com.example.administrator.mygoogleplay.manager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MyDownloadInfo;

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
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDownloadManager {
    private static final String TAG = "MyDownloadManager";
    public static final int STATE_UN_DOWNLOAD = 0;//未下载
    public static final int STATE_DOWNLOADING = 1;//下载中
    public static final int STATE_PAUSE = 2;//暂停下载
    public static final int STATE_WAITING = 3;//等待下载
    public static final int STATE_FAILED = 4;//下载失败
    public static final int STATE_DOWNLOADED = 5;//下载完成
    public static final int STATE_INSTALLED = 6;//已安装
    private static final String DOWNLOAD_DIRECTORY= Environment.getExternalStorageDirectory()
            +"/Android/data/com.example.administrator.mygoogleplay/apk";

    private OkHttpClient mOkHttpClient;


    private MyDownloadManager(){
        mOkHttpClient=new OkHttpClient();
    }
    private static MyDownloadManager mDownloadManager;

    private HashMap<String,MyDownloadInfo> mHashMap=new HashMap<>();
    public static MyDownloadManager getInstance(){
        if(mDownloadManager==null){
            synchronized (MyDownloadManager.class){
                if(mDownloadManager==null){
                    mDownloadManager=new MyDownloadManager();
                }
            }
        }
        return mDownloadManager;
    }
    public void createDownloadDir(){

        File file = new File(DOWNLOAD_DIRECTORY);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public MyDownloadInfo initDownloadInfo(Context context, String packageName, String url, int size) {
        if(mHashMap.get(packageName)!=null){
            return mHashMap.get(packageName);
        }
        MyDownloadInfo downloadInfo = new MyDownloadInfo();
        downloadInfo.setPackageName(packageName);
        downloadInfo.setDownloadUrl(url);
        downloadInfo.setSize(size);

        if(isInstalled(context,packageName)){
            downloadInfo.setStatus(STATE_INSTALLED);
        }else if(isDownloaded(downloadInfo)){
            downloadInfo.setStatus(STATE_DOWNLOADED);
        }else {
            downloadInfo.setStatus(STATE_UN_DOWNLOAD);
        }


        mHashMap.put(packageName,downloadInfo);
        return downloadInfo;

    }

    private boolean isDownloaded(MyDownloadInfo info) {
        String path = DOWNLOAD_DIRECTORY+"/"+info.getPackageName()+".apk";
        File file = new File(path);
        info.setFilePath(path);
        if(file.exists()){
            if(file.length()==info.getSize()){
                return true;
            }else {
                info.setProgress((int) file.length());
                return false;
            }
        }
        return false;
    }

    private boolean isInstalled(Context context, String name) {
        try {
            context.getPackageManager().getPackageInfo(name, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void dealMiddleButtonAction(Context context, String name) {
        MyDownloadInfo downloadInfo = mHashMap.get(name);
        switch (downloadInfo.getStatus()){
            case STATE_DOWNLOADED:

                install(context,downloadInfo);
                break;
            case STATE_INSTALLED:
                openApp(context,downloadInfo);
                break;
            case STATE_UN_DOWNLOAD:
                downloadApp(downloadInfo);
                break;
        }
    }

    private void downloadApp(MyDownloadInfo info) {
        new Thread(new downloadTask(info)).start();
    }

    private class downloadTask implements Runnable{
        private MyDownloadInfo mInfo;

        public downloadTask(MyDownloadInfo info) {
            mInfo = info;
        }

        @Override
        public void run() {
            String downloadurl = MyConstant.URL_DOWNLOAD+mInfo.getDownloadUrl()+"&rang="+mInfo.getProgress();
            File file = new File(mInfo.getFilePath());
            InputStream mInputStream=null;
            FileOutputStream mOutputStream=null;
            if(!file.exists()){

                try {
                    file.createNewFile();
                    Request request = new Request.Builder().get().url(downloadurl).build();
                    Response response =mOkHttpClient.newCall(request).execute();
                    if(response.isSuccessful()){
                        mInputStream = response.body().byteStream();
                        mOutputStream = new FileOutputStream(file, true);
                        byte[] bb= new byte[1024*8];
                        int len =-1;
                        while ((len=mInputStream.read(bb))!=-1){

                            mOutputStream.write(bb,0,len);
                            int progress=mInfo.getProgress()+len;
                            mInfo.setProgress(progress);
                            Log.d(TAG, "run: progress=="+progress);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    myCloseable(mInputStream);
                    myCloseable(mOutputStream);

                }
            }
        }
    }

    private void myCloseable(Closeable closeable) {
        if(closeable!=null){
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void openApp(Context context, MyDownloadInfo info) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(info.getPackageName());
        context.startActivity(intent);
    }

    private void install(Context context, MyDownloadInfo info) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(info.getFilePath());
        if(file.exists()){
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }
}
