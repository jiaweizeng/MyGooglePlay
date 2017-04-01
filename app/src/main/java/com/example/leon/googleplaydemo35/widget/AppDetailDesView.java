package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.bean.AppDetailBean;
import com.example.leon.googleplaydemo35.utils.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Leon on 2017/3/30.
 */

public class AppDetailDesView extends RelativeLayout {

    private static final String TAG = "AppDetailDesView";

    private static final int MAX_LINES = 7;

    @BindView(R.id.app_detail_des_text)
    TextView mAppDetailDesText;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.des_arrow)
    ImageView mDesArrow;

    private boolean isOpen = false;

    private int mOriginalHeight = 0;// 保存初始化高度

    public AppDetailDesView(Context context) {
        this(context, null);
    }

    public AppDetailDesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_des, this);
        ButterKnife.bind(this, this);
        //监听布局完成，设置初始化行高
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //只监听一次
                getViewTreeObserver().removeGlobalOnLayoutListener(this);
                //布局完成之后，在设置行高之前，我保存下初始高度
                mOriginalHeight = mAppDetailDesText.getMeasuredHeight();
                //如果描述的行数大于7行，初始化时就设置成7行
                int lineCount = mAppDetailDesText.getLineCount();
                Log.d(TAG, "onGlobalLayout:" + lineCount);
                if (lineCount > MAX_LINES) {
                    //初始化行
                    mAppDetailDesText.setLines(MAX_LINES);
                }
            }
        });
    }

    public void bindView(AppDetailBean appDetailBean) {
        mAppDetailDesText.setText(appDetailBean.getDes());
        mAppName.setText(appDetailBean.getName());
        //如果描述的行数大于7行，初始化时就设置成7行
        int lineCount = mAppDetailDesText.getLineCount();
        Log.d(TAG, "bindView: " + lineCount);//这里是拿不到行高，绘制流程还没走
    }

    @OnClick(R.id.des_arrow)
    public void onViewClicked() {
        toggle();
    }

    /**
     * 展开或着关闭描述
     */
    private void toggle() {
        if (isOpen) {
            //关闭
            //从展开后的高度(原始高度) 到7行高度
            int start = mOriginalHeight;
            //如果大于7行，设置行高为7行，测量7行高度
            if (mAppDetailDesText.getLineCount() > MAX_LINES) {
                mAppDetailDesText.setLines(7);
                mAppDetailDesText.measure(0, 0);
            }
            int end = mAppDetailDesText.getMeasuredHeight();
            AnimationUtil.animationViewHeight(mAppDetailDesText, start, end);
            AnimationUtil.rotateView(mDesArrow, -180, 0);

        } else {
            //如果关闭就打开
            //如果大于7, 从7行展开到应该有的高度
            int measuredHeight = mAppDetailDesText.getMeasuredHeight();//拿到7行的高度
/*            //测量，期望为UNSPECIFIED 即使期望模式UNSPECIFIED, 还是考虑之前设置的行高
            mAppDetailDesText.measure(0, 0);//测量无效，测出来还是7行高度，由于初始化时候已经设置了行高
            int afterMesuredHeight = mAppDetailDesText.getMeasuredHeight();
            Log.d(TAG, "toggle: " + measuredHeight + " " + afterMesuredHeight);*/
            AnimationUtil.animationViewHeight(mAppDetailDesText, measuredHeight, mOriginalHeight);
            AnimationUtil.rotateView(mDesArrow, 0, -180f);
        }

        isOpen = !isOpen;
    }
}
