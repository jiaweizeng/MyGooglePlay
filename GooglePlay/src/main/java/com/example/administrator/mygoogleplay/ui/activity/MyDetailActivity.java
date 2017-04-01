package com.example.administrator.mygoogleplay.ui.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.administrator.mygoogleplay.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class MyDetailActivity extends MyBaseActivity {


    @BindView(R.id.tb_my_detail)
    Toolbar mTbMyDetail;

    @Override
    protected void init() {
        super.init();
        initActionBar();
        setStatusBarColor();
    }

    private void initActionBar() {
        setSupportActionBar(mTbMyDetail);
        ActionBar bar = getSupportActionBar();
        bar.setTitle(R.string.app_detail);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.mydetail_activity;
    }

    private void setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
