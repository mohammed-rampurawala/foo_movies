package com.foo.movies.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.foo.movies.data.model.Movie;

import java.util.List;

/**
 * Created by mohammed.rampurawala on 1/31/2018.
 */

@Dao
public interface MoviesDAO {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insertAll(Movie... movies);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movie WHERE title LIKE :searchQuery")
    List<Movie> searchMovie(String searchQuery);

}
