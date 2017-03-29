package com.example.administrator.mygoogleplay.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.administrator.mygoogleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public abstract class MyBaseFragment extends Fragment {

    private static final String TAG = "MyBaseFragment";
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.ll_retry)
    LinearLayout mLlRetry;
    private FrameLayout mFrameLayout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.myload_retry, null);
//        mProgressBar= (ProgressBar) inflate.findViewById(R.id.progressBar);
        mFrameLayout = (FrameLayout) inflate.findViewById(R.id.fl_base);
//        mLlRetry=(LinearLayout) inflate.findViewById(R.id.ll_retry);
        ButterKnife.bind(this, inflate);

//        init();
        return inflate;
    }

//    protected abstract void init();

    public abstract View onCreateContentView();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        startLoadData();
    }

    public void startLoadData(){

    }

    private void onRestart(View v) {
        mProgressBar.setVisibility(View.VISIBLE);
        mLlRetry.setVisibility(View.GONE);
        startLoadData();
    }

    public void dataLoadSuccess() {
//        Log.d(TAG, "dataLoadSuccess: mProgressBar" + mProgressBar);
//        Log.d(TAG, "dataLoadSuccess: mLlRetry" + mLlRetry);
        mProgressBar.setVisibility(View.GONE);
        mLlRetry.setVisibility(View.GONE);
        mFrameLayout.addView(onCreateContentView());
    }

    public void dataLoadError() {
        mProgressBar.setVisibility(View.VISIBLE);
        mLlRetry.setVisibility(View.GONE);
    }


}
