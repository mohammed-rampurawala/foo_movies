package com.foo.movies.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.foo.movies.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

@Dao
public interface MoviesDAO {
    @Query("SELECT * FROM movie")
    Flowable<List<Movie>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(ArrayList<? extends Movie> movies);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movie WHERE lower(title) LIKE :searchQuery OR lower(original_title) LIKE :searchQuery")
    Flowable<List<Movie>> searchMovies(String searchQuery);
}
