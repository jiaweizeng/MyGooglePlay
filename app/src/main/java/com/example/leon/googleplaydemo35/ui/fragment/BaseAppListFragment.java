package com.example.leon.googleplaydemo35.ui.fragment;

import android.content.Intent;
import android.widget.BaseAdapter;

import com.example.leon.googleplaydemo35.adapter.AppListAdapter;
import com.example.leon.googleplaydemo35.bean.AppListItemBean;
import com.example.leon.googleplaydemo35.ui.activity.AppDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于首页，游戏，应用3个页面的item长得一样，说明adpater一样，可以抽取一个公共的adapter
 * 数据列表也一样，所以也可以抽取数据列表
 */
public abstract class BaseAppListFragment extends BaseLoadMoreListFragment {

    List<AppListItemBean> mDataList = new ArrayList<AppListItemBean>();//数据集合


    @Override
    public BaseAdapter onCreateAdapter() {
        return new AppListAdapter(getContext(), mDataList);

    }

    //暴露给子类，让子类能够将获取到数据添加到数据集合
    public List<AppListItemBean> getDataList() {
        return mDataList;
    }

    @Override
    protected void onListItemClick(int position) {
        //跳转到应用详情
        Intent intent = new Intent(getContext(), AppDetailActivity.class);
        //传入点击位置item 包名，需要包名发网络请求获取详情数据
        intent.putExtra("package_name", getDataList().get(position).getPackageName());
        startActivity(intent);
    }

    /**
     * 退出详情界面后重新刷新，绑定观察者
     */
    @Override
    public void onResume() {
        super.onResume();
        if (getAdapter() != null) {
            getAdapter().notifyDataSetChanged();
        }
    }
}
