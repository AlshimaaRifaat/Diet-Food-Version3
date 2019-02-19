package com.example.shosho.dietfood.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddToCardData {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cartId")
    @Expose
    private Integer cartId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

}