package com.foo.movies;

import android.app.Application;

import com.foo.movies.di.component.ApplicationComponent;
import com.foo.movies.di.component.DaggerApplicationComponent;
import com.foo.movies.di.module.ApplicationModule;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public class FooMoviesApp extends Application {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
