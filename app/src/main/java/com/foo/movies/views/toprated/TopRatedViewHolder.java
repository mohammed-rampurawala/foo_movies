package com.foo.movies.views.toprated;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.foo.movies.MoviesConstants;
import com.foo.movies.R;
import com.foo.movies.data.model.MovieModel;
import com.foo.movies.views.base.BaseMoviesViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

public class TopRatedViewHolder extends BaseMoviesViewHolder {

    private final String baseUrl;

    @BindView(R.id.movie_image)
    ImageView movieImage;


    public TopRatedViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        SharedPreferences sharedPreferences = itemView.getContext().getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
        baseUrl = sharedPreferences.getString(MoviesConstants.POSTER_URL, "");

    }

    @Override
    public void bind(MovieModel model) {
        Glide.with(movieImage.getContext())
                .load(baseUrl + model.getPosterPath())
                .into(movieImage);
    }
}
