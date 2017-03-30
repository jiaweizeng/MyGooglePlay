package com.example.administrator.mygoogleplay.ui.fragment;

import android.widget.AbsListView;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public abstract class MyBaseLoadMoreListFragment extends MyBaseListFragment{

    @Override
    protected void init() {
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(view.getLastVisiblePosition()== getAdapterCount()){

                    onStartLoadMore();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    protected abstract void onStartLoadMore();

    private int getAdapterCount() {
        return getAdapter().getCount()-1+getListView().getHeaderViewsCount();
    }
}
