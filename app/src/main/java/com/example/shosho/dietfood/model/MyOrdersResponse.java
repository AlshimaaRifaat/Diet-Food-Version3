package com.example.shosho.dietfood.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyOrdersResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private List<MyOrdersData> message = null;
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
    private final static long serialVersionUID = -5363092876279787088L;

    protected MyOrdersResponse(Parcel in) {
        in.readList(this.message, (MyOrdersData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MyOrdersResponse() {
    }

    public List<MyOrdersData> getMessage() {
        return message;
    }

    public void setMessage(List<MyOrdersData> message) {
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
        dest.writeList(message);
        dest.writeValue(status);
        dest.writeValue(error);
    }

    public int describeContents() {
        return 0;
    }

}
