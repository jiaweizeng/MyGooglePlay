package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;
import com.example.administrator.mygoogleplay.bean.MyDownloadInfo;
import com.example.administrator.mygoogleplay.manager.MyDownloadManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDownloadButton extends Button implements Observer{
private int mMax;
    private int mProgress;
    private ColorDrawable mDrawable;
    private Paint mPaint;
private boolean setDraw=true;
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

        if(setDraw){
            float v = mProgress * 1f / mMax;
            mDrawable.setBounds(0,0, (int) (getWidth()*v),getHeight());
            mDrawable.draw(canvas);
        }

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

    public void sync(MyDetailBean body) {
        MyDownloadInfo downloadInfo = MyDownloadManager.getInstance().initDownloadInfo(getContext(),
                body.getPackageName(),
                body.getDownloadUrl(),
                body.getSize());
        MyDownloadManager.getInstance().addObserver(body.getPackageName(),this);
        updateText(downloadInfo);
    }

    private void updateText(MyDownloadInfo info) {
        switch (info.getStatus()){
            case MyDownloadManager.STATE_UN_DOWNLOAD:
                setText(getResources().getString(R.string.download));
                break;
            case MyDownloadManager.STATE_INSTALLED:
                setText(getResources().getString(R.string.open));
                break;
            case MyDownloadManager.STATE_DOWNLOADED:
                cleanBackground();
                setText(getResources().getString(R.string.install));
                break;
            case MyDownloadManager.STATE_WAITING:
                setText(getResources().getString(R.string.waiting));
                break;
            case MyDownloadManager.STATE_DOWNLOADING:
                setDraw=true;
                setMax(info.getSize());
                setProgress(info.getProgress());
                break;
            case MyDownloadManager.STATE_PAUSE:
                setText(getResources().getString(R.string.continue_download));
                cleanBackground();
                break;
        }
    }

    private void cleanBackground() {
        setDraw=false;
        invalidate();
    }

    @Override
    public void update(Observable o, Object arg) {

        final MyDownloadInfo downloadInfo= (MyDownloadInfo) arg;
        post(new Runnable() {
            @Override
            public void run() {
                updateText(downloadInfo);
            }
        });

    }
}
