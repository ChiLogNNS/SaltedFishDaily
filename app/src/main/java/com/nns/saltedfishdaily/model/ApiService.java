package com.nns.saltedfishdaily.model;

import com.nns.saltedfishdaily.bean.News;
import com.nns.saltedfishdaily.bean.NewsDetail;
import com.nns.saltedfishdaily.bean.StartImage;
import com.nns.saltedfishdaily.utils.Constant;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET(Constant.URL_START_IMAGE)
    Call<StartImage> getStartImage();

    @GET(Constant.URL_NEWS_LATEST)
    Call<News> getNewsLatest();

    @GET(Constant.URL_NEWS_BEFORE)
    Call<News> getNewsBefore(@Path("date") String date);

    @GET(Constant.URL_NEWS_DETAIL)
    Call<NewsDetail> getNewsDetail(@Path("id") String id);
}
