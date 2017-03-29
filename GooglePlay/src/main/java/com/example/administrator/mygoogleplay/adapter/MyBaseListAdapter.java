package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


/**
 * Created by Administrator on 2017/3/28 0028.
 */

public abstract class MyBaseListAdapter<T> extends BaseAdapter{
    private static final String TAG = "MyBaseListAdapter";
    public Context getMyContext() {
        return mContext;
    }

    private Context mContext;

    public List<T> getDataList() {
        return mDataList;
    }

    private List<T> mDataList;

    public MyBaseListAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if(mDataList!=null){
//            Log.d(TAG, "getCount: bbbbbb");
            return mDataList.size();
        }
//        Log.d(TAG, "getCount: aaaaaaa");
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            holder.view=onCreateViewHolder(position);
//            holder=onCreateViewHolder(position);
            convertView=holder.view;
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(holder,position);
//        Log.d(TAG, "getView: 11=============");
        return convertView;
    }

//    protected abstract ViewHolder onCreateViewHolder(int position);

    protected abstract View onCreateViewHolder(int position);


    protected abstract void onBindViewHolder(ViewHolder holder, int position);


    static class ViewHolder{
        View view;

//        public ViewHolder(View view) {
//            this.view = view;
//        }
    }
}
