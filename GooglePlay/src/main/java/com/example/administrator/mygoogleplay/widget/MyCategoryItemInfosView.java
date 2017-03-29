package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.app.MyConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class MyCategoryItemInfosView extends RelativeLayout {
    @BindView(R.id.iv_category_infos)
    ImageView mIvCategoryInfos;
    @BindView(R.id.tv_category_infos)
    TextView mTvCategoryInfos;

    public MyCategoryItemInfosView(Context context) {
        this(context, null);
    }

    public MyCategoryItemInfosView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mycategory_item_infos, this);
        ButterKnife.bind(this, inflate);
    }

    public void bind(String name1, String url1) {
        mTvCategoryInfos.setText(name1);
        String url = MyConstant.URL_IMAGE+url1;
        Glide.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher).centerCrop().into(mIvCategoryInfos);
    }
}
