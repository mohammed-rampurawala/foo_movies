package com.foo.movies.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ahmad on 09/09/15.
 */
@Entity
public class Review {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "author")
    private String author;

    @ColumnInfo(name = "content")
    private String content;

    @ColumnInfo(name = "url")
    private String url;

    @ColumnInfo(name = "movie_id")
    private long movieId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getMovieId() {
        return movieId;
    }

}
