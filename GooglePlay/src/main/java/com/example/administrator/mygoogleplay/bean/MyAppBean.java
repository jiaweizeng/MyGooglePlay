package com.example.administrator.mygoogleplay.bean;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyAppBean {

    /**
     * des : 唱吧最时尚的手机KTV国内人气最高的手机K歌社区唱吧4.9，随时和朋友
     * downloadUrl : app/com.changba/com.changba.apk
     * iconUrl : app/com.changba/icon.jpg
     * id : 1527542
     * name : 唱吧
     * packageName : com.changba
     * size : 12309500
     * stars : 2.5
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private double stars;

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }
}
