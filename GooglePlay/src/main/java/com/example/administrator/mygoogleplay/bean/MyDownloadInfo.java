package com.example.administrator.mygoogleplay.bean;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class MyDownloadInfo {
    private int status;//下载状态
    private int progress;//下载进度
    private String packageName;//下载apk的包名
    private int size;//下载apk的大小
    private String filePath;//apk的文件路径

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String downloadUrl;//apk的下载链接
}
