package com.example.administrator.mvpdemo.p;

import com.example.administrator.mvpdemo.m.bean.douban.HotMovieBean;
import com.example.administrator.mvpdemo.m.service.DoubanService;
import com.example.administrator.mvpdemo.m.rxhelper.ErrorListener;
import com.example.administrator.mvpdemo.m.rxhelper.RequestCallback;
import com.example.administrator.mvpdemo.m.rxhelper.RetryWithDelay;
import com.example.administrator.mvpdemo.p.base.BasePresenter;
import com.example.administrator.mvpdemo.utils.RxUtils;
import com.example.administrator.mvpdemo.v.iview.ImainAcitivityView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2017/6/12.
 */
public class MainActivityPst extends BasePresenter<ImainAcitivityView> {

    private DoubanService doubanService;

    @Inject
    public MainActivityPst(ErrorListener errorListener, DoubanService doubanService) {
        super(errorListener);
        this.doubanService = doubanService;
    }

    public void refresh() {
        doubanService.fetchMovieTop250(1, 10)
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.<HotMovieBean>getSchedulerTransformer())
                .compose(RxUtils.<HotMovieBean>bindToLifecycle(mView))
                .subscribe(new RequestCallback<HotMovieBean>(errorListener) {
                    @Override
                    public void onNext(@NonNull HotMovieBean hotMovieBean) {
                        super.onNext(hotMovieBean);
                        mView.show(hotMovieBean.getTitle());
                    }
                });
    }
}
