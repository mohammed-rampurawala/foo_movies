package com.foo.movies.views.detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.movies.R;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.utils.CommonUtils;
import com.foo.movies.utils.MoviesConstants;
import com.foo.movies.views.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivityFragment extends BaseFragment implements IDetailView {

    private Movie movie;

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
        if (!CommonUtils.isNetworkAvailable(getActivity())) {
            //TODO: Fetch trailers and reviews from local
        } else {
            //TODO: Fetch trailers and reviews
        }
        initToolbar();
        initUI();
    }

    private void initUI() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            movie = arguments.getParcelable(MoviesConstants.MOVIE_KEY);
            toolbarLayout.setTitle(movie.getTitle());

            SharedPreferences sharedPreferences = getContext().getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
            String baseUrl = sharedPreferences.getString(MoviesConstants.POSTER_URL, "");

            Glide.with(this).load(baseUrl + movie.getBackdropPath()).into(headerImageView);
        }
        setMovieData(movie);
    }

    private void initToolbar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setMovieData(Movie movie) {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
        String baseUrl = sharedPreferences.getString(MoviesConstants.POSTER_URL, "");

        Glide.with(this).load(baseUrl + movie.getPosterPath()).into(moviePoster);

        String releaseDate = movie.getReleaseDate();
        if (releaseDate.contains("-")) {
            releaseDate = releaseDate.split("-")[0];
        }

        releaseDetail.setText(releaseDate);
        movieRating.setText(getString(R.string.vote_average, movie.getVoteAverage()));
        movieOverview.setText(movie.getOverview());
    }

    private void fetchOfflineReviews() {
      /*  ReviewSelection where = new ReviewSelection();
        where.movieId(movie.getId());
        ReviewCursor reviewCursor = where.query(getActivity());
        Review review;
        List<Review> favouriteReviews = new ArrayList<>();
        while (reviewCursor.moveToNext()) {
            review = new Review(reviewCursor.getReviewAuthor(), reviewCursor.getReviewContent());
            favouriteReviews.add(review);
        }
        reviewCursor.close();
        addReviewsToUI(favouriteReviews);*/
    }

    private void fetchOfflineTrailers() {
    /*    TrailerSelection where = new TrailerSelection();
        where.movieId(movie.getId());
        TrailerCursor trailerCursor = where.query(getActivity());
        Trailer trailer;
        trailers = new ArrayList<>();
        while (trailerCursor.moveToNext()) {
            trailer = new Trailer(trailerCursor.getTrailerName(), trailerCursor.getTrailerKey());
            trailers.add(trailer);
        }
        trailerCursor.close();
        addTrailersToUI(trailers);
        prepareShare();
        getActivity().invalidateOptionsMenu();*/
    }

    private void addReviewsToUI(List<Review> favouriteReviews) {
     /*   if (isAdded()) {
            TextView reviews_title = (TextView) getActivity().findViewById(R.id.reviews_title);
            reviews_title.setVisibility(View.VISIBLE);
            mReviews = new ArrayList<>();
            mReviews.addAll(favouriteReviews);
            ReviewsAdapter mReviewsAdapter = new ReviewsAdapter(getActivity(), mReviews);
            LinearLayout reviewsListView = (LinearLayout) getActivity()
                    .findViewById(R.id.list_item_reviews);
            for (int i = 0; i < mReviewsAdapter.getCount(); i++) {
                View view = mReviewsAdapter.getView(i, null, null);
                reviewsListView.addView(view);
            }
        }*/
    }

    private void addTrailersToUI(List<Trailer> trailers) {
        /*if (isAdded()) {
            TextView trailers_title = (TextView) getActivity().findViewById(R.id.trailers_title);
            trailers_title.setVisibility(View.VISIBLE);
            final ArrayList<Trailer> mTrailers = new ArrayList<>();
            mTrailers.addAll(trailers);
            TrailersAdapter mTrailersAdapter = new TrailersAdapter(getActivity(), mTrailers);
            LinearLayout trailersListView = (LinearLayout) getActivity().findViewById(R.id.list_item_trailers);

            for (int i = 0; i < mTrailersAdapter.getCount(); i++) {
                View view = mTrailersAdapter.getView(i, null, null);
                final int finalI = i;
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String youtubeLink = "https://www.youtube.com/watch?v=" + mTrailers.get(finalI).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink));
                        Utility.preferPackageForIntent(getActivity(), intent,
                                Utility.YOUTUBE_PACKAGE_NAME);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        startActivity(intent);
                    }
                });
                trailersListView.addView(view);
                trailer_title = trailers.get(0).getName();
                trailer_key = trailers.get(0).getKey();
            }
        }*/
    }

    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
    }
}
