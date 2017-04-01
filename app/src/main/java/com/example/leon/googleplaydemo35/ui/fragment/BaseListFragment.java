package com.example.leon.googleplaydemo35.ui.fragment;

import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * Created by Leon on 2017/3/27.
 */
//由于首页，应用，游戏，专题，分类都是List的形式，所以可以抽取一个ListView。
public abstract class BaseListFragment extends BaseFragment {


    private ListView mListView;
    private BaseAdapter mAdapter;

    //返回一个ListView
    @Override
    protected View onCreateContentView() {
        mListView = new ListView(getContext());
        mAdapter = onCreateAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(mOnItemClickListener);
        mListView.setDivider(null);//去掉分隔线
        View header = onCreateHeaderView();
        if ( header != null) {
            mListView.addHeaderView(header);

        }
        initListView();
        return mListView;
    }

    /**
     * 抽取创建头的方法，子类可以实现该方法返回ListView的头
     * @return
     */
    protected View onCreateHeaderView() {
        return null;
    }

    protected void initListView() {}

    /**
     *  子类实现该方法来返回一个adapter给我
     *  因为每个节目列表长相不一样，adapter决定的，所以必须子类来实现
     */
    public abstract BaseAdapter onCreateAdapter();

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            Toast.makeText(getContext(), "BaseListFragment", Toast.LENGTH_SHORT).show();
            position = position - getListView().getHeaderViewsCount();//调整点击的位置，处理加了头的情况
            onListItemClick(position);
        }
    };

    /**
     * 子类自己来实现item点击事件
     * @param position
     */
    protected void onListItemClick(int position) {
    }

    public ListView getListView() {
        return mListView;
    }

    public BaseAdapter getAdapter() {
        return mAdapter;
    }
}
