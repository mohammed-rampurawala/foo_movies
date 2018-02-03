package com.foo.movies.views.movies;

import android.view.ViewGroup;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.listener.ICallback;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseAdapter;
import com.foo.movies.views.base.BaseMoviesViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class MoviesAdapter extends BaseAdapter {

    protected ArrayList<Movie> moviesList = new ArrayList<>();

    private ICallback callback;

    public MoviesAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public BaseMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoviesViewHolder(getInflater().inflate(R.layout.movies_item, parent, false), callback);
    }

    @Override
    public void onBindViewHolder(BaseMoviesViewHolder holder, int position) {
        holder.bind(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        if (moviesList == null || moviesList.size() == 0) return 0;
        return moviesList.size();
    }

    public void addItems(List<? extends Movie> results) {
        if (results == null || results.size() == 0) return;

        int firstPosition = moviesList.size() == 0 ? 0 : moviesList.size() - 1;
        moviesList.addAll(results);
        notifyItemRangeChanged(firstPosition, results.size());
    }

    public void setOnItemClickListener(ICallback callback) {
        this.callback = callback;
    }
}
