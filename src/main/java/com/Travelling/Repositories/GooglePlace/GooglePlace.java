package com.Travelling.Repositories.GooglePlace;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GooglePlace {
    @SerializedName("debug_info")
    private List<String> debug_info;

    @SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("next_page_token")
    private String next_page_token;

    @SerializedName("results")
    private List<Results> results;

    @SerializedName("status")
    private String status;

    public List<String> getDebug_info() {
        return debug_info;
    }

    public void setDebug_info(List<String> debug_info) {
        this.debug_info = debug_info;
    }

    public List<String> getHtml_attributions() {
        return html_attributions;
    }

    public void setHtml_attributions(List<String> html_attributions) {
        this.html_attributions = html_attributions;
    }

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
