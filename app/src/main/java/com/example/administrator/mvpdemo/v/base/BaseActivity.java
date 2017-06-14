package com.example.administrator.mvpdemo.v.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.mvpdemo.common.App;
import com.example.administrator.mvpdemo.m.component.ApiComponent;
import com.example.administrator.mvpdemo.p.base.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/6/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IBaseView {

    @Inject
    protected T mPresenter;

    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(((App) getApplication()).getApiComponent());
        mPresenter.attachView(this);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
    }

    protected abstract void inject(ApiComponent apiComponent);
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        unbinder.unbind();
    }
}
