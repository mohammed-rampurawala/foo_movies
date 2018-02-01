package com.foo.movies.views.popular;

import com.foo.movies.data.Controller;
import com.foo.movies.data.model.MovieMainResponse;
import com.foo.movies.data.model.PopularMovieResponse;
import com.foo.movies.utils.AppLogger;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class PopularPresenterImpl<V extends IPopularView> extends BasePresenter<V> implements IPopularPresenter<V> {

    private int page = 1;

    private boolean isLoading = false;

    @Inject
    public PopularPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }

    @Override
    public void fetchPopularMovies(int page) {
        isLoading = true;
        getMvpView().showLoading();
        getCompositeDisposable().add(getController()
                .getPopularMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PopularMovieResponse>() {
                    @Override
                    public void accept(PopularMovieResponse movieMainResponse) throws Exception {
                        isLoading = false;
                        getMvpView().hideLoading();
                        getMvpView().refreshMovieList(movieMainResponse.getResults());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isLoading = false;
                        getMvpView().hideLoading();
                        getMvpView().showErrorMessage();
                        getMvpView().showEmptyView();
                        AppLogger.d(throwable, "Exception Occured while fetching the popular movies");
                    }
                }));
    }

    @Override
    public void loadNextPage() {
        if (isLoading) {
            return;
        }
        page++;
        fetchPopularMovies(page);
    }
}
