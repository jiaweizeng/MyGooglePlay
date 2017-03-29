package com.example.administrator.mygoogleplay.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.network.Api;
import com.example.administrator.mygoogleplay.network.MyRetrofit;
import com.example.administrator.mygoogleplay.widget.FlowLayout;

import java.util.List;
import java.util.Random;

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
            final TextView text = new TextView(getContext());
            text.setText(mBody.get(i));
            text.setTextColor(Color.WHITE);
            text.setGravity(Gravity.CENTER);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), text.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            int padding = getResources().getDimensionPixelSize(R.dimen.padding);
            text.setPadding(padding,padding,padding,padding);
            StateListDrawable stateDrawable=new StateListDrawable();
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(15);
            int argb= Color.argb(255,
                    new Random().nextInt(200)+30,
                    new Random().nextInt(200)+30,
                    new Random().nextInt(200)+30);
            drawable.setColor(argb);

            GradientDrawable pressDrawable =  new GradientDrawable();
            pressDrawable.setCornerRadius(15);
            pressDrawable.setColor(Color.GRAY);
            stateDrawable.addState(new int[]{android.R.attr.state_pressed},pressDrawable);
            stateDrawable.addState(new int[]{},drawable);
            text.setBackgroundDrawable(stateDrawable);
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
                //Toast.makeText(getContext(), "success to connect", Toast.LENGTH_SHORT).show();
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
