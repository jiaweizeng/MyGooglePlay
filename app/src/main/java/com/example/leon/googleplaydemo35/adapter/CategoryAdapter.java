package com.example.leon.googleplaydemo35.adapter;

import android.content.Context;

import com.example.leon.googleplaydemo35.bean.CategoryItemBean;
import com.example.leon.googleplaydemo35.widget.CategoryItemView;

import java.util.List;

/**
 * Created by Leon on 2017/3/27.
 */

public class CategoryAdapter extends BaseListAdapter<CategoryItemBean> {


    public CategoryAdapter(Context context, List<CategoryItemBean> dataList) {
        super(context, dataList);
    }

    /**
     * 根据position位置获取对应的数据，刷新item
     */
    @Override
    void onBindViewHolder(ViewHolder viewHolder, int position) {
        ((CategoryItemView)viewHolder.mView).bindView(getDataList().get(position));
    }

    /**
     * 创建一个viewholer holder住分类界面的一个item的视图 CategoryItemView
     */
    @Override
    ViewHolder onCreateViewHolder(int position) {
        return new ViewHolder(new CategoryItemView(getContext()));
    }
}
