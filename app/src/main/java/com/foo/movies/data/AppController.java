package com.foo.movies.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.foo.movies.MoviesConstants;
import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.MovieMainResponse;
import com.foo.movies.data.network.ApiHelper;
import com.foo.movies.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Entry point for all the db and network calls
 *
 * @author mohammed.rampurawala
 */

@Singleton
public class AppController implements Controller {

    private static final String TAG = "AppController";

    /**
     * Application context
     */
    private final Context mContext;

    /**
     * API Helper for network calls
     */
    private final ApiHelper mApiHelper;

    @Inject
    public AppController(@ApplicationContext Context context, ApiHelper apiHelper) {
        mContext = context;
        mApiHelper = apiHelper;
    }

    @Override
    public Observable<ConfigurationResponse> getApiConfiguration() {
        return mApiHelper.getApiConfiguration().map(new Function<ConfigurationResponse, ConfigurationResponse>() {
            @Override
            public ConfigurationResponse apply(ConfigurationResponse configurationResponse) throws Exception {
                if (configurationResponse == null || configurationResponse.getImages() == null) {
                    return configurationResponse;
                }
                //Store the url in Preferences
                SharedPreferences sharedPreferences = mContext.getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                ConfigurationResponse.Images images = configurationResponse.getImages();
                edit.putString(MoviesConstants.LOGO_URL, images.getBaseUrl() + images.getLogoSizes().get(images.getLogoSizes().size() - 1));
                edit.putString(MoviesConstants.POSTER_URL, images.getBaseUrl() + images.getPosterSizes().get(images.getPosterSizes().size() - 1));
                edit.apply();

                return configurationResponse;
            }
        });
    }

    @Override
    public Observable<MovieMainResponse> getPopularMovies(int page) {
        return mApiHelper.getPopularMovies(page);
    }

    @Override
    public Observable<MovieMainResponse> getTopRatedMovies(int page) {
        return mApiHelper.getTopRatedMovies(page);
    }

    @Override
    public Observable<MovieMainResponse> getSearchedMovies(String searchedQuery) {
        return null;
    }

}
