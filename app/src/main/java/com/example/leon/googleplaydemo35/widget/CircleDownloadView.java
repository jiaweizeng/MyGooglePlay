package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.bean.DownloadInfo;
import com.example.leon.googleplaydemo35.manager.DownloadManager;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/1/4.
 */

public class CircleDownloadView extends RelativeLayout implements Observer{

    private static final String TAG = "CircleDownloadView";

    @BindView(R.id.download)
    ImageView mDownload;
    @BindView(R.id.download_info)
    TextView mDownloadInfoText;
    private AppListItemBean mAppListItemBean;
    private RectF mRectF;
    private int mPercent;
    private Paint mPaint;

    private boolean enableProgress = true;
    private DownloadInfo mDownloadInfo;

    public CircleDownloadView(Context context) {
        this(context, null);
    }

    public CircleDownloadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_download_progress, this);
        ButterKnife.bind(this, this);
        mRectF = new RectF();

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        //一个ViewGroup一般是不会绘制自己的，除非给CircleDownloadView设置背景或者开启允许绘制
        setWillNotDraw(false);
    }

    /**
     * 同步app下载状态 同步qq音乐的
     *
     * @param appListItemBean
     */
    public void syncState(AppListItemBean appListItemBean) {
        //由于ListView的复用，CircleDownloadView已经可能监听了人人apk下载
        //如果CircleDownloadView被使用，它是回收回来的view, 那么mDownloadInfo不为空
        if (mDownloadInfo != null) {
            //说明这是一个回收回来的CircleDownloadView mDownloadInfo是原来的apk downloadinfo
            //CircleDownloadView要移除以前的观察者
            DownloadManager.getInstance().removeObserver(mDownloadInfo.getPackageName());//人人
            //还残留以前的几个排队的runnable没有跑
        }

        //在这个位置，跑了一个排队的runnable 还是人人的更新runnable

        mAppListItemBean = appListItemBean;
        //初始化下载信息 初始qq音乐
        mDownloadInfo = DownloadManager.getInstance().initDownloadInfo(getContext(),
                appListItemBean.getPackageName(),
                appListItemBean.getSize(),
                appListItemBean.getDownloadUrl());
        //观察DownloadManager状态变化
        DownloadManager.getInstance().addObserver(appListItemBean.getPackageName(), this);

        //根据下载信息刷新界面
        updateStatus(mDownloadInfo);
    }

    private void updateStatus(DownloadInfo downloadInfo) {
        Log.d(TAG, "updateStatus: " + downloadInfo.getPackageName() + mDownloadInfo.getPackageName());
        //过滤掉几个残留人人的runnable
        //在mDownloadInfo被更新成QQ的下载信息之前，有可能还是运行了一个人人 Runnable mDownloadInfo还是人人
        if (!downloadInfo.getPackageName().equals(mDownloadInfo.getPackageName())) {
            return;
        }

        switch (downloadInfo.getStatus()) {
            case DownloadManager.STATE_INSTALLED:
                //显示文本打开
                mDownloadInfoText.setText(getResources().getString(R.string.open));
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_DOWNLOADED:
                clearProgress();
                mDownloadInfoText.setText(getResources().getString(R.string.install));
                mDownload.setImageResource(R.drawable.ic_install);
                break;
            case DownloadManager.STATE_UN_DOWNLOAD:
                clearProgress();//清除掉人人进度
                mDownloadInfoText.setText(getResources().getString(R.string.download));
                mDownload.setImageResource(R.drawable.ic_download);
                break;
            case DownloadManager.STATE_WAITING:
                //图片是x 文本是“等待”
                mDownload.setImageResource(R.drawable.ic_cancel);
                mDownloadInfoText.setText(getResources().getString(R.string.waiting));
                break;
            case DownloadManager.STATE_DOWNLOADING:
                //圆形进度条
                //暂停图片
                mDownload.setImageResource(R.drawable.ic_pause);
                //文本显示进度
                mPercent = (int) (downloadInfo.getProgress() * 1.0f / downloadInfo.getSize() * 100);//15
                String percentString  = mPercent + "%";
                mDownloadInfoText.setText(percentString);
                enableProgress = true;//打开进度条开关
                invalidate();//触发重新绘制 --》 onDraw
                break;
            case DownloadManager.STATE_PAUSE:
                //图片向下箭头
                //文本是继续
                mDownload.setImageResource(R.drawable.ic_download);
                mDownloadInfoText.setText(getResources().getString(R.string.continue_download));
                break;
            case DownloadManager.STATE_FAILED:
                //文本是重试， 图片是重试图片
                mDownload.setImageResource(R.drawable.ic_redownload);
                mDownloadInfoText.setText(getResources().getString(R.string.retry));
                break;
        }
    }

    /**
     * 清除进度条
     */
    private void clearProgress() {
        enableProgress = false;//关闭进度条开关
        invalidate();
    }

    @OnClick(R.id.download)
    public void onViewClicked() {
        //点击处理交给DownloadManager处理
        DownloadManager.getInstance().handleDownloadAction(getContext(), mAppListItemBean.getPackageName());
    }

    @Override
    public void update(Observable o, Object arg) {
        final DownloadInfo downloadInfo = (DownloadInfo) arg;
        //在主线程更新状态 ，并不一定马上执行更新，可能还有几个人人 runnable在排队，ruannable其实是更新上一apk
        post(new Runnable() {
            @Override
            public void run() {
                updateStatus(downloadInfo);
            }
        });
    }

    /**
     * 实现圆形进度条的绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //设置矩形的边界，矩形边界比ImageView 要大
        if (enableProgress) {
            int left = mDownload.getLeft() - 4;
            int top = mDownload.getTop() - 4;
            int right = mDownload.getRight() + 4;
            int bottom = mDownload.getBottom() + 4;
            mRectF.set(left, top, right, bottom);
            int startAngle = -90;
            int sweepAngle = (int) (mPercent * 1.0f / 100 * 360);
            boolean userCenter = false;
            canvas.drawArc(mRectF, startAngle, sweepAngle, userCenter, mPaint);
        }
    }
}
