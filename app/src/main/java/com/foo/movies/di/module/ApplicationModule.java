package com.foo.movies.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.foo.movies.BuildConfig;
import com.foo.movies.data.AppController;
import com.foo.movies.data.Controller;
import com.foo.movies.data.db.DatabaseHelper;
import com.foo.movies.data.db.IDBHelper;
import com.foo.movies.data.db.MoviesDatabase;
import com.foo.movies.data.network.AppApiHelper;
import com.foo.movies.data.network.IApiHelper;
import com.foo.movies.di.ApplicationContext;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mohammed.rampurawala
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }


    @Provides
    @Singleton
    HttpLoggingInterceptor provideLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideClient(HttpLoggingInterceptor interceptor){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        return httpClient.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    @Singleton
    Controller provideController(AppController appDataController) {
        return appDataController;
    }

    @Provides
    @Singleton
    IApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    IDBHelper provideDBHelper(DatabaseHelper databaseHelper) {
        return databaseHelper;
    }

    @Provides
    @Singleton
    RoomDatabase provideDatabase() {
        return Room.databaseBuilder(mApplication, MoviesDatabase.class, BuildConfig.DB_NAME).build();
    }
}
