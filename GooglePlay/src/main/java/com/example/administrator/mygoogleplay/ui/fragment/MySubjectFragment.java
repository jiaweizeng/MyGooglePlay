package com.example.administrator.mygoogleplay.ui.fragment;

import android.widget.BaseAdapter;

import com.example.administrator.mygoogleplay.adapter.MySubjectAdapter;
import com.example.administrator.mygoogleplay.bean.MySubjectItemBean;
import com.example.administrator.mygoogleplay.network.Api;
import com.example.administrator.mygoogleplay.network.MyRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MySubjectFragment extends MyBaseLoadMoreListFragment {

    private List<MySubjectItemBean> mBody;

    @Override
    protected void onStartLoadMore() {

        Call<List<MySubjectItemBean>> call = MyRetrofit.getInstance().getApi().listSubject(mBody.size());
        call.enqueue(new Callback<List<MySubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<MySubjectItemBean>> call, Response<List<MySubjectItemBean>> response) {
                mBody.addAll(response.body());
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<MySubjectItemBean>> call, Throwable throwable) {

                dataLoadError();
            }
        });
    }

    @Override
    public void startLoadData() {
        Api api = MyRetrofit.getInstance().getApi();
        Call<List<MySubjectItemBean>> call = api.listSubject(0);
        call.enqueue(new Callback<List<MySubjectItemBean>>() {
            @Override
            public void onResponse(Call<List<MySubjectItemBean>> call, Response<List<MySubjectItemBean>> response) {
                mBody = response.body();
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<List<MySubjectItemBean>> call, Throwable throwable) {

                dataLoadError();
            }
        });

    }

    @Override
    protected BaseAdapter setAdapter() {
        return new MySubjectAdapter(getContext(),mBody);
    }
}
