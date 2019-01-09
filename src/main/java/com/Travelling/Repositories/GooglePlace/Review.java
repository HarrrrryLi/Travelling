package com.Travelling.Repositories.GooglePlace;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("author_name")
    private String author_name;

    @SerializedName("rating")
    private float rating;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
