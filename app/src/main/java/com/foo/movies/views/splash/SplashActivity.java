package com.foo.movies.views.splash;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.foo.movies.R;
import com.foo.movies.views.movies.MoviesActivity;
import com.foo.movies.views.base.BaseActivity;

import javax.inject.Inject;

/**
 * Startup class for app
 *
 * @author mohammed.rampurawala
 */

public class SplashActivity extends BaseActivity implements ISplashView {


    @Inject
    ISplashPresenter<ISplashView> mSplashPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        getActivityComponent().inject(this);

        mSplashPresenter.onAttach(this);
    }

    @Override
    public void openMoviesActivity() {
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void hideLoading() {
        super.hideLoading();
    }
}
