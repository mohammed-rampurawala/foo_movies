package com.foo.movies.views.popular;

import com.foo.movies.views.base.MvpPresenter;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public interface IPopularPresenter<V extends IPopularView> extends MvpPresenter<V> {
    void fetchPopularMovies(int page);
}
