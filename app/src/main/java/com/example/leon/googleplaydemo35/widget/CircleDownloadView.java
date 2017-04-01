package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.leon.googleplaydemo35.R;

/**
 * Created by Leon on 2017/1/4.
 */

public class CircleDownloadView extends RelativeLayout{

    public CircleDownloadView(Context context) {
        this(context, null);
    }

    public CircleDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_download_progress, this);
    }
}
