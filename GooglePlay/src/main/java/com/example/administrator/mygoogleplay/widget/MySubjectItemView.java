package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MySubjectItemBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MySubjectItemView extends LinearLayout {
    @BindView(R.id.iv_subject)
    ImageView mIvSubject;
    @BindView(R.id.tv_subject)
    TextView mTvSubject;

    public MySubjectItemView(Context context) {
        this(context, null);
    }

    public MySubjectItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mysubject_item, this);
        ButterKnife.bind(this,inflate);
    }

    public void bind(MySubjectItemBean bean) {
        mTvSubject.setText(bean.getDes());
        String url= MyConstant.URL_IMAGE+bean.getUrl();
        Glide.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher).centerCrop().into(mIvSubject);
    }
}
