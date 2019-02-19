package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscribtionResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<SubscribtionResponse> CREATOR = new Creator<SubscribtionResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SubscribtionResponse createFromParcel(Parcel in) {
            return new SubscribtionResponse(in);
        }

        public SubscribtionResponse[] newArray(int size) {
            return (new SubscribtionResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8499213501231167969L;

    protected SubscribtionResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SubscribtionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
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
