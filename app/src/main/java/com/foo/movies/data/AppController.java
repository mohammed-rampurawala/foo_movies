package com.foo.movies.data;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.content.Context;
import android.content.SharedPreferences;

import com.foo.movies.BuildConfig;
import com.foo.movies.data.db.IDBHelper;
import com.foo.movies.data.model.ConfigurationResponse;
import com.foo.movies.data.model.Movie;
import com.foo.movies.data.model.PopularMovieResponse;
import com.foo.movies.data.model.Review;
import com.foo.movies.data.model.ReviewResponse;
import com.foo.movies.data.model.TopRatedMovieResponse;
import com.foo.movies.data.model.Trailer;
import com.foo.movies.data.model.TrailerResponse;
import com.foo.movies.data.network.IApiHelper;
import com.foo.movies.di.ApplicationContext;
import com.foo.movies.utils.MoviesConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Entry point for all the db and network calls
 *
 * @author mohammed.rampurawala
 */

@Singleton
public class AppController implements Controller {

    private static final String TAG = "AppController";

    /**
     * Application context
     */
    private final Context mContext;

    /**
     * API Helper for network calls
     */
    private final IApiHelper mApiHelper;

    /**
     * API Helper for database calls
     */
    private final IDBHelper mDBHelper;

    @Inject
    public AppController(@ApplicationContext Context context, IApiHelper apiHelper, IDBHelper dbHelper) {
        mContext = context;
        mApiHelper = apiHelper;
        mDBHelper = dbHelper;
    }

    @Override
    public Observable<ConfigurationResponse> getApiConfiguration() {
        return mApiHelper.getApiConfiguration().map(new Function<ConfigurationResponse, ConfigurationResponse>() {
            @Override
            public ConfigurationResponse apply(ConfigurationResponse configurationResponse) throws Exception {
                if (configurationResponse == null || configurationResponse.getImages() == null) {
                    return configurationResponse;
                }
                //Store the url in Preferences
                SharedPreferences sharedPreferences = mContext.getSharedPreferences(MoviesConstants.SHARED_PREFS, Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                ConfigurationResponse.Images images = configurationResponse.getImages();
                edit.putString(MoviesConstants.LOGO_URL, images.getBaseUrl() + images.getLogoSizes().get(images.getLogoSizes().size() - 1));
                edit.putString(MoviesConstants.POSTER_URL, images.getBaseUrl() + images.getPosterSizes().get(images.getPosterSizes().size() - 1));
                edit.apply();

                return configurationResponse;
            }
        });
    }

    @Override
    public Observable<PopularMovieResponse> getPopularMovies(int page) {
        return mApiHelper.getPopularMovies(page).map(new Function<PopularMovieResponse, PopularMovieResponse>() {
            @Override
            public PopularMovieResponse apply(PopularMovieResponse movieMainResponse) throws Exception {
                insertMovies(movieMainResponse.getResults());
                return movieMainResponse;
            }
        });
    }

    @Override
    public Observable<TopRatedMovieResponse> getTopRatedMovies(int page) {
        return mApiHelper.getTopRatedMovies(page).map(new Function<TopRatedMovieResponse, TopRatedMovieResponse>() {
            @Override
            public TopRatedMovieResponse apply(TopRatedMovieResponse movieMainResponse) throws Exception {
                insertMovies(movieMainResponse.getResults());
                return movieMainResponse;
            }
        });
    }

    @Override
    public Observable<ReviewResponse> getReviewsForMovie(long movieId) {
        return mApiHelper.getReviewsForMovie(movieId).map(new Function<ReviewResponse, ReviewResponse>() {
            @Override
            public ReviewResponse apply(ReviewResponse reviewResponse) throws Exception {
                insertReviews(reviewResponse.getResults());
                return reviewResponse;
            }
        });
    }

    @Override
    public Observable<TrailerResponse> getMovieTrailers(long movieId) {
        return mApiHelper.getMovieTrailers(movieId).map(new Function<TrailerResponse, TrailerResponse>() {
            @Override
            public TrailerResponse apply(TrailerResponse trailerResponse) throws Exception {
                insertTrailers(trailerResponse.getResults());
                return trailerResponse;
            }
        });
    }

    @Override
    public Flowable<List<Movie>> getSearchMovies(String query) {
        return mDBHelper.getSearchMovies(query);
    }

    @Override
    public void insertMovies(ArrayList<? extends Movie> movies) {
        mDBHelper.insertMovies(movies);
    }

    @Override
    public void insertTrailers(List<Trailer> trailers) {
        mDBHelper.insertTrailers(trailers);
    }

    @Override
    public void insertReviews(List<Review> reviews) {
        mDBHelper.insertReviews(reviews);
    }
}
