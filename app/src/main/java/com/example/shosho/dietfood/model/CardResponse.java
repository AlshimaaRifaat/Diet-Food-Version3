package com.example.shosho.dietfood.model;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardResponse implements Serializable, Parcelable
{

    @SerializedName("message")
    @Expose
    private CardSpecialData message;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<CardResponse> CREATOR = new Creator<CardResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardResponse createFromParcel(Parcel in) {
            return new CardResponse(in);
        }

        public CardResponse[] newArray(int size) {
            return (new CardResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 3449220052504359846L;

    protected CardResponse(Parcel in) {
        this.message = ((CardSpecialData) in.readValue((CardSpecialData.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CardResponse() {
    }

    public CardSpecialData getMessage() {
        return message;
    }

    public void setMessage(CardSpecialData message) {
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