package com.nns.saltedfishdaily.model;

import com.nns.saltedfishdaily.bean.StartImage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashModel extends BaseModel<StartImage> {

    public void getStartImage() {
        Call<StartImage> call = NetworkManager.getInstance().getStartImage();
        call.enqueue(new Callback<StartImage>() {
            @Override
            public void onResponse(Call<StartImage> call, Response<StartImage> response) {
                onRequestSucceedCallback(response.body());
            }

            @Override
            public void onFailure(Call<StartImage> call, Throwable t) {
                onRequestFailedCallback(t.getLocalizedMessage());
            }
        });
    }
}
