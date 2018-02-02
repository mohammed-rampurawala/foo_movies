package com.foo.movies.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Trailer {

    @NonNull
    @PrimaryKey
    private String id;

    @ColumnInfo(name = "key")
    private String key;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "site")
    private String site;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "movie_id")
    private long movieId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getMovieId() {
        return movieId;
    }
}
