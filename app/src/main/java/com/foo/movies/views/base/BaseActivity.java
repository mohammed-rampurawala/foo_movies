package com.foo.movies.views.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.FooMoviesApp;
import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.di.component.DaggerActivityComponent;

/**
 * Base class for the all the activities in app and handling hide/show loading events
 *
 * @author mohammed.rampurawala
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent((((FooMoviesApp) getApplicationContext())).getApplicationComponent())
                .build();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getMoviesContext() {
        return getApplicationContext();
    }

    @Override
    public AppCompatActivity getMoviesActivity() {
        return this;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}