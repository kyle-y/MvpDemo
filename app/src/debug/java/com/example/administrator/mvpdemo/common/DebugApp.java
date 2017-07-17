package com.example.administrator.mvpdemo.common;

import com.facebook.stetho.Stetho;

/**
 * Created by Administrator on 2017/7/12.
 */

public class DebugApp extends App{
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
