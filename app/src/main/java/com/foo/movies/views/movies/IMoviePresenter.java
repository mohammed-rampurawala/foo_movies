package com.foo.movies.views.movies;

import com.foo.movies.di.PerActivity;
import com.foo.movies.views.base.MvpPresenter;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

@PerActivity
public interface IMoviePresenter<V extends IMoviesView> extends MvpPresenter<V> {
}
