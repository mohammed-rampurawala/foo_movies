package com.foo.movies.views.detail;

import com.foo.movies.data.Controller;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.ReviewResponse;
import com.foo.movies.data.model.TopRatedMovieResponse;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class DetailPresenterImpl<V extends IDetailView> extends BasePresenter<V> implements IDetailPresenter<V> {

    private Movie movie;

    @Inject
    public DetailPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String getMovieTitle() {
        return movie.getTitle();
    }

    @Override
    public String getBackdropPath() {
        return movie.getBackdropPath();
    }

    @Override
    public String getPosterPath() {
        return movie.getPosterPath();
    }

    @Override
    public String getReleaseDate() {
        return movie.getReleaseDate();
    }

    @Override
    public String getOverview() {
        return movie.getOverview();
    }

    @Override
    public double getVoteAverage() {
        return movie.getVoteAverage();
    }

    @Override
    public void fetchTrailers() {
    }

    @Override
    public void fetchReviews() {
        getCompositeDisposable().add(
                getController()
                        .getReviewsForMovie(movie.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ReviewResponse>() {
                            @Override
                            public void accept(ReviewResponse reviewResponse) throws Exception {
                                getMvpView().addReviewsToUI(reviewResponse.getResults());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().showErrorMessage();
                            }
                        }));
    }
}
