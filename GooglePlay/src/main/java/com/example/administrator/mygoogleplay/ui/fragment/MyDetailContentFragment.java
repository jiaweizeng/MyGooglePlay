package com.example.administrator.mygoogleplay.ui.fragment;

import android.view.View;

import com.example.administrator.mygoogleplay.R;
import com.example.administrator.mygoogleplay.bean.MyDetailBean;
import com.example.administrator.mygoogleplay.network.MyRetrofit;
import com.example.administrator.mygoogleplay.widget.MyDetailBottomView;
import com.example.administrator.mygoogleplay.widget.MyDetailDesView;
import com.example.administrator.mygoogleplay.widget.MyDetailGalleryView;
import com.example.administrator.mygoogleplay.widget.MyDetailInfosView;
import com.example.administrator.mygoogleplay.widget.MyDetailSecurityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/30 0030.
 */

public class MyDetailContentFragment extends MyBaseFragment{
    private static final String TAG = "MyDetailContentFragment";
    private MyDetailBean mBody;

    @Override
    public View onCreateContentView() {
        View inflate = View.inflate(getContext(), R.layout.mydetail_content, null);
        MyDetailInfosView detailInfosView = (MyDetailInfosView) inflate.findViewById(R.id.myDetailInfos);
        detailInfosView.bind(mBody);
        MyDetailSecurityView securityView = (MyDetailSecurityView) inflate.findViewById(R.id.myDetailSecurity);
        securityView.bind(mBody);
        MyDetailGalleryView galleryView = (MyDetailGalleryView) inflate.findViewById(R.id.myDetailGalleryView);
        galleryView.bind(mBody);
        MyDetailDesView detailDesView= (MyDetailDesView) inflate.findViewById(R.id.myDetailDes);
        detailDesView.bind(mBody);
        MyDetailBottomView detailBottomView= (MyDetailBottomView) inflate.findViewById(R.id.detailBottomView);
        detailBottomView.bind(mBody);
        return inflate;
    }

    @Override
    public void startLoadData() {
        super.startLoadData();
        String packageName = getActivity().getIntent().getStringExtra("packageName");
        Call<MyDetailBean> call = MyRetrofit.getInstance().getApi().listDetail(packageName);
        call.enqueue(new Callback<MyDetailBean>() {
            @Override
            public void onResponse(Call<MyDetailBean> call, Response<MyDetailBean> response) {
                mBody = response.body();
//                Log.d(TAG, "onResponse: ===="+mBody);
                dataLoadSuccess();
            }

            @Override
            public void onFailure(Call<MyDetailBean> call, Throwable t) {
dataLoadError();
            }
        });
    }
}
