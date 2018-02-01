package com.foo.movies.data.network;


import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.MovieMainResponse;
import com.foo.movies.data.model.PopularMovieResponse;
import com.foo.movies.data.model.TopRatedMovieResponse;

import io.reactivex.Observable;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public interface IApiHelper {
    Observable<ConfigurationResponse> getApiConfiguration();

    Observable<PopularMovieResponse> getPopularMovies(int page);

    Observable<TopRatedMovieResponse> getTopRatedMovies(int page);

    Observable<TopRatedMovieResponse> getReviewsForMovie(long movieId);
}
