package com.foo.movies.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.foo.movies.data.db.dao.MoviesDAO;
import com.foo.movies.data.model.Movie;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

@Database(entities = {Movie.class}, version = 1)
public abstract class MoviesDatabase extends RoomDatabase {
    public abstract MoviesDAO moviesDAO();
}
