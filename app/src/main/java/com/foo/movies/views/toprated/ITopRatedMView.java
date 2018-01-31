package com.foo.movies.views.toprated;

import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.MvpView;

import java.util.ArrayList;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public interface ITopRatedMView extends MvpView {
    void refreshMovieList(ArrayList<Movie> results);

    void showEmptyView();
}
