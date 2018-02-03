package com.foo.movies.views.detail;

import android.os.Bundle;

import com.foo.movies.R;
import com.foo.movies.views.base.BaseActivity;

public class DetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putAll(getIntent().getExtras());
            MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
            movieDetailFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, movieDetailFragment)
                    .commit();
        }
    }
}
