package com.foo.movies.views.popular;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.MoviesConstants;
import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.listener.ICallback;
import com.foo.movies.views.base.BaseMoviesViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

public class PopularViewHolder extends BaseMoviesViewHolder {

    private final String baseUrl;

    private final ICallback callback;

    View itemView;

    @BindView(R.id.movie_image)
    ImageView movieImage;

    @BindView(R.id.title)
    TextView movieTitle;


    private Movie movie;


    public PopularViewHolder(View itemView, ICallback callback) {
        super(itemView);
        this.itemView = itemView;
        this.callback = callback;
        ButterKnife.bind(this, itemView);
        SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
        baseUrl = sharedPreferences.getString(MoviesConstants.POSTER_URL, "");
    }

    @OnClick(R.id.movie_card)
    public void onMovieClick() {
        if (callback != null) {
            callback.onItemSelected(movie, itemView);
        }
    }

    @Override
    public void bind(Movie movie) {
        this.movie = movie;
        Glide.with(movieImage.getContext())
                .load(baseUrl + movie.getPosterPath())
                .into(movieImage);
        movieTitle.setText(movie.getTitle());
    }
}
