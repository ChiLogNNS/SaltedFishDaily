package com.nns.saltedfishdaily.presenter;

import com.nns.saltedfishdaily.bean.StartImage;
import com.nns.saltedfishdaily.model.SplashModel;
import com.nns.saltedfishdaily.view.SplashContract;

public class SplashPresenter implements SplashContract.Presenter, SplashModel.OnRequestListener<StartImage> {
    private SplashContract.View mView;
    private final SplashModel mModel;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
        mModel = new SplashModel();
        mModel.setListener(this);
    }

    @Override
    public void getStartImage() {
        mModel.getStartImage();
    }

    @Override
    public void onSucceed(StartImage bean) {
        mView.showImage(bean.getImg());
        mView.showAuthor(bean.getText());
    }

    @Override
    public void onFailed(String str) {
    }

    public void onDestory() {

    }
}
