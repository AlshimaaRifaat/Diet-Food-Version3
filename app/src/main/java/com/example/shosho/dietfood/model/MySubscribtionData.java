package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MySubscribtionData implements Serializable, Parcelable
{

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("due")
    @Expose
    private String due;
    @SerializedName("status")
    @Expose
    private String status;
    public final static Parcelable.Creator<MySubscribtionData> CREATOR = new Creator<MySubscribtionData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MySubscribtionData createFromParcel(Parcel in) {
            return new MySubscribtionData(in);
        }

        public MySubscribtionData[] newArray(int size) {
            return (new MySubscribtionData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 1236208233869297919L;

    protected MySubscribtionData(Parcel in) {
        this.day = ((String) in.readValue((String.class.getClassLoader())));
        this.due = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MySubscribtionData() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDue() {
        return due;
    }

    public void setDue(String due) {
        this.due = due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(day);
        dest.writeValue(due);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}
