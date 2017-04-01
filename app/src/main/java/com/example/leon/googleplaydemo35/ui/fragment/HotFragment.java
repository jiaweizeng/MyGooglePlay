package com.example.leon.googleplaydemo35.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.network.Api;
import com.example.leon.googleplaydemo35.network.HeiMaRetrofit;
import com.example.leon.googleplaydemo35.widget.FlowLayout;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Leon on 2017/3/26.
 */

public class HotFragment extends BaseFragment {

    private static final String TAG = "HotFragment";
    private List<String> mDataList;
    private GradientDrawable normalDrawable;

    @Override
    protected void startLoadData() {
        Api api = HeiMaRetrofit.getInstance().getApi();//获取api
        Call<List<String>> listCall = api.listHot();//获取网络请求call
/*        try {
            Response<List<String>> response = listCall.execute();//同步请求
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        listCall.enqueue(new Callback<List<String>>() {
            /**
             * 在主线程回调
             */
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Toast.makeText(getContext(),"网络成功", Toast.LENGTH_SHORT).show();
                mDataList = response.body();
                onDataLoadedSuccess();//加载数据成功，更新UI
            }

            /**
             * 在主线程回调
             */
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
                Toast.makeText(getContext(), "网络失败", Toast.LENGTH_SHORT).show();
                onDataLoadedFailed();
            }
        });
    }

    /**
     *
     * @return 热门界面的视图
     */
    @Override
    protected View onCreateContentView() {
/*        TextView textView = new TextView(getContext());
        textView.setText("热门界面，数据加载成功");
        return textView;*/
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        FlowLayout flowLayout = new FlowLayout(getContext());
        flowLayout.setPadding(padding, padding, padding, padding);
        //有多少条字符串，就创建多少个TextView
        for (int i = 0; i < mDataList.size(); i++) {
            final TextView textView = getTextView(padding, i);
            StateListDrawable stateListDrawable = getStateListDrawable();
            textView.setBackgroundDrawable(stateListDrawable);
            //将textview加入flowlayout
            flowLayout.addView(textView);
        }
        return flowLayout;
    }

    @NonNull
    private StateListDrawable getStateListDrawable() {
        //创建一个shape
        normalDrawable = new GradientDrawable();
        normalDrawable.setCornerRadius(8);//设置圆角
        //随机产生颜色
        int argb = getArgb();
        normalDrawable.setColor(argb);
        //创建selector
        StateListDrawable stateListDrawable = new StateListDrawable();
        //添加摁下去的状态的drawable
        GradientDrawable  pressedDrawable = new GradientDrawable();
        pressedDrawable.setCornerRadius(8);//设置圆角
        pressedDrawable.setColor(Color.DKGRAY);
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
        //添加正常状态drawable, 空数组表示其他状态都是normalDrawable
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

    private int getArgb() {
        int alpha = 255;
        int red = 30 + new Random().nextInt(200);//30到230
        int green = 30 + new Random().nextInt(200);
        int blue = 30 + new Random().nextInt(200);
        return Color.argb(alpha, red, green, blue);
    }

    @NonNull
    private TextView getTextView(int padding, int i) {
        final TextView textView = new TextView(getContext());
        textView.setText(mDataList.get(i));
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setClickable(true);
        textView.setPadding(padding, padding, padding, padding);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }

}
