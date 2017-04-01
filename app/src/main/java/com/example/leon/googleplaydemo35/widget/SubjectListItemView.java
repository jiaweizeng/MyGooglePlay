package com.example.leon.googleplaydemo35.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.app.Constant;
import com.example.leon.googleplaydemo35.bean.SubjectItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListItemView extends RelativeLayout {

    @BindView(R.id.icon)
    ImageView mIcon;
    @BindView(R.id.title)
    TextView mTitle;

    public SubjectListItemView(Context context) {
        this(context, null);
    }

    public SubjectListItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.list_subject_item, this);
        ButterKnife.bind(this, this);
    }

    public void bindView(SubjectItemBean bean) {
        mTitle.setText(bean.getDes());
        Glide.with(getContext()).load(Constant.URL_IMAGE +  bean.getUrl()).into(mIcon);
    }
}
