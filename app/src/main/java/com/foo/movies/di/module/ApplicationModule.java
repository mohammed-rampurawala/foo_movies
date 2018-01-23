package com.foo.movies.di.module;

import android.app.Application;
import android.content.Context;

import com.foo.movies.di.ApplicationContext;

import dagger.Module;
import dagger.Provides;

/**
 * @author mohammed.rampurawala
 */

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }
}
