package com.foo.movies.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.foo.movies.data.db.dao.MoviesDAO;
import com.foo.movies.data.db.dao.ReviewDAO;
import com.foo.movies.data.db.dao.TrailerDao;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

@Database(entities = {Movie.class, Review.class, Trailer.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MoviesDAO moviesDAO();

    public abstract ReviewDAO reviewDAO();

    public abstract TrailerDao trailerDao();
}
