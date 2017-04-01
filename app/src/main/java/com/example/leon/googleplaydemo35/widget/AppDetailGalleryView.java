package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.app.Constant;
import com.example.leon.googleplaydemo35.bean.AppDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leon on 2017/3/30.
 */

public class AppDetailGalleryView extends RelativeLayout {

    @BindView(R.id.screen_container)
    LinearLayout mScreenContainer;

    public AppDetailGalleryView(Context context) {
        this(context, null);
    }

    public AppDetailGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.view_app_detail_gallery, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(AppDetailBean appDetailBean) {
        int padding = getResources().getDimensionPixelSize(R.dimen.padding);
        for (int i = 0; i < appDetailBean.getScreen().size(); i++) {
            String url = appDetailBean.getScreen().get(i);
            //拼接url
            String imageUrl = Constant.URL_IMAGE + url;
            ImageView imageView = new ImageView(getContext());
            //设置imageview padding 最后一个image view加一个右边padding
            if (i == appDetailBean.getScreen().size() - 1) {
                imageView.setPadding(padding, 0, padding, 0);
            } else {
                imageView.setPadding(padding, 0, 0, 0);
            }
            Glide.with(getContext()).load(imageUrl).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);
            //添加到容器
            mScreenContainer.addView(imageView);
        }
    }
}
