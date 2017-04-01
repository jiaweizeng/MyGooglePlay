package com.example.leon.googleplaydemo35.ui.fragment;

import android.util.Log;

import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class GameFragment extends BaseAppListFragment {
    private static final String TAG = "GameFragment";

    @Override
    protected void startLoadData() {
        Call<List<AppListItemBean>> listCall = HeiMaRetrofit.getInstance().getApi().listGame(0);
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                //将网络数据加入封装好了的数据集合
                getDataList().addAll(response.body());
                //通知数据加载成功
                onDataLoadedSuccess();// --> onCreateContentView[BaseListFragment实现添加ListView] -->onCreateAdapter[BaseAppListFragment实现创建AppListAdapter]
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {
                //如果stars是int类型，会回调失败
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    /**
     * 加载更多的游戏数据
     */
    @Override
    protected void onStartLoadMore() {
        Call<List<AppListItemBean>> listCall = HeiMaRetrofit.getInstance().getApi().listGame(getDataList().size());
        listCall.enqueue(new Callback<List<AppListItemBean>>() {
            @Override
            public void onResponse(Call<List<AppListItemBean>> call, Response<List<AppListItemBean>> response) {
                //将更多数据加入数据集合
                getDataList().addAll(response.body());
                //刷新加载更多数据的列表
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AppListItemBean>> call, Throwable t) {

            }
        });
    }
}
