package com.foo.movies.data.db;

import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.ReviewResponse;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.data.model.TrailerResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

public interface IDBHelper {
    Flowable<List<Movie>> getSearchMovies(String query);

    void insertMovies(ArrayList<? extends Movie> movies);

    void insertTrailers(List<Trailer> trailers);

    void insertReviews(List<Review> reviews);

    Observable<TrailerResponse> getMovieTrailers(long movieId);

    Observable<ReviewResponse> getMovieReviews(long movieId);
}
