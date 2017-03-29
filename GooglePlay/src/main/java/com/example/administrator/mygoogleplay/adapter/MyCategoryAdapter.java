package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.mygoogleplay.widget.MyCategoryItemView;

import java.util.List;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class MyCategoryAdapter<MyCategoryItemBean> extends MyBaseListAdapter {
    private static final String TAG = "MyCategoryAdapter";
    private List<MyCategoryItemBean> mBeen;

    public MyCategoryAdapter(Context context, List<MyCategoryItemBean> been) {
        super(context,been);
        mBeen = been;
    }


    @Override
    protected View onCreateViewHolder(int position) {
        return new MyCategoryItemView(getMyContext());
    }

//    @Override
//    protected ViewHolder onCreateViewHolder(int position) {
//        return new ViewHolder(new MyCategoryItemView(getMyContext()));
//    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
//        Log.d(TAG, "onBindViewHolder: ================");
        ((MyCategoryItemView)holder.view).bind((com.example.administrator.mygoogleplay.bean.MyCategoryItemBean) getDataList().get(position));
    }

}
