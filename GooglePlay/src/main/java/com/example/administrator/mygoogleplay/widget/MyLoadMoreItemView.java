package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.administrator.mygoogleplay.R;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyLoadMoreItemView extends RelativeLayout {
    public MyLoadMoreItemView(Context context) {
        this(context,null);
    }

    public MyLoadMoreItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.myload_more_item,this);
    }
}
