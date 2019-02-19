package com.example.shosho.dietfood.model.CheckOut;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckOutData implements Serializable, Parcelable
{

    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("description")
    @Expose
    private String description;
    public final static Parcelable.Creator<CheckOutData> CREATOR = new Creator<CheckOutData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CheckOutData createFromParcel(Parcel in) {
            return new CheckOutData(in);
        }

        public CheckOutData[] newArray(int size) {
            return (new CheckOutData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4225949606861548565L;

    protected CheckOutData(Parcel in) {
        this.code = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
    }

    public CheckOutData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(code);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}