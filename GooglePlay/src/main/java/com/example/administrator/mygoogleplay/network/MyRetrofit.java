package com.example.administrator.mygoogleplay.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class MyRetrofit {

    private static final String BASE_URL = "http://10.0.2.2:8080/GooglePlayServer/";

    private static MyRetrofit mRetrofit;

    private Gson mGson = new GsonBuilder().setLenient().create();

    public Api getApi() {
        return mApi;
    }

    private final Api mApi;

    public static MyRetrofit getInstance(){
        if (mRetrofit==null){
            synchronized (MyRetrofit.class){
                if(mRetrofit==null){
                    mRetrofit=new MyRetrofit();
                }
            }
        }
        return mRetrofit;
    }

    private MyRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mApi = retrofit.create(Api.class);
    }
}
