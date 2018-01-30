package com.foo.movies.views.base;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.utils.CommonUtils;

/**
 * Created by mohammed.rampurawala on 7/5/2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;

    private ProgressDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mActivity = (BaseActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this.getMoviesContext());
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
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
