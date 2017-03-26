package com.example.administrator.mygoogleplay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/26 0026.
 */

public abstract class MyBaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        init();
        setContentView(getLayoutId());
    }

    public abstract int getLayoutId();

    protected void init() {
    }
}
