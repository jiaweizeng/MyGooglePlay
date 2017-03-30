package com.example.administrator.mygoogleplay.bean;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyGameBean {

    /**
     * des : 捕鱼达人土豪金是捕鱼达人原班团队（fishingjoy）继捕鱼达人2研发的一款精
     * downloadUrl : app/org.cocos2dx.GoldenFishGame/org.cocos2dx.GoldenFishGame.apk
     * iconUrl : app/org.cocos2dx.GoldenFishGame/icon.jpg
     * id : 1642739
     * name : 捕鱼达人土豪金
     * packageName : org.cocos2dx.GoldenFishGame
     * size : 9815944
     * stars : 2.5
     */

    private String des;
    private String downloadUrl;
    private String iconUrl;
    private int id;
    private String name;
    private String packageName;
    private int size;
    private float stars;

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

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }
}
