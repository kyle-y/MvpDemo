package com.example.administrator.mvpdemo.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.administrator.mvpdemo.R;
import com.example.administrator.mvpdemo.m.component.ApiComponent;
import com.example.administrator.mvpdemo.p.MainActivityPst;
import com.example.administrator.mvpdemo.v.base.BaseActivity;
import com.example.administrator.mvpdemo.v.iview.ImainAcitivityView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPst> implements ImainAcitivityView {

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.refresh();
    }

    @Override
    public void show(String content) {
        textView.setText(content);
    }
}
