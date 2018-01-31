package com.foo.movies.views.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.foo.movies.R;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseFragment;
import com.foo.movies.views.popular.PopularFragment;
import com.foo.movies.views.toprated.TopRatedFragment;

import javax.inject.Inject;

public class MoviesActivity extends BaseActivity implements IMoviesView {
    private DrawerLayout drawerLayout;

    @Inject
    IMoviePresenter<IMoviesView> moviePresenter;

    private NavigationView navigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_activity);

        getActivityComponent().inject(this);
        moviePresenter.onAttach(this);

        initToolbar();
        initUI();
        addFragment(TopRatedFragment.newInstance());
        navigationView.setCheckedItem(R.id.drawer_top_rated);
    }

    private void addFragment(BaseFragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.content_frame, fragment, null)
                .commit();
    }

    private void initUI() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                handleFragmentChange(menuItem);
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void handleFragmentChange(MenuItem menuItem) {
        if (menuItem.isChecked()) {
            return;
        }

        switch (menuItem.getItemId()) {
            case R.id.drawer_popular:
                addFragment(PopularFragment.newInstance());
                break;
            case R.id.drawer_top_rated:
                addFragment(TopRatedFragment.newInstance());
                break;
        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
