package com.foo.movies.views.toprated;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.foo.movies.R;
import com.foo.movies.di.component.ActivityComponent;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class TopRatedFragment extends BaseFragment implements ITopRatedMView {

    @Inject
    ITopRatedPresenter<ITopRatedMView> mPresenter;

    private TopRatedMoviesAdapter mAdapter;

    private RecyclerView mRecyclerView;

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
        initView(view);
        initPresenter();
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.movies_recyclerview);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
    }

    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(getMoviesActivity(), 2);
        mAdapter = new TopRatedMoviesAdapter((BaseActivity) getMoviesActivity());

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(manager);
    }

    private void initPresenter() {
        mPresenter.onAttach(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onDetach();
    }
}
