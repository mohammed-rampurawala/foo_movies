package com.foo.movies.views.detail;

import com.foo.movies.data.Controller;
import com.foo.movies.views.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mohammed.rampurawala on 2/2/2018.
 */

public class DetailPresenterImpl<V extends IDetailView> extends BasePresenter<V> implements IDetailPresenter<V> {

    @Inject
    public DetailPresenterImpl(Controller controller, CompositeDisposable compositeDisposable) {
        super(controller, compositeDisposable);
    }
}
