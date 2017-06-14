package com.example.administrator.mvpdemo.common.delegate;

import android.app.Application;

import com.example.administrator.mvpdemo.m.component.ApiComponent;
import com.example.administrator.mvpdemo.m.component.AppComponent;
import com.example.administrator.mvpdemo.m.component.DaggerApiComponent;
import com.example.administrator.mvpdemo.m.component.DaggerAppComponent;
import com.example.administrator.mvpdemo.m.model.ApiModule;
import com.example.administrator.mvpdemo.m.model.AppModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/13.
 */

public class Appdelegate {
    @Inject
    ActivityDelegate activityDelegate;
    private Application appContext;
    private AppComponent appComponent;
    private ApiComponent apiComponent;

    public Appdelegate(Application appContext) {
        this.appContext = appContext;
    }

    public void onCreate() {
        initInject();
    }

    private void initInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .build();

        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .appModule(getAppModule())
                .build();
    }

    private AppModule getAppModule(){
        return new AppModule(appContext);
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public ApiComponent getApiComponent(){
        return apiComponent;
    }

}
