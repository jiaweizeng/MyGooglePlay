package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.view.View;

import com.example.administrator.mygoogleplay.bean.MySubjectItemBean;
import com.example.administrator.mygoogleplay.widget.MySubjectItemView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */

public class MySubjectAdapter extends MyBaseLoadMoreAdapter<MySubjectItemBean>{


    private List<MySubjectItemBean> mDataList;

    public MySubjectAdapter(Context context, List<MySubjectItemBean> dataList) {
        super(context, dataList);
        mDataList = dataList;
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder holder, int position) {

        ((MySubjectItemView)holder.view).bind(mDataList.get(position));
    }

    @Override
    protected View onCreateNormalView() {
        return new MySubjectItemView(getMyContext());
    }
}
