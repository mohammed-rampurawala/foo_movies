package com.foo.movies.data.network;


import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.MovieMainResponse;

import io.reactivex.Observable;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public interface ApiHelper {
    Observable<ConfigurationResponse> getApiConfiguration();

    Observable<MovieMainResponse> getPopularMovies(int page);

    Observable<MovieMainResponse> getTopRatedMovies(int page);

    Observable<MovieMainResponse> getSearchedMovies(String searchedQuery);
}
