package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.mygoogleplay.widget.MyLoadMoreItemView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public abstract class MyBaseLoadMoreAdapter<T> extends MyBaseListAdapter<T>{
    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOAD_MORE = 1;
    public MyBaseLoadMoreAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    @Override
    public int getCount() {
        if(getDataList()==null){
            return 0;
        }
        return getDataList().size()+1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==getCount()-1){
            return ITEM_TYPE_LOAD_MORE;
        }
        return ITEM_TYPE_NORMAL;
    }

    @Override
    protected View onCreateViewHolder(int position) {
        if(getItemViewType(position)==ITEM_TYPE_LOAD_MORE){

            return new MyLoadMoreItemView(getMyContext());
        }
        return onCreateNormalView();
    }


    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
        if(ITEM_TYPE_NORMAL==getItemViewType(position)){
            onBindNormalViewHolder(holder,position);
        }
    }

    protected abstract void onBindNormalViewHolder(ViewHolder holder, int position);

    protected abstract View onCreateNormalView();
}
