package com.example.leon.googleplaydemo35.network;

import com.example.leon.googleplaydemo35.bean.AppDetailBean;
import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.bean.CategoryItemBean;
import com.example.leon.googleplaydemo35.bean.HomeBean;
import com.example.leon.googleplaydemo35.bean.SubjectItemBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Leon on 2017/3/26.
 */

public interface Api {

    /**
     * 泛型T你想要解析后数据结构
     */
    @GET("hot")
    Call<List<String>> listHot();

    @GET("recommend")
    Call<List<String>> listRecommend();

    @GET("category")
    Call<List<CategoryItemBean>> listCategory();

    @GET("subject")
    Call<List<SubjectItemBean>> listSubject(@Query("index") int index);

    @GET("game")
    Call<List<AppListItemBean>> listGame(@Query("index")int index);

    @GET("app")
    Call<List<AppListItemBean>> listApp(@Query("index")int index);

    @GET("home")
    Call<HomeBean> listHome(@Query("index")int index);

    @GET("detail")
    Call<AppDetailBean> getAppDetail(@Query("packageName") String packageName);
}
