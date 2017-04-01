package com.example.leon.googleplaydemo35.ui.fragment;

import android.util.Log;
import android.widget.AbsListView;

/**
 * 创建者: Leon
 * 创建时间: 2016/9/24 22:46
 * 描述： TODO
 */

//首页，应用，游戏，专题都能够滚动到底部加载更多，都显示一个加载进度条。 所以BaseLoadMoreListFragment封装了滚动到列表底部处罚加载更多的逻辑
public abstract class BaseLoadMoreListFragment extends BaseListFragment{

    private static final String TAG = "BaseLoadMoreListFragmen";

    @Override
    protected void initListView() {
        super.initListView();
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    //getLastVisiblePosition会将头计算在内， 所以getLoadMorePosition方法也加头的个数计算
                    Log.d(TAG, "onScrollStateChanged: " + view.getLastVisiblePosition() + " " + getLoadMorePosition());
                    if (view.getLastVisiblePosition() == getLoadMorePosition()) {
                        onStartLoadMore();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    private int getLoadMorePosition() {
        return getAdapter().getCount() - 1 + getListView().getHeaderViewsCount();
    }

    /**
     * 子类来实现加载更多数据的操作
     */
    protected abstract void onStartLoadMore();

}
