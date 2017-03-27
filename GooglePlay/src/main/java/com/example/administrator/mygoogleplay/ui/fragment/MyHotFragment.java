package com.example.administrator.mygoogleplay.ui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mygoogleplay.network.Api;
import com.example.administrator.mygoogleplay.network.MyRetrofit;
import com.example.administrator.mygoogleplay.widget.FlowLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/26 0026.
 */
public class MyHotFragment extends MyBaseFragment {
    private static final String TAG = "MyHotFragment";
    private List<String> mBody;

    @Override
    public View onCreateContentView() {
        ScrollView scrollView = new ScrollView(getContext());
        FlowLayout flowLayout = new FlowLayout(getContext());
        for (int i = 0; i <mBody.size() ; i++) {
            TextView text = new TextView(getContext());
            text.setText(mBody.get(i));
            flowLayout.addView(text);
        }
        scrollView.addView(flowLayout);
        return scrollView;
    }

    @Override
    public void startLoadData() {

        Api api = MyRetrofit.getInstance().getApi();
        Call<List<String>> call = api.listHot();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(getContext(), "success to connect", Toast.LENGTH_SHORT).show();
                mBody = response.body();
                Log.d(TAG, "onResponse: "+ mBody.toString());
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                dataLoadError();
                Toast.makeText(getContext(), "fail to connect", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
