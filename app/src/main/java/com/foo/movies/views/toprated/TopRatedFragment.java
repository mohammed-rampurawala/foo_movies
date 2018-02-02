package com.foo.movies.views.toprated;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.listener.ICallback;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseFragment;
import com.foo.movies.views.movies.MoviesAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class TopRatedFragment extends BaseFragment implements ITopRatedMView {

    @Inject
    ITopRatedPresenter<ITopRatedMView> mPresenter;

    private MoviesAdapter mAdapter;

    @BindView(R.id.movies_recyclerview)
    RecyclerView mRecyclerView;

    @BindView(R.id.empty_view_text)
    TextView mEmptyTextView;

    private Unbinder bind;

    public static TopRatedFragment newInstance() {
        Bundle args = new Bundle();
        TopRatedFragment fragment = new TopRatedFragment();
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
        mPresenter.fetchTopRatedMovies(1);
        changeTitle();
    }

    private void changeTitle() {
        getMoviesActivity().getSupportActionBar().setTitle(getString(R.string.top_rated));
    }

    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(getMoviesActivity(), getResources().getInteger(R.integer.span_count));
        mAdapter = new MoviesAdapter((BaseActivity) getMoviesActivity());
        mAdapter.setOnItemClickListener((ICallback) getMoviesActivity());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(RecyclerView.VERTICAL)) {
                    mPresenter.loadNextPage();
                }
            }
        });
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
