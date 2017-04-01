package com.example.administrator.mygoogleplay.ui.fragment;

import android.view.View;

import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MyHomeBean;
import com.example.administrator.mygoogleplay.network.MyRetrofit;
import com.leon.loopviewpagerlib.FunBanner;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MyHomeFragment extends MyBaseAppFragment {

//    private MyHomeBean mBody;
    private List<String> mPicture;

    @Override
    public void startLoadData() {
        Call<MyHomeBean> call = MyRetrofit.getInstance().getApi().listHome(0);
        call.enqueue(new Callback<MyHomeBean>() {
            @Override
            public void onResponse(Call<MyHomeBean> call, Response<MyHomeBean> response) {
                getDataList().addAll(response.body().getList());
//                mBody = response.body();
//                mPicture = mBody.getPicture();
                mPicture = response.body().getPicture();
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<MyHomeBean> call, Throwable throwable) {
dataLoadError();
            }
        });
    }

    @Override
    protected View onCreateHeaderView() {
        FunBanner banner = new FunBanner(getContext());
        banner.setRatio(0.377f);
        banner.setEnableAutoLoop(true);
        banner.setImageUrls(mPicture);
        banner.setImageUrlHost(MyConstant.URL_IMAGE);
        return banner;

    }

    @Override
    protected void onStartLoadMore() {
//        Call<MyHomeBean> call = MyRetrofit.getInstance().getApi().listHome(mBody.getList().size());
        Call<MyHomeBean> call = MyRetrofit.getInstance().getApi().listHome(getDataList().size());
        call.enqueue(new Callback<MyHomeBean>() {
            @Override
            public void onResponse(Call<MyHomeBean> call, Response<MyHomeBean> response) {
//                mBody.getList().addAll(response.body().getList());
                getDataList().addAll(response.body().getList());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MyHomeBean> call, Throwable throwable) {
dataLoadError();
            }
        });

    }

//    @Override
//    protected BaseAdapter setAdapter() {
//        return new MyGameAdapter(getContext(),mBody.getList());
//    }
}
