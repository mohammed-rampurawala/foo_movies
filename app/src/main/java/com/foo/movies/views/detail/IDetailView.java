package com.foo.movies.views.detail;

import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.views.base.MvpView;

import java.util.List;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public interface IDetailView extends MvpView {
    void addReviewsToUI(List<Review> reviewList);

    void addTrailersToUI(List<Trailer> reviewList);
}
