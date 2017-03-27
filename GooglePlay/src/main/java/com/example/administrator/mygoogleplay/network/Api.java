package com.example.administrator.mygoogleplay.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public interface Api {
    @GET("hot")
    Call<List<String>> listHot();
}
