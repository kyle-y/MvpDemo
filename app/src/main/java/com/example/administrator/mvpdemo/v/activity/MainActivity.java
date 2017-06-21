package com.example.administrator.mvpdemo.v.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.mvpdemo.R;
import com.example.administrator.mvpdemo.m.bean.douban.HotMovieBean;
import com.example.administrator.mvpdemo.m.component.ApiComponent;
import com.example.administrator.mvpdemo.p.MainActivityPst;
import com.example.administrator.mvpdemo.tools.imageLoader.ImageLoader;
import com.example.administrator.mvpdemo.v.base.BaseActivity;
import com.example.administrator.mvpdemo.v.iview.ImainAcitivityView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPst> implements ImainAcitivityView {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private BaseQuickAdapter<HotMovieBean.SubjectsBean, BaseViewHolder> adapter;
    private View emptyView;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        emptyView = getLayoutInflater().inflate(R.layout.empty_view, null);
        emptyView.findViewById(R.id.layoutEmptyView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.progress).setVisibility(View.VISIBLE);
                mPresenter.refresh();
            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0, 0, 0, 10);
            }
        });
        initAdapter();
    }

    private void initAdapter() {
        adapter = new BaseQuickAdapter<HotMovieBean.SubjectsBean, BaseViewHolder>(R.layout.item_movie) {
            @Override
            protected void convert(BaseViewHolder helper, HotMovieBean.SubjectsBean item) {
                ImageLoader.loadImageWithUrl((ImageView) helper.getView(R.id.ivPhoto), item.getImages().getMedium());
                helper.setText(R.id.tvCnName, item.getTitle())
                        .setText(R.id.tvEnName, item.getOriginal_title())
                        .setText(R.id.tvTime, item.getYear())
                        .setText(R.id.tvType, item.getGenres().toString());
            }
        };

        adapter.setEmptyView(emptyView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        adapter.bindToRecyclerView(recyclerView);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        },recyclerView);
    }

    @Override
    public void show(HotMovieBean data, boolean isLoadMore) {
        if (isLoadMore) {
            if (data.getSubjects().size() == 0) {
                adapter.loadMoreEnd();
            } else {
                adapter.addData(data.getSubjects());
                adapter.loadMoreComplete();
            }
        } else {
            refresh.setRefreshing(false);
            textView.setText(data.getTitle());
            adapter.setNewData(data.getSubjects());
        }


    }
}
