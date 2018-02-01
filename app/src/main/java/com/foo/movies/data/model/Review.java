package com.foo.movies.data.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ahmad on 09/09/15.
 */
public class Review {

    private String id;
    private String author;
    private String content;

    public Review() {

    }

    public Review(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Review(JSONObject trailer) throws JSONException {
        this.id = trailer.getString("id");
        this.author = trailer.getString("author");
        this.content = trailer.getString("content");
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
