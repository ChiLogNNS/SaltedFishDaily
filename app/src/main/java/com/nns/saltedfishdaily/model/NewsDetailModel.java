package com.nns.saltedfishdaily.model;

import com.nns.saltedfishdaily.bean.NewsDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailModel extends BaseModel<NewsDetail>  {

    public void getNewsDetail(String nId) {
        Call<NewsDetail> call = NetworkManager.getInstance().getNewsDetail(nId);
        call.enqueue(new Callback<NewsDetail>() {
            @Override
            public void onResponse(Call<NewsDetail> call, Response<NewsDetail> response) {
                onRequestSucceedCallback(response.body());
            }

            @Override
            public void onFailure(Call<NewsDetail> call, Throwable t) {

            }
        });
    }
}
