package com.foo.movies.views.toprated;

import com.foo.movies.views.base.MvpPresenter;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public interface ITopRatedPresenter<V extends ITopRatedMView> extends MvpPresenter<V> {
    void fetchTopRatedMovies(int page);
}
