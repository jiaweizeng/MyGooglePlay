package com.example.administrator.mygoogleplay.ui.fragment;

import android.util.Log;
import android.view.View;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.adapter.recommendAdapter;
import com.example.administrator.mygoogleplay.network.Api;
import com.example.administrator.mygoogleplay.network.MyRetrofit;
import com.example.administrator.mygoogleplay.widget.StellarMap;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MyRecommendFragment extends MyBaseFragment {
    private static final String TAG = "MyRecommendFragment";
    private List<String> mBody;
    private StellarMap mMap;

    @Override
    public View onCreateContentView() {
        mMap = new StellarMap(getContext());
        mMap.setAdapter(new recommendAdapter(getContext(),mBody));
        int pading = getResources().getDimensionPixelSize(R.dimen.padding);
        mMap.setInnerPadding(pading,pading,pading,pading);
        mMap.setRegularity(15,20);
        mMap.setGroup(0,true);
        return mMap;
    }

    @Override
    public void startLoadData() {
        Api api = MyRetrofit.getInstance().getApi();
        Call<List<String>> call = api.listRecommend();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mBody = response.body();
                dataLoadSuccess();
                Log.d(TAG, "onResponse: =======");
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                dataLoadError();
                Log.d(TAG, "onFailure: ======");
            }
        });

    }
}
