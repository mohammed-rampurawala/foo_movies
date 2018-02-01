package com.foo.movies.listener;

import android.view.View;

import com.foo.movies.data.model.Movie;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public interface ICallback {
    /**
     * callback for when an item has been selected.
     */
    void onItemSelected(Movie movie, View view);
}
