package com.foo.movies.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

@Dao
public interface TrailerDao {
    @Query("SELECT * from trailer where id = :id")
    Flowable<List<Trailer>> getTrailerById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieTrailers(List<Trailer> trailers);
}
