package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;
import com.example.administrator.mygoogleplay.manager.MyDownloadManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDetailBottomView extends RelativeLayout {
    @BindView(R.id.collection)
    Button mCollection;
    @BindView(R.id.download)
    MyDownloadButton mDownload;
    @BindView(R.id.share)
    Button mShare;
    private MyDetailBean mBody;

    public MyDetailBottomView(Context context) {
        this(context, null);
    }

    public MyDetailBottomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View.inflate(getContext(), R.layout.mydetail_bottom_item, this);
        ButterKnife.bind(this, this);
//        mDownload.setMax(100);
//        mDownload.setProgress(10);
    }

    public void bind(MyDetailBean body) {
        mBody = body;
        mDownload.sync(body);
    }

    @OnClick({R.id.collection, R.id.download, R.id.share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collection:
                break;
            case R.id.download:

                MyDownloadManager.getInstance().dealMiddleButtonAction(getContext(),mBody.getPackageName());
                break;
            case R.id.share:
                break;
        }
    }


}
