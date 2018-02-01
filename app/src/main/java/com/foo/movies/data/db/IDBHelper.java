package com.foo.movies.data.db;

import com.foo.movies.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public interface IDBHelper {
    Flowable<List<Movie>> getSearchMovies(String query);

    void insertMovies(ArrayList<? extends Movie> movies);
}
