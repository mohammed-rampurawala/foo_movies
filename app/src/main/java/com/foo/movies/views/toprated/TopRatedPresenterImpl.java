package com.foo.movies.views.toprated;

import com.foo.movies.data.Controller;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class TopRatedPresenterImpl<V extends ITopRatedMView> extends BasePresenter<V> implements ITopRatedPresenter<V> {

    @Inject
    public TopRatedPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }
}
