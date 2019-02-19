package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOrderData implements  Parcelable
{

    @SerializedName("orderId")
    @Expose
    private int orderId;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<PostOrderData> CREATOR = new Creator<PostOrderData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PostOrderData createFromParcel(Parcel in) {
            return new PostOrderData(in);
        }

        public PostOrderData[] newArray(int size) {
            return (new PostOrderData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1015642447541852261L;

    protected PostOrderData(Parcel in) {
        this.orderId = ((int) in.readValue((int.class.getClassLoader())));
        this.total = ((int) in.readValue((int.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PostOrderData() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(orderId);
        dest.writeValue(total);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }

}
