package com.foo.movies.di.module;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.di.ActivityContext;
import com.foo.movies.di.PerActivity;
import com.foo.movies.views.detail.DetailPresenterImpl;
import com.foo.movies.views.detail.IDetailPresenter;
import com.foo.movies.views.detail.IDetailView;
import com.foo.movies.views.movies.IMoviePresenter;
import com.foo.movies.views.movies.IMoviesView;
import com.foo.movies.views.movies.MoviesPresenterImpl;
import com.foo.movies.views.popular.IPopularPresenter;
import com.foo.movies.views.popular.IPopularView;
import com.foo.movies.views.popular.PopularPresenterImpl;
import com.foo.movies.views.search.ISearchPresenter;
import com.foo.movies.views.search.ISearchView;
import com.foo.movies.views.search.SearchPresenterImpl;
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

    @Provides
    @PerActivity
    IPopularPresenter<IPopularView> providePopularPresenter(PopularPresenterImpl<IPopularView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ISearchPresenter<ISearchView> provideSearchPresenter(SearchPresenterImpl<ISearchView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    IDetailPresenter<IDetailView> provideDetailPresenter(DetailPresenterImpl<IDetailView> presenter) {
        return presenter;
    }
}
