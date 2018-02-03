package com.foo.movies.data.db;


import android.arch.persistence.room.RoomDatabase;

import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.ReviewResponse;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.data.model.TrailerResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by mohammed.rampurawala on 2/1/2018.
 */

@Singleton
public class DatabaseHelper implements IDBHelper {

    private MoviesDatabase database;

    @Inject
    DatabaseHelper(RoomDatabase database) {
        this.database = (MoviesDatabase) database;
    }

    @Override
    public Flowable<List<Movie>> getSearchMovies(String query) {
        return database.moviesDAO().searchMovies("%" + query + "%");
    }

    @Override
    public void insertMovies(ArrayList<? extends Movie> movies) {
        database.moviesDAO().insertAll(movies);
    }

    @Override
    public void insertTrailers(List<Trailer> trailers) {
        database.trailerDao().insertMovieTrailers(trailers);
    }

    @Override
    public void insertReviews(List<Review> reviews) {
        database.reviewDAO().insertMovieReviews(reviews);
    }

    @Override
    public Observable<TrailerResponse> getMovieTrailers(final long movieId) {
        return Observable.fromCallable(new Callable<TrailerResponse>() {
            @Override
            public TrailerResponse call() throws Exception {
                List<Trailer> trailers = database.trailerDao().getTrailerById(movieId).blockingFirst();

                TrailerResponse response = new TrailerResponse();
                response.setResults(trailers);
                return response;
            }
        });
    }

    @Override
    public Observable<ReviewResponse> getMovieReviews(final long movieId) {
        return Observable.fromCallable(new Callable<ReviewResponse>() {
            @Override
            public ReviewResponse call() throws Exception {
                List<Review> reviews = database.reviewDAO().getReviewsById(movieId).blockingFirst();
                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setResults(reviews);
                return reviewResponse;
            }
        });
    }
}
