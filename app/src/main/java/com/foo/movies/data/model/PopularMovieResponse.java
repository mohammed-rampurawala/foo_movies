package com.foo.movies.data.model;

import java.util.ArrayList;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public class PopularMovieResponse extends MovieMainResponse {
    private ArrayList<? extends Movie> results;

    public ArrayList<? extends Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<? extends Movie> results) {
        this.results = results;
    }
}
