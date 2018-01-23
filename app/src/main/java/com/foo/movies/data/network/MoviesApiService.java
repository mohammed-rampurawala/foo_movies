package com.foo.movies.data.network;

import com.foo.movies.data.model.ConfigurationResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public interface MoviesApiService {
    @GET("configuration")
    Observable<ConfigurationResponse> getConfiguration(@Query("api_key") String apiKey);
}
