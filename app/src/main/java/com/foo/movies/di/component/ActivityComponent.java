package com.foo.movies.di.component;


import com.foo.movies.di.PerActivity;
import com.foo.movies.di.module.ActivityModule;
import com.foo.movies.views.movies.MoviesActivity;
import com.foo.movies.views.popular.PopularFragment;
import com.foo.movies.views.search.SearchActivity;
import com.foo.movies.views.splash.SplashActivity;
import com.foo.movies.views.toprated.TopRatedFragment;

import dagger.Component;

/**
 * Activity component holder to inject activity, Fragment to be accessed in activity level.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(SearchActivity searchActivity);

    void inject(PopularFragment popularFragment);

    void inject(MoviesActivity moviesActivity);

    void inject(TopRatedFragment topRatedFragment);
}
