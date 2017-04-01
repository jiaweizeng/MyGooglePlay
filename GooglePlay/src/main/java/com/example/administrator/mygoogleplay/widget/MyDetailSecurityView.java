package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;
import com.example.administrator.mygoogleplay.utils.MyAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class MyDetailSecurityView extends RelativeLayout {
    @BindView(R.id.ll_photo)
    LinearLayout mLlPhoto;
    @BindView(R.id.iv_arraw)
    ImageView mIvArraw;
    @BindView(R.id.ll_des)
    LinearLayout mLlDes;
private boolean isOpen=true;
    private static final String TAG = "MyDetailSecurityView";
    public MyDetailSecurityView(Context context) {
        this(context, null);
    }

    public MyDetailSecurityView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mydetail_security_item, this);
        ButterKnife.bind(this, inflate);
    }

    @OnClick(R.id.iv_arraw)
    public void onClick() {
        toggle();
    }

    private void toggle() {

        if(isOpen){

            int measuredHeight = mLlDes.getMeasuredHeight();
            MyAnimation.AnimationHeight(mLlDes,measuredHeight,0);
//            ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(mIvArraw,"rotation",0,180f);
//            objectAnimator.start();
            MyAnimation.RotationAnimation(mIvArraw,0,180f);
          /*  ValueAnimator valueAnimator = ValueAnimator.ofInt(measuredHeight,0);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) mLlDes.getLayoutParams();
                    layoutParams.height=value;
                    mLlDes.setLayoutParams(layoutParams);
                }
            });
            valueAnimator.start();*/
        }else {

            mLlDes.measure(0,0);
            int height = mLlDes.getMeasuredHeight();
//            Log.d(TAG, "toggle: "+height);
//            ValueAnimator valueAnimator = ValueAnimator.ofInt(0,height);
            MyAnimation.AnimationHeight(mLlDes,0,height);

//            ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(mIvArraw,"rotation",180f,0);
//            objectAnimator.start();
            MyAnimation.RotationAnimation(mIvArraw,180f,0);
            /*valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    int value = (int) animation.getAnimatedValue();
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mLlDes.getLayoutParams();
                    layoutParams.height=value;
                    mLlDes.setLayoutParams(layoutParams);
                }
            });
            valueAnimator.start();*/
        }
        isOpen=!isOpen;
    }

    public void bind(MyDetailBean body) {
        for (int i = 0; i <body.getSafe().size() ; i++) {
            MyDetailBean.SafeBean bean = body.getSafe().get(i);
            String safeUrl = MyConstant.URL_IMAGE+bean.getSafeUrl();
            ImageView imageView=new ImageView(getContext());
            Glide.with(getContext()).load(safeUrl).override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).into(imageView);
//            Glide.with(getContext()).load(safeUrl).override(180,160).into(imageView);
            mLlPhoto.addView(imageView);

            LinearLayout linearLayout=new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            ImageView view = new ImageView(getContext());
            String gougou = MyConstant.URL_IMAGE+bean.getSafeDesUrl();
            Glide.with(getContext()).load(gougou).override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).into(view);
//            Glide.with(getContext()).load(gougou).override(80,60).into(view);
            linearLayout.addView(view);
            TextView textView=new TextView(getContext());
            textView.setTextSize(16f);
            textView.setText(bean.getSafeDes());
            if(bean.getSafeDesColor()!=0){
                textView.setTextColor(getResources().getColor(R.color.warning_color));
            }else {
                textView.setTextColor(Color.BLACK);
            }
            linearLayout.addView(textView);
            mLlDes.addView(linearLayout);

        }
    }
}
