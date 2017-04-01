package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.administrator.mygoogleplay.R;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDownloadButton extends Button{
private int mMax;
    private int mProgress;
    private ColorDrawable mDrawable;
    private Paint mPaint;

    public MyDownloadButton(Context context) {
        this(context,null);
    }

    public MyDownloadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDrawable = new ColorDrawable(Color.BLUE);
//        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float v = mProgress * 1f / mMax;
        mDrawable.setBounds(0,0, (int) (getWidth()*v),getHeight());
        mDrawable.draw(canvas);
        super.onDraw(canvas);
    }

    public void setProgress(int progress){
        mProgress=progress;
        int percent = (int) (progress*1f/mMax*100);
        setText(String.format(getResources().getString(R.string.download_progress),percent));
    }

    public void setMax(int max){
        mMax=max;
    }
}
