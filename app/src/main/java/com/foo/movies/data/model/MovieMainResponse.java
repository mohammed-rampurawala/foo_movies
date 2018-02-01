package com.foo.movies.data.model;

import java.util.ArrayList;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

public class MovieMainResponse {
    private int page;

    private int totalResults;

    private int totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
