package com.example.administrator.mvpdemo.p;

import com.demo.greendao.MoviesDao;
import com.example.administrator.mvpdemo.m.bean.douban.HotMovieBean;
import com.example.administrator.mvpdemo.m.database.GreendaoManager;
import com.example.administrator.mvpdemo.m.database.daos.Movies;
import com.example.administrator.mvpdemo.m.rxhelper.ErrorListener;
import com.example.administrator.mvpdemo.m.rxhelper.RequestCallback;
import com.example.administrator.mvpdemo.m.rxhelper.RetryWithDelay;
import com.example.administrator.mvpdemo.m.service.DoubanService;
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

    private int start = 1, count = 10;
    private boolean isLoadMore;
    private MoviesDao moviesDao;

    @Inject
    public MainActivityPst(ErrorListener errorListener, DoubanService doubanService) {
        super(errorListener);
        this.doubanService = doubanService;
    }

    @Override
    public void attachView(ImainAcitivityView view) {
        super.attachView(view);
        moviesDao = GreendaoManager.getInstance().getMainSession().getMoviesDao();
    }

    private void request() {
        doubanService.fetchMovieTop250(start, count)
                .retryWhen(new RetryWithDelay(3, 2))
                .compose(RxUtils.<HotMovieBean>getSchedulerTransformer())
                .compose(RxUtils.<HotMovieBean>bindToLifecycle(mView))
                .subscribe(new RequestCallback<HotMovieBean>(errorListener) {
                    @Override
                    public void onNext(@NonNull HotMovieBean data) {
                        super.onNext(data);
                        changeData(data);
                        mView.show(data, isLoadMore);
                    }
                });
    }

    public void refresh() {
        start = 1;
        isLoadMore = false;
        request();
    }

    public void loadMore() {
        start += count;
        isLoadMore = true;
        request();
    }


    boolean isFirstTime = true;
    private void changeData(HotMovieBean data) {
        for (HotMovieBean.SubjectsBean bean : data.getSubjects()
                ) {
            Movies movies = new Movies(Long.parseLong(bean.getId()), bean.getTitle(), Integer.parseInt(bean.getYear()), bean.getGenres().toString());
            if (isLoadMore) {
                moviesDao.insert(movies);
            } else {
                if (isFirstTime) {
                    moviesDao.insert(movies);
                } else {
                    moviesDao.update(movies);
                }
            }
        }
        isFirstTime = false;
    }
}
