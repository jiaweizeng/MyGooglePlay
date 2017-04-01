package com.example.leon.googleplaydemo35.adapter;

import android.content.Context;

import com.example.leon.googleplaydemo35.widget.LoadingMoreProgressView;

import java.util.List;

//由于列表的item的视图都是由Adapter来决定的，且首页，应用，游戏，专题都有一个加载进度条，
// 所以可以抽取一个Adapter来封装加载进度条的创建
//ListView的多条目的实现
public abstract class BaseLoadMoreListAdapter<T> extends BaseListAdapter<T> {

    private static final int ITEM_TYPE_NORMAL = 0;
    private static final int ITEM_TYPE_LOAD_MORE = 1;

    public BaseLoadMoreListAdapter(Context context, List<T> dataList) {
        super(context, dataList);
    }

    /**
     *  返回条目个数，由于多了一个进度条的条目，所以多加一个1。
     */
    @Override
    public int getCount() {
        if (getDataList() == null) {
            return 0;
        } else {
            return getDataList().size() + 1;
        }
    }

    /**
     *  返回条目的类型个数，这里有两种类型的条目，一种是正常的item, 一种是进度条条目
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 返回对应position位置的item的类型，最后一个位置为进度条类型，其他为正常item类型
     */
    @Override
    public int getItemViewType(int position) {
        if (position == getCount() - 1) {
            return ITEM_TYPE_LOAD_MORE;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    /**
     * 由于加载更多进度条每个页面是一样的，所以统一在这里创建
     */
    @Override
    protected ViewHolder onCreateViewHolder(int position) {
        if (getItemViewType(position) == ITEM_TYPE_NORMAL) {
            //子类实现普通类型item的创建
            return onCreateNormalViewHolder();
        } else {
            return new ViewHolder(new LoadingMoreProgressView(getContext()));
        }
    }

    /**
     * 只需要绑定普通类型的item, 进度条不需要数据进行绑定
     */
    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (ITEM_TYPE_NORMAL == getItemViewType(position)) {
            onBindNormalViewHolder(viewHolder, position);//子类来实现普通类型的item的绑定
        }
    }

    /**
     *  创建普通的item的ViewHolder
     */
    protected abstract ViewHolder onCreateNormalViewHolder();

    /**
     * 绑定普通的ViewHolder
     */
    protected abstract void onBindNormalViewHolder(ViewHolder viewHolder, int position);

}
