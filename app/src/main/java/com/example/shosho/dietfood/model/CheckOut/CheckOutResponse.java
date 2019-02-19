package com.example.shosho.dietfood.model.CheckOut;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOutResponse implements Serializable, Parcelable
{

    @SerializedName("result")
    @Expose
    private CheckOutData result;
    @SerializedName("buildNumber")
    @Expose
    private String buildNumber;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("ndc")
    @Expose
    private String ndc;
    @SerializedName("id")
    @Expose
    private String id;
    public final static Parcelable.Creator<CheckOutResponse> CREATOR = new Creator<CheckOutResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CheckOutResponse createFromParcel(Parcel in) {
            return new CheckOutResponse(in);
        }

        public CheckOutResponse[] newArray(int size) {
            return (new CheckOutResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4237600322826852126L;

    protected CheckOutResponse(Parcel in) {
        this.result = ((CheckOutData) in.readValue((CheckOutData.class.getClassLoader())));
        this.buildNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.timestamp = ((String) in.readValue((String.class.getClassLoader())));
        this.ndc = ((String) in.readValue((String.class.getClassLoader())));
        this.id = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CheckOutResponse() {
    }

    public CheckOutData getResult() {
        return result;
    }

    public void setResult(CheckOutData result) {
        this.result = result;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNdc() {
        return ndc;
    }

    public void setNdc(String ndc) {
        this.ndc = ndc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(buildNumber);
        dest.writeValue(timestamp);
        dest.writeValue(ndc);
        dest.writeValue(id);
    }

    public int describeContents() {
        return 0;
    }

}