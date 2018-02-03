package com.foo.movies.views.detail;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.listener.ITrailerClickListener;
import com.foo.movies.utils.MoviesConstants;
import com.foo.movies.views.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends BaseFragment implements IDetailView, ITrailerClickListener {

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

    @BindView(R.id.no_trailers)
    TextView noTrailers;

    @BindView(R.id.no_reviews)
    TextView noReviews;

    @BindView(R.id.reviewsRecyclerView)
    RecyclerView reviewsContainer;

    @BindView(R.id.trailerRecyclerView)
    RecyclerView trailerContainer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    TrailersAdapter trailersAdapter;

    ReviewsAdapter reviewsAdapter;

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
        init();
        initToolbar();
        setMovieData();
    }

    private void init() {
        trailersAdapter = new TrailersAdapter(getMoviesActivity(), this);
        LinearLayoutManager manager = new LinearLayoutManager(getMoviesActivity(), RecyclerView.HORIZONTAL, false);
        trailerContainer.setLayoutManager(manager);
        trailerContainer.setAdapter(trailersAdapter);
        trailerContainer.setItemAnimator(new DefaultItemAnimator());
        trailerContainer.setNestedScrollingEnabled(false);


        reviewsAdapter = new ReviewsAdapter(getMoviesActivity());
        LinearLayoutManager reviewsManager = new LinearLayoutManager(getMoviesActivity(), RecyclerView.VERTICAL, false);
        reviewsContainer.setLayoutManager(reviewsManager);
        reviewsContainer.setAdapter(reviewsAdapter);
        reviewsContainer.setItemAnimator(new DefaultItemAnimator());
        reviewsContainer.setNestedScrollingEnabled(false);


    }

    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMoviesActivity().finish();
            }
        });
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
        if (isAdded() && reviews.size() != 0) {
            reviewsContainer.setVisibility(View.VISIBLE);
            reviewsAdapter.setReviewList(reviews);
            noReviews.setVisibility(View.GONE);
        }
    }

    @Override
    public void addTrailersToUI(final List<Trailer> trailers) {
        if (isAdded() && trailers.size() != 0) {
            trailerContainer.setVisibility(View.VISIBLE);
            trailersAdapter.setTrailers(trailers);
            noTrailers.setVisibility(View.GONE);
        }
    }

    @Override
    public void onTrailerClicked(String trailerKey) {
        String youtubeLink = "https://www.youtube.com/watch?v=" + trailerKey;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        startActivity(intent);
    }
}
