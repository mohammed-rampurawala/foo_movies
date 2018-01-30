package com.foo.movies.views.toprated;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.foo.movies.R;
import com.foo.movies.views.base.BaseActivity;
import com.foo.movies.views.base.BaseAdapter;
import com.foo.movies.views.base.BaseMoviesViewHolder;

/**
 * Created by mohammed.rampurawala on 1/30/2018.
 */

public class TopRatedMoviesAdapter extends BaseAdapter {


    public TopRatedMoviesAdapter(BaseActivity activity) {
        super(activity);
    }

    @Override
    public BaseMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseMoviesViewHolder(getInflater().inflate(R.layout.movies_item, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseMoviesViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
