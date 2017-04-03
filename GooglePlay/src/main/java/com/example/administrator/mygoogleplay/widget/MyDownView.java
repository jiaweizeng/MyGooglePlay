package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyDownloadInfo;
import com.example.administrator.mygoogleplay.bean.MyGameBean;
import com.example.administrator.mygoogleplay.manager.MyDownloadManager;

import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyDownView extends RelativeLayout implements Observer {
    @BindView(R.id.iv_download)
    ImageView mIvDownload;
    @BindView(R.id.tv_download)
    TextView mTvDownload;
    private MyDownloadInfo mDownloadInfo;
    private MyGameBean mBean;
    private RectF mRectF;
    private Paint mPaint;
private boolean draw=true;
    private float mFloatPercent;
    private static final String TAG = "MyDownView";
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
        mRectF = new RectF();
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        setWillNotDraw(false);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void update(Observable o, Object arg) {

        final MyDownloadInfo downloadInfo = (MyDownloadInfo) arg;
        post(new Runnable() {
            @Override
            public void run() {
                updateState(downloadInfo);
            }
        });

    }

    public void bind(MyGameBean bean) {
        mDownloadInfo = MyDownloadManager.getInstance().initDownloadInfo(getContext(),
                bean.getPackageName(), bean.getDownloadUrl(), bean.getSize());
        mBean = bean;
        MyDownloadManager.getInstance().addObserver(bean.getPackageName(), this);
        updateState(mDownloadInfo);
    }

    private void updateState(MyDownloadInfo info) {

        switch (info.getStatus()){
            case MyDownloadManager.STATE_INSTALLED:
                mTvDownload.setText(getResources().getString(R.string.open));
                mIvDownload.setImageResource(R.mipmap.ic_install);
                break;
            case MyDownloadManager.STATE_DOWNLOADED:
                cleanBackground();
                mTvDownload.setText(getResources().getString(R.string.install));
                mIvDownload.setImageResource(R.mipmap.ic_install);
                break;
            case MyDownloadManager.STATE_UN_DOWNLOAD:
                mTvDownload.setText(getResources().getString(R.string.download));
                mIvDownload.setImageResource(R.mipmap.ic_download);
                break;
            case MyDownloadManager.STATE_WAITING:
                mTvDownload.setText(getResources().getString(R.string.waiting));
                mIvDownload.setImageResource(R.mipmap.ic_cancel);
                break;
            case MyDownloadManager.STATE_DOWNLOADING:
                draw=true;
                int size = info.getSize();
                int progress = info.getProgress();
                mFloatPercent = progress * 1.0f / size;

                mIvDownload.setImageResource(R.mipmap.ic_pause);
                Log.d(TAG, "updateState: ==="+mFloatPercent);
                mTvDownload.setText((int)(mFloatPercent*100)+"%");
                invalidate();
                break;
            case MyDownloadManager.STATE_PAUSE:
                mIvDownload.setImageResource(R.mipmap.ic_download);
                mTvDownload.setText(getResources().getString(R.string.continue_download));
                break;
            case MyDownloadManager.STATE_FAILED:
                mIvDownload.setImageResource(R.mipmap.ic_redownload);
                mTvDownload.setText(getResources().getString(R.string.retry));
                break;

        }
    }

    private void cleanBackground() {
        draw=false;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(draw){
            int left = mIvDownload.getLeft()-4;
            int top = mIvDownload.getTop()-4;
            int right = mIvDownload.getRight()+4;
            int bottom = mIvDownload.getBottom()+4;
            mRectF.set(left,top,right,bottom);
            canvas.drawArc(mRectF,-90,360*mFloatPercent,false,mPaint);
        }


    }

    @OnClick(R.id.iv_download)
    public void onClick() {
        MyDownloadManager.getInstance().dealMiddleButtonAction(getContext(),mBean.getPackageName());
    }
}
