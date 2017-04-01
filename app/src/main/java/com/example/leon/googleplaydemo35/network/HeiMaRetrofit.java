package com.example.leon.googleplaydemo35.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Leon on 2017/3/26.
 */

public class HeiMaRetrofit {

    private static HeiMaRetrofit sHeiMaRetrofit;

    private static final String BASE_URL = "http://10.0.2.2:8080/GooglePlayServer/";



    private final Api mApi;

    private Gson mGson = new GsonBuilder().setLenient().create();//设置宽大处理畸形的json

    //单例模式
    public static HeiMaRetrofit getInstance() {
        if (sHeiMaRetrofit == null) {
            synchronized (HeiMaRetrofit.class) {
                if (sHeiMaRetrofit == null) {
                    sHeiMaRetrofit = new HeiMaRetrofit();
                }
            }
        }
        return sHeiMaRetrofit;
    }

    private HeiMaRetrofit() {
        //使用Retrofit来实现Api接口 需要配置gson转换器
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mApi = retrofit.create(Api.class);
    }

    /**
     * 暴露api，然外界调用发送网络请求
     */
    public Api getApi() {
        return mApi;
    }
}
