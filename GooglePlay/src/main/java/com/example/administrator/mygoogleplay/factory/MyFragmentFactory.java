package com.example.administrator.mygoogleplay.factory;


import android.support.v4.app.Fragment;

import com.example.administrator.mygoogleplay.ui.fragment.MyAppFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MyCategoryFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MyGameFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MyHomeFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MyHotFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MyRecommendFragment;
import com.example.administrator.mygoogleplay.ui.fragment.MySubjectFragment;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public class MyFragmentFactory {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_APP = 1;
    private static final int FRAGMENT_GAME = 2;
    private static final int FRAGMENT_SUBJECT = 3;
    private static final int FRAGMENT_RECOMMEND = 4;
    private static final int FRAGMENT_CATEGORY = 5;
    private static final int FRAGMENT_HOT = 6;
    private static MyFragmentFactory mFragmentFactory;

    public static MyFragmentFactory getInstance() {
        if (mFragmentFactory == null) {
            synchronized (MyFragmentFactory.class) {
                if (mFragmentFactory == null) {
                    mFragmentFactory = new MyFragmentFactory();
                }
            }
        }
        return mFragmentFactory;
    }

    public Fragment getFragment(int position){
        switch (position){
            case FRAGMENT_HOME:
                return new MyHomeFragment();
            case FRAGMENT_APP:
                return new MyAppFragment();
            case FRAGMENT_GAME:
                return new MyGameFragment();
            case FRAGMENT_SUBJECT:
                return new MySubjectFragment();
            case FRAGMENT_RECOMMEND:
                return new MyRecommendFragment();
            case FRAGMENT_CATEGORY:
                return new MyCategoryFragment();
            case FRAGMENT_HOT:
                return new MyHotFragment();
        }
        return null;
    }

}
