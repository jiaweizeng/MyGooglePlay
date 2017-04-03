package com.example.leon.googleplaydemo35.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Leon on 2017/3/27.
 */

/**
 * 每次写ListView的adapter都要很多方法 getCount, getItemId, getItem, getView
 */
public abstract class BaseListAdapter<T> extends BaseAdapter {

    //抽取上下文数据列表
    private Context mContext;
    protected List<T> mDataList;

    public BaseListAdapter(Context context, List<T> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    /**
     * 返回对于位置数据
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return mDataList.get(position);
    }

    /**
     * 返回对于item的id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            //没有回收的view,需要创建
/*            convertView = View.inflate(mContext, R.layout.xx, null);
            viewHolder = new ViewHolder(convertView);
             convertView.setTag(viewHolder);*/
            //基类里面不知道具体list item的视图长相，需要子类去实现，子类实现一个viewhoder，holder item视图
            viewHolder = onCreateViewHolder(position);
            //子类实现了viewholder，创建一个item view， 并且hold住
            convertView = viewHolder.mView;

            convertView.setTag(viewHolder);

        } else {
             viewHolder = (ViewHolder) convertView.getTag();
    }

        //绑定一下hold住的view
//        viewHolder.mView.setText();
        //让子类来实现view的绑定,根据位置position，拿到对应位置的数据，来绑定对应位置item的视图
        onBindViewHolder(viewHolder, position);

        return convertView;
    }

    /**
     * 绑定对应位置的item view
     */
    abstract void onBindViewHolder(ViewHolder viewHolder, int position);

    /**
     * 创建一个viewholder holder 对应位置item view
     */
    abstract ViewHolder onCreateViewHolder(int position);
    

    public class ViewHolder {

        //item 对应view (convertview)
        View mView;

        public ViewHolder(View root) {
            mView = root;
//            root.findViewById(R.id.xx);
        }
    }

    protected List<T> getDataList() {
        return mDataList;
    }


    protected Context getContext() {
        return mContext;
    }

}
