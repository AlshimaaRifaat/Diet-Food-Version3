package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaidConsultationResponse implements  Parcelable
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
    public final static Parcelable.Creator<PaidConsultationResponse> CREATOR = new Creator<PaidConsultationResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaidConsultationResponse createFromParcel(Parcel in) {
            return new PaidConsultationResponse(in);
        }

        public PaidConsultationResponse[] newArray(int size) {
            return (new PaidConsultationResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 625653104255627587L;

    protected PaidConsultationResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaidConsultationResponse() {
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
