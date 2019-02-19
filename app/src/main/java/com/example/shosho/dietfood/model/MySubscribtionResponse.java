package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MySubscribtionResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private MySubscribtionSpecialData message;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<MyOrdersResponse> CREATOR = new Creator<MyOrdersResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MyOrdersResponse createFromParcel(Parcel in) {
            return new MyOrdersResponse(in);
        }

        public MyOrdersResponse[] newArray(int size) {
            return (new MyOrdersResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 9182649370382835109L;

    protected MySubscribtionResponse(Parcel in) {
        this.message = ((MySubscribtionSpecialData) in.readValue((MySubscribtionSpecialData.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MySubscribtionResponse() {
    }

    public MySubscribtionSpecialData getMessage() {
        return message;
    }

    public void setMessage(MySubscribtionSpecialData message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(status);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}
