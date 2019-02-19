package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelSubscribtionResponse implements Serializable, Parcelable
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
    public final static Parcelable.Creator<CancelSubscribtionResponse> CREATOR = new Creator<CancelSubscribtionResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CancelSubscribtionResponse createFromParcel(Parcel in) {
            return new CancelSubscribtionResponse(in);
        }

        public CancelSubscribtionResponse[] newArray(int size) {
            return (new CancelSubscribtionResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -8550997609842381835L;

    protected CancelSubscribtionResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CancelSubscribtionResponse() {
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
