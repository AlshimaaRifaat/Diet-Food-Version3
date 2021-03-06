package com.example.shosho.dietfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCardResponse {

    @SerializedName("message")
    @Expose
    private AddToCardData message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("error")
    @Expose
    private String error;

    public AddToCardData getMessage() {
        return message;
    }

    public void setMessage(AddToCardData message) {
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