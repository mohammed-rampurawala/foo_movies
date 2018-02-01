package com.foo.movies.views.popular;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseFragment;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class PopularFragment extends BaseFragment implements IPopularView {

    @Inject
    IPopularPresenter<IPopularView> mPresenter;

    private PopularMoviesAdapter mAdapter;

    @BindView(R.id.movies_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty_view_text)
    TextView mEmptyTextView;

    private Unbinder bind;

    public static PopularFragment newInstance() {
        Bundle args = new Bundle();
        PopularFragment fragment = new PopularFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_rated_fragment, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            mPresenter.onAttach(this);
        }
        bind = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        mPresenter.fetchPopularMovies(1);
        changeTitle();
    }

    private void changeTitle() {
        getMoviesActivity().getSupportActionBar().setTitle(getString(R.string.popular));
    }

    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(getMoviesActivity(), getResources().getInteger(R.integer.span_count));
        mAdapter = new PopularMoviesAdapter((BaseActivity) getMoviesActivity());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
    }


    @Override
    public void refreshMovieList(ArrayList<? extends Movie> results) {
        if (mRecyclerView.getVisibility() == View.GONE) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyTextView.setVisibility(View.GONE);
        }
        mAdapter.addItems(results);
    }

    @Override
    public void showEmptyView() {
        mRecyclerView.setVisibility(View.GONE);
        mEmptyTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDetach();
        bind.unbind();
    }

}
