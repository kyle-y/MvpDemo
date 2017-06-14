package com.example.administrator.mvpdemo.p;

import com.example.administrator.mvpdemo.m.bean.douban.HotMovieBean;
import com.example.administrator.mvpdemo.m.service.DoubanService;
import com.example.administrator.mvpdemo.p.base.BasePresenter;
import com.example.administrator.mvpdemo.v.iview.ImainAcitivityView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/12.
 */
public class MainActivityPst extends BasePresenter<ImainAcitivityView> {

    private DoubanService doubanService;

    @Inject
    public MainActivityPst(DoubanService doubanService) {
        this.doubanService = doubanService;
    }

    public void refresh() {
        doubanService.fetchMovieTop250(1, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(@NonNull HotMovieBean hotMovieBean) throws Exception {
                        if (hotMovieBean != null) {
                            mView.show(hotMovieBean.getTitle());
                        }
                    }
                });
    }
}
