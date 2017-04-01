package com.example.leon.googleplaydemo35.ui.fragment;

import android.view.View;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.adapter.RecommendAdapter;
import com.example.leon.googleplaydemo35.network.Api;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;
import com.example.leon.googleplaydemo35.widget.StellarMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class RecommendFragment extends BaseFragment {

    private static final String TAG = "RecommendFragment";
    private List<String> mDataList;

    @Override
    protected void startLoadData() {
        Api api = HeiMaRetrofit.getInstance().getApi();
        Call<List<String>> listCall = api.listRecommend();
        listCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
//                Toast.makeText(getContext(), "加载推荐页面数据成功", Toast.LENGTH_SHORT).show();
                mDataList = response.body();
                onDataLoadedSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
//                Toast.makeText(getContext(), "加载推荐界面失败", Toast.LENGTH_SHORT).show();
                onDataLoadedFailed();//显示失败视图
            }
        });
    }

    /**
     *  返回推荐界面的视图
     */
    @Override
    protected View onCreateContentView() {
        StellarMap stellarMap = new StellarMap(getContext());//星状图
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
//        stellarMap.setPadding(padding, padding, padding, padding);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        stellarMap.setAdapter(new RecommendAdapter(getContext(), mDataList));
        //设置分布网格
        stellarMap.setRegularity(15, 20);
        //设置初始化页面
        stellarMap.setGroup(0, false);
        return stellarMap;
    }
}
