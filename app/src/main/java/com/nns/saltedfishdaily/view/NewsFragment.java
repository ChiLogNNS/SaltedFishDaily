package com.nns.saltedfishdaily.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nns.saltedfishdaily.R;
import com.nns.saltedfishdaily.base.BaseFragment;
import com.nns.saltedfishdaily.bean.News;
import com.nns.saltedfishdaily.presenter.NewsPresenter;

import java.util.List;

public class NewsFragment extends BaseFragment implements NewsContract.View, SwipeRefreshLayout.OnRefreshListener, NewsPresenter.NewsListAdapter.OnItemClickListener {
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recycler;
    private NewsPresenter.NewsListAdapter mAdapter;
    private NewsPresenter mPresenter;
    private LinearLayoutManager mManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initEvent();

        mPresenter = new NewsPresenter(this);
        mPresenter.getData();
    }

    private void initEvent() {
        swipeRefresh.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                    return;
                }
                int lastVisibleItemPosition = mManager.findLastVisibleItemPosition();
                int itemCount = mManager.getItemCount() - 1;
                Log.v("nnslog", itemCount + " onScrolled --> " + lastVisibleItemPosition);
                if (lastVisibleItemPosition >= itemCount) {
                    getMoreData();
                }
            }

        });
    }

    private void initView() {
        swipeRefresh = (SwipeRefreshLayout) getActivity().findViewById(R.id.swipe_refresh);
        recycler = (RecyclerView) getActivity().findViewById(R.id.recycler);
        mManager = new LinearLayoutManager(getContext().getApplicationContext());
        recycler.setLayoutManager(mManager);
        mAdapter = new NewsPresenter.NewsListAdapter();
        recycler.setAdapter(mAdapter);
    }

    @Override
    public void setNewsList(List<News.StoriesBean> data) {
        if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
            swipeRefresh.setRefreshing(false);
        }
        mAdapter.setData(data);
    }

    @Override
    public void setHeadView(List<News.TopStoriesBean> top_stories) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_news_viewpager_headview, null);
        mAdapter.setHeadView(view);
        ViewPager vpBanner = (ViewPager) view.findViewById(R.id.vp_banner);
        NewsPresenter.BannerAdapter adapter = new NewsPresenter.BannerAdapter();
        vpBanner.setAdapter(adapter);
        adapter.setData(top_stories);
    }

    public void getMoreData() {
        mPresenter.getMoreData();
    }

    @Override
    public void onRefresh() {
        mPresenter.getData();
    }

    @Override
    public void onItemClick(int position, long newsId) {
        //Snackbar.make(getView(), "点击了 " + position, Snackbar.LENGTH_LONG).show();
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_ID, newsId);
        startActivity(intent);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivImage;
        public View view;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            ivImage = (ImageView) view.findViewById(R.id.iv_image);
        }
    }
}
