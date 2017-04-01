package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;
import com.example.administrator.mygoogleplay.utils.MyAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDetailDesView extends RelativeLayout {
    private static final String TAG = "MyDetailDesView";
    @BindView(R.id.tv_des)
    TextView mTvDes;
    @BindView(R.id.tv_app_name)
    TextView mTvAppName;
    @BindView(R.id.iv_arraw)
    ImageView mIvArraw;
private boolean isOpen=false;
    private static final int MAX_COUNT = 7;
    private int mOriginalHeight;

    public MyDetailDesView(Context context) {
        this(context, null);
    }

    public MyDetailDesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mydetail_des_item, this);
        ButterKnife.bind(this, inflate);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mOriginalHeight = mTvDes.getMeasuredHeight();
                int lineCount = mTvDes.getLineCount();
                if(lineCount>MAX_COUNT){
                    mTvDes.setLines(MAX_COUNT);
                }
            }
        });
    }

    @OnClick(R.id.iv_arraw)
    public void onClick() {
        toggle();
    }

    private void toggle() {
        if(isOpen){
            Log.d(TAG, "toggle: open===>close");

            if(mTvDes.getLineCount()>MAX_COUNT){
                mTvDes.setLines(MAX_COUNT);

                mTvDes.measure(0,0);
                int height = mTvDes.getMeasuredHeight();
                Log.d(TAG, "toggle: =========="+height);
                MyAnimation.AnimationHeight(mTvDes,mOriginalHeight,height);
            }
//            int height = mTvDes.getMeasuredHeight();
//            MyAnimation.AnimationHeight(mTvDes,mOriginalHeight,height);
        }else {
            Log.d(TAG, "toggle: close===>>>>open");
            int height = mTvDes.getMeasuredHeight();
            MyAnimation.AnimationHeight(mTvDes,height,mOriginalHeight);
        }
        isOpen=!isOpen;
    }

    public void bind(MyDetailBean body) {
        mTvAppName.setText(body.getName());
        mTvDes.setText(body.getDes());
    }
}
