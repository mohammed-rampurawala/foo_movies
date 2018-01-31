package com.foo.movies.data.network;

import com.foo.movies.BuildConfig;
import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.MovieMainResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    private MoviesApiService moviesApiService;

    @Inject
    public AppApiHelper(Retrofit ref) {
        moviesApiService = ref.create(MoviesApiService.class);
    }

    @Override
    public Observable<ConfigurationResponse> getApiConfiguration() {
        return moviesApiService.getConfiguration(BuildConfig.API_KEY);
    }

    @Override
    public Observable<MovieMainResponse> getPopularMovies(int page) {
        return moviesApiService.getPopularMovies(BuildConfig.API_KEY, page);
    }

    @Override
    public Observable<MovieMainResponse> getTopRatedMovies(int page) {
        return moviesApiService.getTopRatedMovies(BuildConfig.API_KEY, page);
    }

    @Override
    public Observable<MovieMainResponse> getSearchedMovies(String searchedQuery) {
        return null;
    }

}
