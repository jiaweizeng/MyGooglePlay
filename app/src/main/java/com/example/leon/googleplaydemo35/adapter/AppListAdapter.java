package com.example.leon.googleplaydemo35.adapter;

import android.content.Context;

import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.widget.AppListItemView;

import java.util.List;



//由于首页，游戏，应用3个页面的item长得一样，说明adpater一样，可以抽取一个公共的adapter
public class AppListAdapter extends BaseLoadMoreListAdapter<AppListItemBean> {


    public AppListAdapter(Context context, List<AppListItemBean> dataList) {
        super(context, dataList);
    }

    @Override
    protected void onBindNormalViewHolder(ViewHolder viewHolder, int position) {
        //绑定app 列表 的item
        ((AppListItemView)viewHolder.mView).bindView(getDataList().get(position));
    }

    @Override
    protected ViewHolder onCreateNormalViewHolder() {
        return new ViewHolder(new AppListItemView(getContext()));
    }

}
