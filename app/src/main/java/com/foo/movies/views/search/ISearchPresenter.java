package com.foo.movies.views.search;

import com.foo.movies.di.PerActivity;
import com.foo.movies.views.base.MvpPresenter;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

@PerActivity
public interface ISearchPresenter<V extends ISearchView> extends MvpPresenter<V> {
    void getMovies(String query);
}
