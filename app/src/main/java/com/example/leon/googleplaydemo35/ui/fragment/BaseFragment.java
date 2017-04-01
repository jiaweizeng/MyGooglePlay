package com.example.leon.googleplaydemo35.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.leon.googleplaydemo35.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/3/26.
 */

/**
 * 抽取基本布局 进度条 网络成功和失败ui业务逻辑
 */
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.loading_error)
    LinearLayout mLoadingError;
    @BindView(R.id.fragment_content)
    FrameLayout mFragmentContent;
    @BindView(R.id.retry)
    Button mRetry;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_base, null);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        startLoadData();//当fragment视图创建完成之后就开始加载数据
    }

    /**
     * 由于每个Fragment都要加载各自的数据，方法覆写该方法来实现各自数据加载
     */
    protected void startLoadData() {
    }

    /**
     * 获取数据成功逻辑每个Fragment都差不多，所以抽取在基类
     */
    protected void onDataLoadedSuccess() {
        //隐藏进度条
        mProgressBar.setVisibility(View.GONE);
        //将各个Framgent布局加入到FrameLayout
        mFragmentContent.addView(onCreateContentView());
    }

    /**
     * 子类必须实现该方法来创建自己的视图
     */
    protected abstract View onCreateContentView();

    /**
     * 获取数据失败逻辑每个Fragment都差不多，所以抽取在基类
     */
    protected void onDataLoadedFailed() {
        //进度条消失
        mProgressBar.setVisibility(View.GONE);
        //将失败视图显示
        mLoadingError.setVisibility(View.VISIBLE);
    }



    @OnClick(R.id.retry)
    public void onViewClicked() {
        //将失败的视图隐藏
        mLoadingError.setVisibility(View.GONE);
        //显示进度条
        mProgressBar.setVisibility(View.VISIBLE);
        //重新加载数据
        startLoadData();
    }
}
