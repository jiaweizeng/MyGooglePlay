package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.bean.AppDetailBean;
import com.example.leon.googleplaydemo35.manager.DownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/3/30.
 */

public class AppDetailBottomBar extends RelativeLayout {
    @BindView(R.id.download_button)
    DownloadButton mDownloadButton;

    private AppDetailBean mAppDetailBean;

    public AppDetailBottomBar(Context context) {
        this(context, null);
    }

    public AppDetailBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_bottom_bar, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailBean = appDetailBean;
        mDownloadButton.syncState(appDetailBean);//根据数据同步apk下载状态
    }

    @OnClick(R.id.download_button)
    public void onViewClicked() {
        //让DownloadManager处理用户点击事件，里面有状态判断，根据不同的状态做不同操作
        DownloadManager.getInstance().handleDownloadAction(getContext(), mAppDetailBean.getPackageName());
    }
}
