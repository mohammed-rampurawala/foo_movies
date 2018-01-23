package com.foo.movies.views.base;


abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private static final String TAG = "BasePresenter";

    private V mvpView = null;

    //Default Constructor
    public BasePresenter() {

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
}
