package com.example.administrator.mygoogleplay.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyAnimation {
    public static void AnimationHeight(final View view, int start, int end){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(start,end);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams.height=value;
                view.setLayoutParams(layoutParams);
            }
        });
        valueAnimator.start();
    }

    public static void RotationAnimation(View view,float startAngle,float endAngle){
        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(view,"rotation",startAngle,endAngle);
        objectAnimator.start();
    }
}
