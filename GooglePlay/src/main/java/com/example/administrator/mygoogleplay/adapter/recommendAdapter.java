package com.example.administrator.mygoogleplay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.mygoogleplay.widget.StellarMap;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/3/27 0027.
 */

public class recommendAdapter implements StellarMap.Adapter{
    private Context mContext;
    private List<String> mDataList;
    private static final int PAGE_COUNT = 15;
    public recommendAdapter(Context context, List<String> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public int getGroupCount() {
        int count = mDataList.size()/PAGE_COUNT;
        if(mDataList.size()%PAGE_COUNT != 0){
            count++;
        }
        return count;
    }

    @Override
    public int getCount(int group) {
        if(mDataList.size()%PAGE_COUNT!=0){
            if(group==getGroupCount()-1){
                return mDataList.size()%PAGE_COUNT;
            }
        }
        return PAGE_COUNT;
    }

    @Override
    public View getView(int group, int position, View convertView) {
        if(convertView==null){
            convertView=new TextView(mContext);
        }
        TextView textView = (TextView) convertView;
        textView.setTextColor(getColor());
        textView.setTextSize(new Random().nextInt(10)+15);
        textView.setText(mDataList.get(group*PAGE_COUNT+position));
        return textView;
    }

    private int getColor() {

        return Color.argb(255,new Random().nextInt(200)+30,
                new Random().nextInt(200)+30,
                new Random().nextInt(200)+30);
    }

    @Override
    public int getNextGroupOnPan(int group, float degree) {
        return 0;
    }

    @Override
    public int getNextGroupOnZoom(int group, boolean isZoomIn) {
        if(isZoomIn){
            return (group+1)%getGroupCount();
        }else {
            return (group-1+getGroupCount())%getGroupCount();
        }
    }
}
