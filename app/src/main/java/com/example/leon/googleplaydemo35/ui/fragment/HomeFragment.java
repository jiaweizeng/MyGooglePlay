package com.example.leon.googleplaydemo35.ui.fragment;

import android.view.View;

import com.example.leon.googleplaydemo35.app.Constant;
import com.example.leon.googleplaydemo35.bean.HomeBean;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class HomeFragment extends BaseAppListFragment {

    private List<String> mPicture;

/*
    @Override
    protected void initListView() {
        super.initListView();
        //初始化listview，加入轮播图
        FunBanner funBanner = new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.URL_IMAGE)
                .setImageUrls(mPicture)
                .build();

        getListView().addHeaderView(funBanner);
    }
*/

    @Override
    protected View onCreateHeaderView() {
        return  new FunBanner.Builder(getContext())
                .setHeightWidthRatio(0.377f)
                .setEnableAutoLoop(true)
                .setImageUrlHost(Constant.URL_IMAGE)
                .setImageUrls(mPicture)
                .build();
    }

    @Override
    protected void startLoadData() {
        Call<HomeBean> homeBeanCall = HeiMaRetrofit.getInstance().getApi().listHome(0);
        homeBeanCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                //将应用列表数据加入数据集合中
                getDataList().addAll(response.body().getList());
                //保存轮播图数据
                mPicture = response.body().getPicture();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStartLoadMore() {
        Call<HomeBean> homeBeanCall = HeiMaRetrofit.getInstance().getApi().listHome(getDataList().size());
        homeBeanCall.enqueue(new Callback<HomeBean>() {
            @Override
            public void onResponse(Call<HomeBean> call, Response<HomeBean> response) {
                getDataList().addAll(response.body().getList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<HomeBean> call, Throwable t) {

            }
        });
    }
}
