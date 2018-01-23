package com.foo.movies.data;

import android.content.Context;

import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.network.ApiHelper;
import com.foo.movies.data.network.MoviesApiService;
import com.foo.movies.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

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
        return mApiHelper.getApiConfiguration();
    }

}
