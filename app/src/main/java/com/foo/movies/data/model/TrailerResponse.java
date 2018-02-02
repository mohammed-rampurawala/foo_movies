package com.foo.movies.data.model;

import java.util.ArrayList;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class TrailerResponse {
    private long id;
    private ArrayList<Trailer> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Trailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<Trailer> results) {
        this.results = results;
    }
}
