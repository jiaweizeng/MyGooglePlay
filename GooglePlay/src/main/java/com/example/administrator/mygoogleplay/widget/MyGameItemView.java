package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.text.format.Formatter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MyGameBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyGameItemView extends RelativeLayout {
    @BindView(R.id.iv_app_icon)
    ImageView mIvAppIcon;
    @BindView(R.id.tv_app_name)
    TextView mTvAppName;
    @BindView(R.id.rb_app)
    RatingBar mRbApp;
    @BindView(R.id.tv_app_size)
    TextView mTvAppSize;
    @BindView(R.id.mydownview)
    MyDownView mMydownview;
    @BindView(R.id.tv_app_des)
    TextView mTvAppDes;

    public MyGameItemView(Context context) {
        this(context, null);
    }

    public MyGameItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mygame_item, this);
        ButterKnife.bind(this, inflate);
    }


    public void bind(MyGameBean bean) {
        mTvAppName.setText(bean.getName());
        mTvAppDes.setText(bean.getDes());
        mTvAppSize.setText(Formatter.formatFileSize(getContext(),bean.getSize()));
        mMydownview.bind(bean);
        mRbApp.setRating(bean.getStars());
        String url = MyConstant.URL_IMAGE+bean.getIconUrl();
        Glide.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher).centerCrop().into(mIvAppIcon);
    }
}
