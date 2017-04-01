package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.leon.googleplaydemo35.R;

public class LoadingMoreProgressView extends RelativeLayout {

    public LoadingMoreProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingMoreProgressView(Context context) {
        this(context, null);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_loading_more_progress, this);
    }
}
