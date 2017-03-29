package com.example.administrator.mygoogleplay.ui.fragment;

import android.util.Log;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.administrator.mygoogleplay.adapter.MyCategoryAdapter;
import com.example.administrator.mygoogleplay.bean.MyCategoryItemBean;
import com.example.administrator.mygoogleplay.network.Api;
import com.example.administrator.mygoogleplay.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MyCategoryFragment extends MyBaseListFragment {
    private static final String TAG = "MyCategoryFragment";
    private List<MyCategoryItemBean> mCategoryItemBeen;

    @Override
    public void startLoadData() {
        Api api = MyRetrofit.getInstance().getApi();
        Call<List<MyCategoryItemBean>> call = api.listCategoryBean();
        call.enqueue(new Callback<List<MyCategoryItemBean>>() {
            @Override
            public void onResponse(Call<List<MyCategoryItemBean>> call, Response<List<MyCategoryItemBean>> response) {

                mCategoryItemBeen = response.body();
                Toast.makeText(getContext(), "category success to connect", Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "onResponse: =="+mCategoryItemBeen.toString());
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<List<MyCategoryItemBean>> call, Throwable t) {
                dataLoadError();
            }
        });
    }


    @Override
    protected ListAdapter setAdapter() {
        Log.d(TAG, "setAdapter: 222222222222");
        return new MyCategoryAdapter(getContext(),mCategoryItemBeen);
    }
}
