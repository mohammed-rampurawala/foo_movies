package com.foo.movies;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.crashlytics.android.Crashlytics;
import com.foo.movies.data.Controller;
import com.foo.movies.data.db.MoviesDatabase;
import com.foo.movies.di.component.ApplicationComponent;
import com.foo.movies.di.component.DaggerApplicationComponent;
import com.foo.movies.di.module.ApplicationModule;
import com.foo.movies.utils.AppLogger;

import javax.inject.Inject;

/**
 * Created by mohammed.rampurawala on 1/23/2018.
 */

public class FooMoviesApp extends Application {

    @Inject
    Controller controller;

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //Crash Test
//        Crashlytics.getInstance().crash();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        AppLogger.init();

        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
