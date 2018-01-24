package com.foo.movies.data.network;

import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.MovieMainResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public interface MoviesApiService {
    @GET("configuration")
    Observable<ConfigurationResponse> getConfiguration(@Query("api_key") String apiKey);


    @GET("movie/popular")
    Observable<MovieMainResponse> getPopularMovies(@Query("api_key") String apiKey,
                                                   @Query("language") String language,
                                                   @Query("page") int page);

    @GET("movie/top_rated")
    Observable<MovieMainResponse> getTopRatedMovies(@Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("page") int page);

    @GET("search/movie")
    Observable<MovieMainResponse> getSearchKeyword(@Query("api_key") String apiKey,
                                                   @Query("language") String language,
                                                   @Query("query") String keyword,
                                                   @Query("page") int page,
                                                   @Query("include_adult") boolean includeAdult);
}
