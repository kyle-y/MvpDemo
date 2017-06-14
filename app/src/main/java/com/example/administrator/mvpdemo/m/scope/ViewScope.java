package com.example.administrator.mvpdemo.m.scope;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/6/13.
 */

@Scope
@Retention(RUNTIME)
public @interface ViewScope {
}
