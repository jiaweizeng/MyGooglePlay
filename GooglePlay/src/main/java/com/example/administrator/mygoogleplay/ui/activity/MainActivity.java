package com.example.administrator.mygoogleplay.ui.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.adapter.pagerAdapter;

public class MainActivity extends MyBaseActivity {

    private static final String TAG = "MainActivity";
    private DrawerLayout mActivityMain;
    private NavigationView mNvMySlide;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mBarDrawerToggle;
    private Toolbar mMToolBar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String[] mArray;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        mArray = getResources().getStringArray(R.array.main_titles);
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mMToolBar = (Toolbar) findViewById(R.id.mytool_bar);
        mActivityMain= (DrawerLayout) findViewById(R.id.activity_main);
        mNvMySlide = (NavigationView) findViewById(R.id.nv_my_slide);
        Log.d(TAG, "init: mNvMySlide===" + mNvMySlide);
        mNvMySlide.setNavigationItemSelectedListener(mSelectedListener);
        initActionBar();
        mViewPager.setAdapter(new pagerAdapter(mArray,getSupportFragmentManager()));

        mTabLayout.setupWithViewPager(mViewPager);
    }



    private void initActionBar() {
        setSupportActionBar(mMToolBar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
//        Log.d(TAG, "mActivityMain=== "+mActivityMain);
        mBarDrawerToggle = new ActionBarDrawerToggle(this, mActivityMain, R.string.open, R.string.close);
//        Log.d(TAG, "mBarDrawerToggle==="+mBarDrawerToggle);
        mBarDrawerToggle.syncState();
        mActivityMain.addDrawerListener(mBarDrawerToggle);
    }

    private NavigationView.OnNavigationItemSelectedListener mSelectedListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.d(TAG, "onNavigationItemSelected: mActivityMain=="+mActivityMain);
            mActivityMain.closeDrawer(GravityCompat.START);
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mBarDrawerToggle.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
