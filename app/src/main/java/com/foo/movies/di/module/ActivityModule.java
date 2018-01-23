package com.foo.movies.di.module;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.di.ActivityContext;
import com.foo.movies.di.PerActivity;

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
}
