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
import com.example.administrator.mygoogleplay.bean.MyDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class MyDetailInfosView extends RelativeLayout {
    @BindView(R.id.iv_detail_infos_appicon)
    ImageView mIvDetailInfosAppicon;
    @BindView(R.id.tv_detail_appName)
    TextView mTvDetailAppName;
    @BindView(R.id.rb_detail_stars)
    RatingBar mRbDetailStars;
    @BindView(R.id.tv_detail_downloadCount)
    TextView mTvDetailDownloadCount;
    @BindView(R.id.tv_version_code)
    TextView mTvVersionCode;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_app_size)
    TextView mTvAppSize;
    private static final String TAG = "MyDetailInfosView";
    public MyDetailInfosView(Context context) {
        this(context, null);
    }

    public MyDetailInfosView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mydetail_infos_item, this);
        ButterKnife.bind(this, this);
    }

    public void bind(MyDetailBean body) {
//        Log.d(TAG, "bind: "+body);
        String url = MyConstant.URL_IMAGE+body.getIconUrl();
        Glide.with(getContext()).load(url).placeholder(R.mipmap.ic_launcher).centerCrop().into(mIvDetailInfosAppicon);
        mTvDetailAppName.setText(body.getName());
        mRbDetailStars.setRating(body.getStars());

        String downloadCount = getResources().getString(R.string.download_count);
        String resultCount = String.format(downloadCount,body.getDownloadNum());
        mTvDetailDownloadCount.setText(resultCount);
        mTvVersionCode.setText(String.format(getResources().getString(R.string.version_code),body.getVersion()));
        mTvTime.setText(String.format(getResources().getString(R.string.time),body.getDate()));
        mTvAppSize.setText(String.format(getResources().getString(R.string.app_size),
                Formatter.formatFileSize(getContext(),body.getSize())));
    }
}
