package com.example.administrator.mvpdemo.common;

import android.app.Application;

import com.example.administrator.mvpdemo.common.delegate.Appdelegate;
import com.example.administrator.mvpdemo.m.component.ApiComponent;
import com.example.administrator.mvpdemo.m.component.AppComponent;

/**
 * Created by Administrator on 2017/6/9.
 */

public class App extends Application{

    private Appdelegate appdelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        appdelegate = new Appdelegate(this);
        appdelegate.onCreate();
    }



    public AppComponent getAppComponent(){
        return appdelegate.getAppComponent();
    }

    public ApiComponent getApiComponent(){
        return appdelegate.getApiComponent();
    }
}
