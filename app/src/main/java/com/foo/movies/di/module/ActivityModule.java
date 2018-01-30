package com.foo.movies.di.module;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.di.ActivityContext;
import com.foo.movies.di.PerActivity;
import com.foo.movies.views.movies.IMoviePresenter;
import com.foo.movies.views.movies.IMoviesView;
import com.foo.movies.views.movies.MoviesPresenterImpl;
import com.foo.movies.views.splash.ISplashView;
import com.foo.movies.views.splash.ISplashPresenter;
import com.foo.movies.views.splash.SplashPresenterImpl;
import com.foo.movies.views.toprated.ITopRatedMView;
import com.foo.movies.views.toprated.ITopRatedPresenter;
import com.foo.movies.views.toprated.TopRatedPresenterImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Activity Module for getting presenter for respective activity rather than creating instance on activity level.
 *
 * @author mohammed.rampurawala
 */

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerActivity
    ISplashPresenter<ISplashView> provideSplashPresenter(SplashPresenterImpl<ISplashView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IMoviePresenter<IMoviesView> provideMoviePresenter(MoviesPresenterImpl<IMoviesView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ITopRatedPresenter<ITopRatedMView> provideTopRatedPresenter(TopRatedPresenterImpl<ITopRatedMView> presenter) {
        return presenter;
    }
}
