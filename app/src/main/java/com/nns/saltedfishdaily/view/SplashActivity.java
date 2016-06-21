package com.nns.saltedfishdaily.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nns.saltedfishdaily.R;
import com.nns.saltedfishdaily.base.BaseActivity;
import com.nns.saltedfishdaily.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity implements SplashContract.View {
    private ImageView ivSplash;
    private TextView tvAuthor;
    private SplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ivSplash = (ImageView) findViewById(R.id.iv_splash);
        tvAuthor = (TextView) findViewById(R.id.tv_author);

        mPresenter = new SplashPresenter(this);
        mPresenter.getStartImage();

        tvAuthor.postDelayed(new Runnable() {
            @Override
            public void run() {
                goToMain();
            }
        }, 4000);
    }

    @Override
    public void showImage(String url) {
        Glide.with(getApplicationContext()).load(url).centerCrop().into(ivSplash);
    }

    @Override
    public void showAuthor(String author) {
        tvAuthor.setText(author);
    }

    @Override
    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
        mPresenter.onDestory();
        finish();
    }
}
