package com.foo.movies.views.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.popular.PopularMoviesAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SearchActivity extends BaseActivity implements ISearchView {
    private DrawerLayout drawerLayout;

    @Inject
    ISearchPresenter<ISearchView> presenter;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.movies_recyclerview)
    RecyclerView moviesRecyclerView;

    @BindView(R.id.empty_view_text)
    TextView emptyTextView;

    private Unbinder binder;

    private SearchMoviesAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        getActivityComponent().inject(this);
        presenter.onAttach(this);
        binder = ButterKnife.bind(this);
        initToolbar();
        initUI();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.search));
    }

    private void initUI() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.getMovies(newText);
                return false;
            }
        });

        initAdapter();
    }

    private void initAdapter() {
        GridLayoutManager manager = new GridLayoutManager(getMoviesActivity(), getResources().getInteger(R.integer.span_count));
        mAdapter = new SearchMoviesAdapter((BaseActivity) getMoviesActivity());

        moviesRecyclerView.setAdapter(mAdapter);
        moviesRecyclerView.setLayoutManager(manager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    @Override
    public void populateMovies(List<Movie> movies) {
        if (emptyTextView.getVisibility() == View.VISIBLE) {
            emptyTextView.setVisibility(View.GONE);
            moviesRecyclerView.setVisibility(View.VISIBLE);
        }
        mAdapter.addItems(movies);
    }

    @Override
    public void onMoviesFound() {
        emptyTextView.setVisibility(View.VISIBLE);
        moviesRecyclerView.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.unbind();
    }
}
