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
            bundle.putBundle("movie", getIntent().getExtras());
            DetailActivityFragment detailActivityFragment = new DetailActivityFragment();
            detailActivityFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, detailActivityFragment)
                    .commit();
        }
    }
}
