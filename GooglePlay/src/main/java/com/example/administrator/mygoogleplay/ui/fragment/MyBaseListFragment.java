package com.example.administrator.mygoogleplay.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public abstract class MyBaseListFragment extends MyBaseFragment{
    @Override
    public View onCreateContentView() {
        ListView listView = new ListView(getContext());
        listView.setAdapter(setAdapter());
        listView.setOnItemClickListener(mItemClickListener);
        listView.setDivider(null);
        return listView;
    }

    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            onListItemClick(position);
        }
    };

    protected  void onListItemClick(int position){}

    protected abstract ListAdapter setAdapter();

}
