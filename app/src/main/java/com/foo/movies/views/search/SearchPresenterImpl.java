package com.foo.movies.views.search;

import com.foo.movies.data.Controller;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mohammed.rampurawala on 1/24/2018.
 */

public class SearchPresenterImpl<V extends ISearchView> extends BasePresenter<V> implements ISearchPresenter<V> {

    @Inject
    public SearchPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }
}
