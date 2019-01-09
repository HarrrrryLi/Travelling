package com.Travelling.Repositories.GooglePlace;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailResult {
    @SerializedName("formatted_phone_number")
    private String phone_num;

    @SerializedName("website")
    private String website;

    @SerializedName("reviews")
    private List<Review> reviews;

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
