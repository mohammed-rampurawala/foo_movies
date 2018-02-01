package com.foo.movies.views.search;

import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.MvpView;

import java.util.List;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

public interface ISearchView extends MvpView {
    void populateMovies(List<Movie> movies);

    void onMoviesFound();
}
