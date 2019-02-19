package com.example.shosho.dietfood.model.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeBannerResponse {
    @SerializedName("message")
    @Expose
    private List<HomeBannerData> message = null;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public List<HomeBannerData> getMessage() {
        return message;
    }

    public void setMessage(List<HomeBannerData> message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}

