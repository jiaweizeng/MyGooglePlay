package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.app.Constant;
import com.example.leon.googleplaydemo35.bean.AppListItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppListItemView extends RelativeLayout {

    @BindView(R.id.app_icon)
    ImageView mAppIcon;
    @BindView(R.id.app_name)
    TextView mAppName;
    @BindView(R.id.app_rating)
    RatingBar mAppRating;
    @BindView(R.id.app_size)
    TextView mAppSize;
    @BindView(R.id.download_progress)
    CircleDownloadView mDownloadProgress;
    @BindView(R.id.app_des)
    TextView mAppDes;

    public AppListItemView(Context context) {
        this(context, null);
    }

    public AppListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_app_item, this);
        ButterKnife.bind(this, this);
    }


    public void bindView(AppListItemBean appListItemBean) {
        //刷新app图片
        String url = Constant.URL_IMAGE + appListItemBean.getIconUrl();
        Glide.with(getContext()).load(url).into(mAppIcon);
        //刷新标题
        mAppName.setText(appListItemBean.getName());
        //刷新RatingBar
        mAppRating.setRating(appListItemBean.getStars());
        //刷新app size
        mAppSize.setText(Formatter.formatFileSize(getContext(), appListItemBean.getSize()));
        //刷新描述
        mAppDes.setText(appListItemBean.getDes());
    }
}
