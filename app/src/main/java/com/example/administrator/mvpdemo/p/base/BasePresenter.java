package com.example.administrator.mvpdemo.p.base;

import com.example.administrator.mvpdemo.m.rxhelper.ErrorListener;
import com.example.administrator.mvpdemo.v.base.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/6/9.
 */

public abstract class BasePresenter<View extends IBaseView> {

    protected View mView;
    protected Reference<View> reference;
    protected ErrorListener errorListener;

    public BasePresenter(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void attachView(View view) {
        reference = new WeakReference<View>(view);
        mView = reference.get();
    }

    public void detachView(){
        if (reference != null) {
            reference.clear();
        }
        reference = null;
    }

}
