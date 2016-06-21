package com.nns.saltedfishdaily.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.nns.saltedfishdaily.R;
import com.nns.saltedfishdaily.bean.NewsDetail;
import com.nns.saltedfishdaily.presenter.NewsDetailPresenter;

public class NewsDetailActivity extends AppCompatActivity implements NewsDetailContract.View {
    private Toolbar toolbar;
    private WebView webView;

    public static final String EXTRA_NEWS_ID = "EXTRA_NEWS_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        long newsId = getIntent().getLongExtra(EXTRA_NEWS_ID, -1);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);

        NewsDetailPresenter presenter = new NewsDetailPresenter(this);
        presenter.getData(String.valueOf(newsId));
    }

    @Override
    public void setData(NewsDetail bean) {
        getSupportActionBar().setTitle(bean.getTitle());
//        webView.loadUrl(bean.getBody());
        System.out.println(bean.getBody());
    }
}
