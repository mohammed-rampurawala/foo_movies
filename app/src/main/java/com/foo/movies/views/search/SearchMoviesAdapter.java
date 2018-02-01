package com.foo.movies.views.search;

import android.view.ViewGroup;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseAdapter;
import com.foo.movies.views.base.BaseMoviesViewHolder;
import com.foo.movies.views.popular.PopularViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class SearchMoviesAdapter extends BaseAdapter {


    private ArrayList<Movie> moviesList = new ArrayList<>();

    public SearchMoviesAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public BaseMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PopularViewHolder(getInflater().inflate(R.layout.movies_item, parent, false),null);
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

    public void addItems(List<Movie> results) {
        moviesList.clear();
        moviesList.addAll(results);
        notifyDataSetChanged();
    }
}
