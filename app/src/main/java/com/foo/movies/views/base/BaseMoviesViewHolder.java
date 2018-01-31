package com.foo.movies.views.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.foo.movies.data.model.Movie;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public abstract class BaseMoviesViewHolder extends RecyclerView.ViewHolder {


    public BaseMoviesViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Movie model);
}
