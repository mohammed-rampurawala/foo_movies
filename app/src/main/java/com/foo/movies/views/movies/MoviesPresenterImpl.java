package com.foo.movies.views.movies;

import com.foo.movies.data.Controller;
import com.foo.movies.data.model.MovieMainResponse;
import com.foo.movies.utils.AppLogger;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

public class MoviesPresenterImpl<V extends IMoviesMovie> extends BasePresenter<V> implements IMoviePresenter<V> {

    @Inject
    public MoviesPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }

    @Override
    public void fetchPopularMovies(int page) {
        getCompositeDisposable().add(getController()
                .getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieMainResponse>() {
                    @Override
                    public void accept(MovieMainResponse movieMainResponse) throws Exception {
                        AppLogger.d(movieMainResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        AppLogger.d(throwable, "Exception Occured while fetching the popular movies");
                    }
                }));
    }
}
