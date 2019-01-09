package com.Travelling.Repositories.GooglePlace;

import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("result")
    private DetailResult DetailResult;

    @SerializedName("status")
    private String status;

    public DetailResult getDetailResult() {
        return DetailResult;
    }

    public void setDetailResult(DetailResult detailResult) {
        DetailResult = detailResult;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
