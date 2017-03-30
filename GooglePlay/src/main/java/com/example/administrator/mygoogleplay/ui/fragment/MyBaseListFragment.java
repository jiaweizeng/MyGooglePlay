package com.example.administrator.mygoogleplay.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public abstract class MyBaseListFragment extends MyBaseFragment{

    public BaseAdapter getAdapter() {
        return mAdapter;
    }

    private BaseAdapter mAdapter;

    public ListView getListView() {
        return mListView;
    }

    private ListView mListView;

    @Override
    public View onCreateContentView() {
        mListView = new ListView(getContext());
        mAdapter = setAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mItemClickListener);
        mListView.setDivider(null);
        View header = onCreateHeaderView();
        if(header!=null){
            mListView.addHeaderView(header);
        }
        return mListView;
    }

    protected View onCreateHeaderView() {
        return null;
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            position=position-mListView.getHeaderViewsCount();
            onListItemClick(position);
        }
    };

    protected  void onListItemClick(int position){}

    protected abstract BaseAdapter setAdapter();

}
