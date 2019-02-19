package com.example.shosho.dietfood.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProducerFamilyResponse implements  Parcelable
{

    @SerializedName("message")
    @Expose
    private List<ProducerFamilyData> message = null;
    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("error")
    @Expose
    private String error;
    public final static Parcelable.Creator<ProducerFamilyResponse> CREATOR = new Creator<ProducerFamilyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProducerFamilyResponse createFromParcel(Parcel in) {
            return new ProducerFamilyResponse(in);
        }

        public ProducerFamilyResponse[] newArray(int size) {
            return (new ProducerFamilyResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 884876370677568628L;

    protected ProducerFamilyResponse(Parcel in) {
        in.readList(this.message, (ProducerFamilyData.class.getClassLoader()));
        this.status = ((boolean) in.readValue((boolean.class.getClassLoader())));
        this.error = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProducerFamilyResponse() {
    }

    public List<ProducerFamilyData> getMessage() {
        return message;
    }

    public void setMessage(List<ProducerFamilyData> message) {
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