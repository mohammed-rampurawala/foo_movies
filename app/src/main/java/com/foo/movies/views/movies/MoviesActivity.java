package com.foo.movies.views.movies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.listener.ICallback;
import com.foo.movies.utils.AppLogger;
import com.foo.movies.utils.MoviesConstants;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseFragment;
import com.foo.movies.views.detail.DetailActivity;
import com.foo.movies.views.popular.PopularFragment;
import com.foo.movies.views.search.SearchActivity;
import com.foo.movies.views.toprated.TopRatedFragment;

import java.util.logging.Logger;

import javax.inject.Inject;

public class MoviesActivity extends BaseActivity implements IMoviesView, ICallback {
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
                menuItem.setChecked(true);
                break;
            case R.id.drawer_top_rated:
                addFragment(TopRatedFragment.newInstance());
                menuItem.setChecked(true);
                break;
            case R.id.drawer_search:
                startSearchActivity();
                menuItem.setChecked(false);
                break;
            default:
                AppLogger.d("No Menu item found for fragment change");
                break;

        }
    }

    private void startSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
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

    @Override
    public void onItemSelected(Movie movie, View view) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MoviesConstants.MOVIE_KEY, movie);

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImageView moviePoster = (ImageView) view.findViewById(R.id.movie_image);
            Pair<View, String> p1 = Pair.create((View) moviePoster, getString(R.string.movie_poster_transition));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1);
            startActivity(intent, options.toBundle());

        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else {
            super.onBackPressed();
        }
    }
}
