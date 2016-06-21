package com.nns.saltedfishdaily.model;

import com.nns.saltedfishdaily.bean.News;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsModel extends BaseModel<News> {

    public void getNewsLatest() {
        Call<News> call = NetworkManager.getInstance().getNewsLatest();
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                onRequestSucceedCallback(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }

    public void getMoreData(String date) {
        Call<News> call = NetworkManager.getInstance().getNewsBefore(date);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                onRequestSucceedCallback(response.body());
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {

            }
        });
    }
}
