package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.mygoogleplay.bean.MyGameBean;
import com.example.administrator.mygoogleplay.widget.MyGameItemView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MyGameAdapter extends MyBaseLoadMoreAdapter<MyGameBean>{
    private List<MyGameBean> mDataList;

    public MyGameAdapter(Context context, List<MyGameBean> dataList) {
        super(context, dataList);
        mDataList = dataList;
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder holder, int position) {

        ((MyGameItemView)holder.view).bind(mDataList.get(position));
    }

    @Override
    protected View onCreateNormalView() {
        return new MyGameItemView(getMyContext());
    }
}
