package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.leon.googleplaydemo35.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 2017/3/30.
 */

public class AppDetailBottomBar extends RelativeLayout {
    @BindView(R.id.download_button)
    DownloadButton mDownloadButton;

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
}
