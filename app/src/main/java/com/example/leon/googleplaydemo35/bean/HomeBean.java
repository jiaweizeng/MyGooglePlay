package com.example.leon.googleplaydemo35.bean;

import java.util.List;

/**
 * Created by Leon on 2017/3/29.
 */

public class HomeBean {

    private List<AppListItemBean> list;
    private List<String> picture;

    public List<AppListItemBean> getList() {
        return list;
    }

    public void setList(List<AppListItemBean> list) {
        this.list = list;
    }

    public List<String> getPicture() {
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

}
