package com.example.administrator.mvpdemo.m.component;

import com.example.administrator.mvpdemo.common.delegate.Appdelegate;
import com.example.administrator.mvpdemo.m.model.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/12.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(Appdelegate appdelegate);
}
