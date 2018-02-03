package com.foo.movies.data.model;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public class TopRatedMovie extends Movie {
    public static final int TOP_RATED = 1;
    TopRatedMovie() {
        setMovieType(TOP_RATED);
    }
}
