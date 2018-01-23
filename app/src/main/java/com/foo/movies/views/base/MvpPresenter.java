package com.foo.movies.views.base;


public interface MvpPresenter<V extends MvpView> {
    void onAttach(V view);

    void onDetach();
}
