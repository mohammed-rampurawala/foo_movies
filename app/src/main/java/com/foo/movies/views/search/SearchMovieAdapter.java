package com.foo.movies.views.search;

import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.movies.MoviesAdapter;

import java.util.List;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class SearchMovieAdapter extends MoviesAdapter {
    public SearchMovieAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public void addItems(List<? extends Movie> results) {
        moviesList.clear();
        moviesList.addAll(results);
        notifyDataSetChanged();
    }
}
