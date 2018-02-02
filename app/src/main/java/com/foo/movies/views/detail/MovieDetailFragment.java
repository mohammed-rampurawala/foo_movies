package com.foo.movies.views.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.utils.MoviesConstants;
import com.foo.movies.views.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends BaseFragment implements IDetailView {

    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout toolbarLayout;

    @BindView(R.id.poster)
    ImageView headerImageView;

    @BindView(R.id.moviePoster)
    ImageView moviePoster;

    @BindView(R.id.releaseDate)
    TextView releaseDetail;

    @BindView(R.id.ratingDetail)
    TextView movieRating;

    @BindView(R.id.movieOverView)
    TextView movieOverview;

    @BindView(R.id.reviewsTitle)
    TextView reviewsTitle;

    @BindView(R.id.trailersTitle)
    TextView trailerTitle;

    @BindView(R.id.listItemReviews)
    LinearLayout reviewsContainer;

    @BindView(R.id.listItemTrailer)
    LinearLayout trailerContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    IDetailPresenter<IDetailView> presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getActivityComponent().inject(this);
        presenter.onAttach(this);
        presenter.setMovie((Movie) getArguments().getParcelable(MoviesConstants.MOVIE_KEY));
        initToolbar();
        setMovieData();
    }

    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setMovieData() {
        presenter.fetchTrailers();
        presenter.fetchReviews();
        toolbarLayout.setTitle(presenter.getMovieTitle());

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
        String baseUrl = sharedPreferences.getString(MoviesConstants.POSTER_URL, "");

        Glide.with(this).load(baseUrl + presenter.getBackdropPath()).into(headerImageView);
        Glide.with(this).load(baseUrl + presenter.getPosterPath()).into(moviePoster);

        String releaseDate = presenter.getReleaseDate();
        if (releaseDate.contains("-")) {
            releaseDate = releaseDate.split("-")[0];
        }

        releaseDetail.setText(releaseDate);
        movieRating.setText(getString(R.string.vote_average, presenter.getVoteAverage()));
        movieOverview.setText(presenter.getOverview());
    }


    @Override
    public void addReviewsToUI(List<Review> reviews) {
        if (isAdded()) {
            reviewsTitle.setVisibility(View.VISIBLE);
            ReviewsAdapter mReviewsAdapter = new ReviewsAdapter(getMoviesActivity(), reviews);
            for (int i = 0; i < mReviewsAdapter.getCount(); i++) {
                View view = mReviewsAdapter.getView(i, null, null);
                reviewsContainer.addView(view);
            }
        }
    }

    @Override
    public void addTrailersToUI(final List<Trailer> trailers) {
        if (isAdded()) {
            trailerTitle.setVisibility(View.VISIBLE);
            TrailersAdapter mTrailersAdapter = new TrailersAdapter(getActivity(), trailers);
            for (int i = 0; i < mTrailersAdapter.getCount(); i++) {
                View view = mTrailersAdapter.getView(i, null, null);
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String youtubeLink = "https://www.youtube.com/watch?v=" + trailers.get(finalI).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        startActivity(intent);
                    }
                });
                trailerContainer.addView(view);
            }
        }
    }
}
