package com.foo.movies.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class TrailerResponse {
    private long id;
    private List<Trailer> results;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
