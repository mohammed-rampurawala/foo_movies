package com.foo.movies.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class ReviewResponse {
    private long id;
    private long page;
    private List<Review> results;
    private long totalPages;
    private long totalResults;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Review> getResults() {
        return results;
    }

    public void setResults(List<Review> results) {
        this.results = results;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }
}
