package com.foo.movies.views.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 *
 * @author mohammed.rampurawala
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    Context getMoviesContext();

    AppCompatActivity getMoviesActivity();

}