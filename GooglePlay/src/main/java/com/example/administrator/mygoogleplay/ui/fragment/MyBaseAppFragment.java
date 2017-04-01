package com.example.administrator.mygoogleplay.ui.fragment;

import android.content.Intent;
import android.widget.BaseAdapter;

import com.example.administrator.mygoogleplay.adapter.MyGameAdapter;
import com.example.administrator.mygoogleplay.bean.MyGameBean;
import com.example.administrator.mygoogleplay.ui.activity.MyDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public abstract class MyBaseAppFragment extends MyBaseLoadMoreListFragment{
    public List<MyGameBean> getDataList() {
        return mDataList;
    }

    private List<MyGameBean> mDataList = new ArrayList<>();

    @Override
    protected BaseAdapter setAdapter() {
        return new MyGameAdapter(getContext(),mDataList);
    }

    @Override
    protected void onListItemClick(int position) {
        Intent intent=new Intent(getContext(), MyDetailActivity.class);
        intent.putExtra("packageName",mDataList.get(position).getPackageName());
        startActivity(intent);
    }
}
