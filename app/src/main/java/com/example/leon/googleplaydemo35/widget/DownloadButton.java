package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.bean.AppDetailBean;
import com.example.leon.googleplaydemo35.bean.DownloadInfo;
import com.example.leon.googleplaydemo35.manager.DownloadManager;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Leon on 2017/3/30.
 */

public class DownloadButton extends Button implements Observer{

    private Paint mPaint;
    private ColorDrawable mColorDrawable;

    private int mProgress;//进度
    private int mMax;//最大值

    private boolean enableProgress = true;

    public DownloadButton(Context context) {
        this(context, null);
    }

    public DownloadButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        mColorDrawable = new ColorDrawable(Color.BLUE);
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画一个矩形表示进度
//        canvas.drawRect(0, 0, 200, getHeight(), mPaint);
        if (enableProgress) {//如果是允许画背景 才去绘制
            float percent = mProgress * 1.0f / mMax; // 0.1
            float right = getWidth() * percent;
            mColorDrawable.setBounds(0, 0, (int) right, getHeight());
            mColorDrawable.draw(canvas);
        }

        super.onDraw(canvas);
    }

    /**
     * 设置进度
     */
    public void setProgress(int progress) {
        //计算进度的百分比
        mProgress = progress;
        int percent = (int) (mProgress * 1.0f / mMax * 100);
        String percentString = String.format(getResources().getString(R.string.download_progress), percent);
        setText(percentString);
        invalidate();
    }

    public void setMax(int max) {
        mMax = max;
    }


    /**
     * 同步DownloadButton下载状态
     * @param appDetailBean
     */
    public void syncState(AppDetailBean appDetailBean) {
        //初始化DownloadInfo 下载信息
        DownloadInfo downloadInfo = DownloadManager.getInstance().initDownloadInfo(getContext(),
                appDetailBean.getPackageName(),
                appDetailBean.getSize(),
                appDetailBean.getDownloadUrl());

        //观察DownloadManager里面的状态变化
        DownloadManager.getInstance().addObserver(appDetailBean.getPackageName(), this);

        //根据初始化的结果，刷新界面状态
        updateStatus(downloadInfo);
    }

    private void updateStatus(DownloadInfo downloadInfo) {
        switch (downloadInfo.getStatus()) {
            case DownloadManager.STATE_INSTALLED:
                setText(getResources().getString(R.string.open));
                break;
            case DownloadManager.STATE_DOWNLOADED:
                clearProgressBackground();
                setText(getResources().getString(R.string.install));//如果是下载完成，则显示安装
                break;
            case DownloadManager.STATE_UN_DOWNLOAD:
                setText(getResources().getString(R.string.download));
                break;
            case DownloadManager.STATE_WAITING:
                setText(getResources().getString(R.string.waiting));
                break;
            case DownloadManager.STATE_DOWNLOADING:
                //更新进度
                enableProgress = true;//进度开关打开
                setMax(downloadInfo.getSize());
                setProgress(downloadInfo.getProgress());
                break;
            case DownloadManager.STATE_PAUSE:
                setText(getResources().getString(R.string.continue_download));//如果是暂停状态，则显示继续
                break;
            case DownloadManager.STATE_FAILED:
                //如果是下载失败的状态，显示重试
                clearProgressBackground();//清除进度的背景
                setText(getResources().getString(R.string.retry));
                break;
        }
    }

    /**
     * 下载完成后去掉蓝色背景
     */
    private void clearProgressBackground() {
        //关闭绘制背景的开关
        enableProgress = false;
        invalidate();
    }


    @Override
    public void update(Observable o, Object arg) {
        final DownloadInfo downloadInfo = (DownloadInfo) arg;
        //在主线程更新状态
        post(new Runnable() {
            @Override
            public void run() {
                updateStatus(downloadInfo);
            }
        });
    }
}
