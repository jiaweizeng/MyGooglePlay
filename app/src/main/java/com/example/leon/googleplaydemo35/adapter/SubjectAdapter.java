package com.example.leon.googleplaydemo35.adapter;

import android.content.Context;

import com.example.leon.googleplaydemo35.bean.SubjectItemBean;
import com.example.leon.googleplaydemo35.widget.SubjectListItemView;

import java.util.List;

/**
 * Created by Leon on 2017/3/29.
 */

public class SubjectAdapter extends BaseLoadMoreListAdapter<SubjectItemBean> {

    public SubjectAdapter(Context context, List<SubjectItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new SubjectListItemView(getContext()));
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        ((SubjectListItemView)viewHolder.mView).bindView(getDataList().get(position));
    }
}
