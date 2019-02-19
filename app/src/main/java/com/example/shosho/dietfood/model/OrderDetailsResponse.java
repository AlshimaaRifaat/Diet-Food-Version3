package com.example.shosho.dietfood.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private List<OrderDetailsData> message = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<OrderDetailsResponse> CREATOR = new Creator<OrderDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderDetailsResponse createFromParcel(Parcel in) {
            return new OrderDetailsResponse(in);
        }

        public OrderDetailsResponse[] newArray(int size) {
            return (new OrderDetailsResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1271642279080765460L;

    protected OrderDetailsResponse(Parcel in) {
        in.readList(this.message, (OrderDetailsData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderDetailsResponse() {
    }

    public List<OrderDetailsData> getMessage() {
        return message;
    }

    public void setMessage(List<OrderDetailsData> message) {
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
