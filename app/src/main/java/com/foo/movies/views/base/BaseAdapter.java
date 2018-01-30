package com.foo.movies.views.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseMoviesViewHolder> {

    protected LayoutInflater inflater;

    public BaseAdapter(BaseActivity activity) {
        inflater = LayoutInflater.from(activity);
    }

    public LayoutInflater getInflater() {
        return inflater;
    }
}
