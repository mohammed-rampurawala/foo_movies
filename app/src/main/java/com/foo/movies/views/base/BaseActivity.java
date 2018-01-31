package com.foo.movies.views.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.foo.movies.FooMoviesApp;
import com.foo.movies.R;
import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.di.component.DaggerActivityComponent;
import com.foo.movies.di.module.ActivityModule;
import com.foo.movies.utils.CommonUtils;

/**
 * Base class for the all the activities in app and handling hide/show loading events
 *
 * @author mohammed.rampurawala
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent((((FooMoviesApp) getApplicationContext())).getApplicationComponent())
                .build();
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getMoviesActivity());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public Context getMoviesContext() {
        return getApplicationContext();
    }

    @Override
    public AppCompatActivity getMoviesActivity() {
        return this;
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getMoviesActivity(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }
}