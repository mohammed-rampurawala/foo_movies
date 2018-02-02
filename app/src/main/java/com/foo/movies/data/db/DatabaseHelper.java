package com.foo.movies.data.db;


import android.arch.persistence.room.RoomDatabase;

import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

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
}
