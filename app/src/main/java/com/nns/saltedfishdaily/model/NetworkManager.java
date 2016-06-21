package com.nns.saltedfishdaily.model;

import com.nns.saltedfishdaily.utils.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private NetworkManager() {
    }

    public static ApiService getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.URL_BASE).addConverterFactory(GsonConverterFactory.create()).build();
        private static final ApiService INSTANCE = retrofit.create(ApiService.class);
    }
}


