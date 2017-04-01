package com.example.leon.googleplaydemo35.ui.fragment;

import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class AppFragment extends BaseAppListFragment {

    @Override
    protected void startLoadData() {
        Call<List<AppListItemBean>> listCall = HeiMaRetrofit.getInstance().getApi().listApp(0);
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                getDataList().addAll(response.body());
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onStartLoadMore() {
        Call<List<AppListItemBean>> listCall = HeiMaRetrofit.getInstance().getApi().listApp(getDataList().size());
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                getDataList().addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }
}
