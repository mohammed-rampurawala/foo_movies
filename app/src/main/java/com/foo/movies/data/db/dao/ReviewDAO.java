package com.foo.movies.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.foo.movies.data.model.Review;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

@Dao
public interface ReviewDAO {
    @Query("SELECT * from review where id = :id")
    Flowable<List<Review>> getReviewsById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovieReviews(List<Review> review);
}
