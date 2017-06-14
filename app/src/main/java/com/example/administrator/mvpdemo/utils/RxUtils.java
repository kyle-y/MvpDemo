package com.example.administrator.mvpdemo.utils;

import com.example.administrator.mvpdemo.v.base.IBaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * 在presenter中使用生命周期管理，防止内存泄漏
 */

public class RxUtils {

    public static <T> LifecycleTransformer<T> bindToLifecycle(IBaseView view) {
        if (view instanceof RxAppCompatActivity) {
            return ((RxAppCompatActivity) view).bindToLifecycle();
        } else if (view instanceof RxFragment) {
            return ((RxFragment) view).bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }
    }

}
