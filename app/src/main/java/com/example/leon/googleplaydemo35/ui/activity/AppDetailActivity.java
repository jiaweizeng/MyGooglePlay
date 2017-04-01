package com.example.leon.googleplaydemo35.ui.activity;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.example.leon.googleplaydemo35.R;

import butterknife.BindView;

/**
 * Created by Leon on 2017/3/30.
 */

public class AppDetailActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_app_detail;
    }

    @Override
    protected void init() {
        super.init();
/*        String extra = getIntent().getStringExtra("package_name");
        Toast.makeText(this, extra, Toast.LENGTH_SHORT).show();*/
        initToolbar();
        setStatusBarColor();
    }

    private void initToolbar() {
        setSupportActionBar(mToolBar);//用Toolbar替换Action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.app_detail));
        actionBar.setDisplayHomeAsUpEnabled(true);//设置返回按钮
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    /**
     * 由于状态栏的颜色在主题中配置成透明了，所以需要写代码将状态栏的颜色动态的改成想要的颜色
     */
    private void setStatusBarColor() {
        //只要api level > 21 才能写代码配置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();//获取Activity对应phone window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }
}
