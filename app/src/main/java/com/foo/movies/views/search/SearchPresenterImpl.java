package com.foo.movies.views.search;

import com.foo.movies.data.Controller;
import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

public class SearchPresenterImpl<V extends ISearchView> extends BasePresenter<V> implements ISearchPresenter<V> {

    @Inject
    public SearchPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }

    @Override
    public void getMovies(String query) {
        getCompositeDisposable().add(
                getController()
                        .getSearchMovies(query)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Movie>>() {
                            @Override
                            public void accept(List<Movie> movies) throws Exception {
                                if (movies == null || movies.size() == 0) {
                                    getMvpView().onMoviesFound();
                                } else {
                                    getMvpView().populateMovies(movies);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                getMvpView().showErrorMessage();
                            }
                        })
        );
    }
}
