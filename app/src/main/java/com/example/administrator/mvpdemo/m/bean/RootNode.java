package com.example.administrator.mvpdemo.m.bean;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/6/19.
 */

public class RootNode<T> {
    @JSONField(name = "errorCode")
    private int flagCode;
    @JSONField(name = "errorMessage")
    private String errorMsg;
    @JSONField(name = "result")
    private T data;

    public int getFlagCode() {
        return flagCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public T getData() {
        return data;
    }
}
