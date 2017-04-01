package com.example.administrator.mygoogleplay.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.app.MyConstant;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class MyDetailGalleryView extends RelativeLayout {
    @BindView(R.id.ll_detail_gallery)
    LinearLayout mLlDetailGallery;

    public MyDetailGalleryView(Context context) {
        this(context, null);
    }

    public MyDetailGalleryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        View inflate = View.inflate(getContext(), R.layout.mydetail_gallery_item, this);
        ButterKnife.bind(this, inflate);
    }


    public void bind(MyDetailBean body) {
        List<String> screen = body.getScreen();
        for (int i = 0; i <screen.size() ; i++) {
            ImageView imageView=new ImageView(getContext());
            int pading = getResources().getDimensionPixelSize(R.dimen.padding);
            if(i==0){
                imageView.setPadding(pading,0,pading,0);
            }else {
                imageView.setPadding(0,0,pading,0);
            }

            String url = MyConstant.URL_IMAGE+screen.get(i);
            Glide.with(getContext()).load(url).override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).into(imageView);
            mLlDetailGallery.addView(imageView);

        }
    }
}
