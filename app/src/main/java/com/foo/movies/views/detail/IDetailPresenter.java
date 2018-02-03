package com.foo.movies.views.detail;

import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.MvpPresenter;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public interface IDetailPresenter<V extends IDetailView> extends MvpPresenter<V> {
    void setMovie(Movie movie);

    String getMovieTitle();

    String getBackdropPath();

    String getPosterPath();

    String getReleaseDate();

    String getOverview();

    double getVoteAverage();

    void fetchTrailers();

    void fetchReviews();
}
