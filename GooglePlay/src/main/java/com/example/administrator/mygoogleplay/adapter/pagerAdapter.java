package com.example.administrator.mygoogleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.administrator.mygoogleplay.factory.MyFragmentFactory;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class pagerAdapter extends FragmentPagerAdapter{
    private String[] mStringData;
    private static final String TAG = "pagerAdapter";
    public pagerAdapter(String[] stringData, FragmentManager fm) {
        super(fm);
        mStringData = stringData;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position=="+position);
        return MyFragmentFactory.getInstance().getFragment(position);
    }

    @Override
    public int getCount() {
        return mStringData.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringData[position];
    }
}
