package com.example.administrator.mygoogleplay.ui.fragment;

import android.widget.BaseAdapter;

import com.example.administrator.mygoogleplay.adapter.MyGameAdapter;
import com.example.administrator.mygoogleplay.bean.MyGameBean;
import com.example.administrator.mygoogleplay.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MyAppFragment extends MyBaseLoadMoreListFragment {

    private List<MyGameBean> mAppBeanList;

    @Override
    public void startLoadData() {
        Call<List<MyGameBean>> call = MyRetrofit.getInstance().getApi().listApp(0);
        call.enqueue(new Callback<List<MyGameBean>>() {
            @Override
            public void onResponse(Call<List<MyGameBean>> call, Response<List<MyGameBean>> response) {
                mAppBeanList = response.body();
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<List<MyGameBean>> call, Throwable throwable) {
dataLoadError();
            }
        });
    }

    @Override
    protected void onStartLoadMore() {
        Call<List<MyGameBean>> call = MyRetrofit.getInstance().getApi().listApp(mAppBeanList.size());
        call.enqueue(new Callback<List<MyGameBean>>() {
            @Override
            public void onResponse(Call<List<MyGameBean>> call, Response<List<MyGameBean>> response) {
                mAppBeanList.addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MyGameBean>> call, Throwable throwable) {
dataLoadError();
            }
        });
    }

    @Override
    protected BaseAdapter setAdapter() {
        return new MyGameAdapter(getContext(),mAppBeanList);
    }
}
