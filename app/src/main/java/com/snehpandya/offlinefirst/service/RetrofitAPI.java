package com.snehpandya.offlinefirst.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sneh.pandya on 10/11/17.
 */

    //TODO: 03. Setup RetrofitAPI

public class RetrofitAPI {

    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    public RandomUserAPI mRandomUserAPI = mRetrofit.create(RandomUserAPI.class);
}
