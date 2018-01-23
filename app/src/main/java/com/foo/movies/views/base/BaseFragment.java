package com.foo.movies.views.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.di.component.ActivityComponent;

/**
 * Created by mohammed.rampurawala on 7/5/2017.
 */

abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public Context getMoviesContext() {
        return mActivity.getApplicationContext();
    }

    @Override
    public AppCompatActivity getMoviesActivity() {
        return mActivity;
    }

    protected boolean onBackPressed() {
        return false;
    }

    public ActivityComponent getActivityComponent() {
        return ((BaseActivity) mActivity).getActivityComponent();
    }


}
