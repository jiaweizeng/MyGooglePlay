package com.example.leon.googleplaydemo35.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leon.googleplaydemo35.R;
import com.example.leon.googleplaydemo35.adapter.MainAdapter;
import com.example.leon.googleplaydemo35.manager.DownloadManager;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    private ActionBar supportActionBar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTitles;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        navigationView.setNavigationItemSelectedListener(onNavigationItemSelectedListener);

        mTitles = getResources().getStringArray(R.array.main_titles);
        initActionBar();
        initViewPager();

//        DownloadManager.getInstance().createDownloadDir();
        int result = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result != PackageManager.PERMISSION_GRANTED) {
            //没有写磁盘权限，申请
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

    }

    private void initViewPager() {
        mViewPager.setAdapter(new MainAdapter(mTitles, getSupportFragmentManager()));
        //关联TabLayout和ViewPager
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(MainActivity.this);
            textView.setText(mTitles[position]);

            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    };

    private void initActionBar() {
//        ActionBar actionBar = getActionBar();//拿到空
        setSupportActionBar(mToolBar);    //将Toolbar替换成ActionBar，原来ActionBar的操作全部换成操作Toolbar

        supportActionBar = getSupportActionBar();
        //设置标题
/*
        supportActionBar.setTitle("标题");
        supportActionBar.setSubtitle("副标题");
        supportActionBar.setDisplayShowTitleEnabled(false);

        //设置图片
        supportActionBar.setIcon(R.drawable.ic_about);
        supportActionBar.setLogo(R.drawable.ic_default);
        supportActionBar.setDisplayUseLogoEnabled(true);
        supportActionBar.setDisplayShowHomeEnabled(true);
*/

        //设置返回按钮
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        //DrawerLayout和ActionBar的联动
        // ActionBarDrawerToggle能够拿到Activity，所以能够拿到ActionBar去设置图片
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        //同步DrawerLayout状态，来显示成不同图片
        mActionBarDrawerToggle.syncState();

        //注册DrawerLayout监听器，监听DrawerLayout动作
       mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);   //mActionBarDrawerToggle要监听Drawerlayout
    }

    /**
     * 创建菜单选项
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    /**
     * ActionBar菜单按钮点击事件回调
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //打开或者关闭侧滑菜单, 内部封装了mDrawerLayout.openDrawer(), mDrawerLayout.closeDrawer();
                mActionBarDrawerToggle.onOptionsItemSelected(item);
//                mDrawerLayout.openDrawer();
                break;
            case R.id.menu_test:
                Toast.makeText(this, "Menu Test Click", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    private NavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {

        /**
         *
         * @param item 点击的菜单选项
         * @return true将点击选项显示成选中状态
         */
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            navigationView.setCheckedItem(item.getItemId());//设置菜单选项选中
            return true;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    DownloadManager.getInstance().createDownloadDir();
                } else {
                    Toast.makeText(this, "你残忍的拒绝了我，无法下载应用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
