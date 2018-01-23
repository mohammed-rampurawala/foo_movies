package com.foo.movies.views.base;


import com.foo.movies.data.Controller;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private static final String TAG = "BasePresenter";

    private Controller controller;

    private CompositeDisposable compositeDisposable;

    private V mvpView = null;

    public BasePresenter(Controller controller, CompositeDisposable compositeDisposable) {
        this.controller = controller;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        this.mvpView = view;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public V getMvpView() {
        return mvpView;
    }

    public Controller getController() {
        return controller;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
