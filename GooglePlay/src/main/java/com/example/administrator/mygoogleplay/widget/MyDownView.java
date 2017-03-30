package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygoogleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyDownView extends RelativeLayout {
    @BindView(R.id.iv_download)
    ImageView mIvDownload;
    @BindView(R.id.tv_download)
    TextView mTvDownload;

    public MyDownView(Context context) {
        this(context, null);
    }

    public MyDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mydown_item, this);
        ButterKnife.bind(this, inflate);
    }
}
