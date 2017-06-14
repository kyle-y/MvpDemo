package com.example.administrator.mvpdemo.m.component;

import com.example.administrator.mvpdemo.m.model.ApiModule;
import com.example.administrator.mvpdemo.v.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/6/13.
 */

@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}
