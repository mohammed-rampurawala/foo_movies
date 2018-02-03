package com.foo.movies.views.movies;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.listener.ICallback;
import com.foo.movies.utils.AppLogger;
import com.foo.movies.utils.MoviesConstants;
import com.foo.movies.views.base.BaseMoviesViewHolder;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class MoviesViewHolder extends BaseMoviesViewHolder {
    private final String baseUrl;

    private final ICallback callback;

    View itemView;

    @BindView(R.id.movie_image)
    ImageView movieImage;

    private Movie movie;


    public MoviesViewHolder(View itemView, ICallback callback) {
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
        try {
            Glide.with(movieImage.getContext())
                    .load(new URL(baseUrl + movie.getPosterPath()))
                    .transition(new GenericTransitionOptions<Drawable>())
                    .into(movieImage);
        } catch (MalformedURLException e) {
            AppLogger.e(e,"Exception whle loading glide images");
        }
    }
}
