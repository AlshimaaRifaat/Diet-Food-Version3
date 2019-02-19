package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostOrderResponse implements  Parcelable
{

    @SerializedName("message")
    @Expose
    private PostOrderData message;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<PostOrderResponse> CREATOR = new Creator<PostOrderResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PostOrderResponse createFromParcel(Parcel in) {
            return new PostOrderResponse(in);
        }

        public PostOrderResponse[] newArray(int size) {
            return (new PostOrderResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4951453438861501956L;

    protected PostOrderResponse(Parcel in) {
        this.message = ((PostOrderData) in.readValue((PostOrderData.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PostOrderResponse() {
    }

    public PostOrderData getMessage() {
        return message;
    }

    public void setMessage(PostOrderData message) {
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
