package com.foo.movies.di.component;


import android.app.Application;
import android.content.Context;

import com.foo.movies.FooMoviesApp;
import com.foo.movies.di.ApplicationContext;
import com.foo.movies.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component for application level object
 *
 * @author mohammed.rampurawala
 */

@Singleton
@Component(modules = {ApplicationModule.class,})
public interface ApplicationComponent {

    void inject(FooMoviesApp app);

    @ApplicationContext
    Context context();

    Application application();
}