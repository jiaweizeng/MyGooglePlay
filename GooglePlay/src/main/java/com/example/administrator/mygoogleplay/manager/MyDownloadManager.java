package com.example.administrator.mygoogleplay.manager;

import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDownloadManager {
    private static final String DOWNLOAD_DIRECTORY= Environment.getExternalStorageDirectory()
            +"/Android/data/com.example.administrator.mygoogleplay/apk";
    private MyDownloadManager(){}
    private static MyDownloadManager mDownloadManager;
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
}
