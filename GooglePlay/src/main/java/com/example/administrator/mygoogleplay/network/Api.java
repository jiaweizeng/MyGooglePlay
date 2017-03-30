package com.example.administrator.mygoogleplay.network;

import com.example.administrator.mygoogleplay.bean.MyCategoryItemBean;
import com.example.administrator.mygoogleplay.bean.MyGameBean;
import com.example.administrator.mygoogleplay.bean.MyHomeBean;
import com.example.administrator.mygoogleplay.bean.MySubjectItemBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public interface Api {
    @GET("hot")
    Call<List<String>> listHot();

    @GET("recommend")
    Call<List<String>> listRecommend();

    @GET("category")
    Call<List<MyCategoryItemBean>> listCategoryBean();

    @GET("subject")
    Call<List<MySubjectItemBean>> listSubject(@Query("index")int index);

    @GET("game")
    Call<List<MyGameBean>> listGame(@Query("index")int index);

    @GET("app")
    Call<List<MyGameBean>> listApp(@Query("index")int index);

    @GET("home")
    Call<MyHomeBean> listHome(@Query("index")int index);
}
