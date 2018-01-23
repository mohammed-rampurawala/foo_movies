package com.foo.movies.views.splash;


import com.foo.movies.di.PerActivity;
import com.foo.movies.views.base.MvpPresenter;

/**
 */

@PerActivity
public interface ISplashPresenter<V extends ISplashView> extends MvpPresenter<V> {

}
