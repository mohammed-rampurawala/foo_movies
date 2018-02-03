package com.foo.movies.data.model;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public class PopularMovie extends Movie {
    public static final int POPULAR = 2;

    PopularMovie() {
        setMovieType(POPULAR);
    }
}
