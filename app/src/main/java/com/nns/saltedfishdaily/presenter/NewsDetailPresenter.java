package com.nns.saltedfishdaily.presenter;

import com.nns.saltedfishdaily.bean.NewsDetail;
import com.nns.saltedfishdaily.model.BaseModel;
import com.nns.saltedfishdaily.model.NewsDetailModel;
import com.nns.saltedfishdaily.view.NewsDetailActivity;
import com.nns.saltedfishdaily.view.NewsDetailContract;

public class NewsDetailPresenter implements BaseModel.OnRequestListener<NewsDetail>, NewsDetailContract.Presenter {
    private NewsDetailActivity mView;
    private final NewsDetailModel mModel;

    public NewsDetailPresenter(NewsDetailActivity view) {
        mView = view;
        mModel = new NewsDetailModel();
        mModel.setListener(this);
    }

    @Override
    public void getData(String nId) {
        mModel.getNewsDetail(nId);

    }

    @Override
    public void onSucceed(NewsDetail bean) {
        mView.setData(bean);
    }

    @Override
    public void onFailed(String msg) {

    }
}
