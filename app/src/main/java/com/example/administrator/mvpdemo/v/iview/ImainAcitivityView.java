package com.example.administrator.mvpdemo.v.iview;

import com.example.administrator.mvpdemo.m.bean.douban.HotMovieBean;
import com.example.administrator.mvpdemo.v.base.IBaseView;

/**
 * Created by Administrator on 2017/6/9.
 */

public interface ImainAcitivityView extends IBaseView{
    void show(HotMovieBean data, boolean isLoadMore);
}
