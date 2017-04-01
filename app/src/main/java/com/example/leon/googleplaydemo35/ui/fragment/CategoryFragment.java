package com.example.leon.googleplaydemo35.ui.fragment;

import android.widget.BaseAdapter;

import com.example.leon.googleplaydemo35.adapter.CategoryAdapter;
import com.example.leon.googleplaydemo35.bean.CategoryItemBean;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class CategoryFragment extends BaseListFragment {
    private static final String TAG = "CategoryFragment";
    private List<CategoryItemBean> mDataList;

    @Override
    protected void startLoadData() {
        Call<List<CategoryItemBean>> listCall = HeiMaRetrofit.getInstance().getApi().listCategory();
        listCall.enqueue(new Callback<List<CategoryItemBean>>() {
            @Override
            public void onResponse(Call<List<CategoryItemBean>> call, Response<List<CategoryItemBean>> response) {
//                Log.d(TAG, "onResponse: " + response.body().get(0).getTitle());
                mDataList = response.body();
                onDataLoadedSuccess();
                //onCreateContentView -> onCreateAdapter
            }

            @Override
            public void onFailure(Call<List<CategoryItemBean>> call, Throwable t) {
                onDataLoadedFailed();
            }
        });
    }

    /**
     * 创建分类界面adpater
     */
    @Override
    public BaseAdapter onCreateAdapter() {
        return new CategoryAdapter(getContext(), mDataList);
    }
}
