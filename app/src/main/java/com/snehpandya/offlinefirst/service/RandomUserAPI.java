package com.snehpandya.offlinefirst.service;

import com.snehpandya.offlinefirst.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by sneh.pandya on 10/11/17.
 */

public interface RandomUserAPI {

    @GET("/api/")
    Observable<Response> getRandomUsers(@Query("results") int results, @Query("exc") String exclude, @Query("seed") String seed);
}
