package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyCategoryItemBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/3/28 0028.
 */
public class MyCategoryItemView extends RelativeLayout {
    private static final String TAG = "MyCategoryItemView";
    @BindView(R.id.tv_category)
    TextView mTvCategory;
    @BindView(R.id.tl_category)
    TableLayout mTlCategory;

    public MyCategoryItemView(Context context) {
        this(context, null);
    }

    public MyCategoryItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mycategory_item, this);
        ButterKnife.bind(this, inflate);
    }


    public void bind(MyCategoryItemBean bean) {
        mTvCategory.setText(bean.getTitle());
//        Log.d(TAG, "bind: ==="+bean.getTitle());
        List<MyCategoryItemBean.InfosBean> infos = bean.getInfos();
        for (int i = 0; i < infos.size(); i++) {
            MyCategoryItemBean.InfosBean bean1 = infos.get(i);
            TableRow row = new TableRow(getContext());
            int i1 = getResources().getDisplayMetrics().widthPixels - mTlCategory.getPaddingLeft() - mTlCategory.getPaddingRight();
            TableRow.LayoutParams params = new TableRow.LayoutParams(getContext(), null);
            params.width=i1/3;

            MyCategoryItemInfosView view1 = new MyCategoryItemInfosView(getContext());
            view1.bind(bean1.getName1(),bean1.getUrl1());
            view1.setLayoutParams(params);
            row.addView(view1);

            if(bean1.getName2().length()>0){
                MyCategoryItemInfosView view2 = new MyCategoryItemInfosView(getContext());
                view2.setLayoutParams(params);
                view2.bind(bean1.getName2(),bean1.getUrl2());
                row.addView(view2);
            }

            if(bean1.getName3().length()>0){
                MyCategoryItemInfosView view3 = new MyCategoryItemInfosView(getContext());
                view3.bind(bean1.getName3(),bean1.getUrl3());
                view3.setLayoutParams(params);
                row.addView(view3);
            }

            mTlCategory.addView(row);
        }

    }
}
