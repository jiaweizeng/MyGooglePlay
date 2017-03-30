package com.example.administrator.mygoogleplay.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyHomeBean {

    /**
     * des : 产品介绍：google市场app测试。
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * iconUrl : app/com.itheima.www/icon.jpg
     * id : 1525489
     * name : 黑马程序员
     * packageName : com.itheima.www
     * size : 91767
     * stars : 5
     */

    private List<MyGameBean> list;
    private List<String> picture;

    public List<MyGameBean> getList() {
        return list;
    }

    public void setList(List<MyGameBean> list) {
        this.list = list;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

}
