package com.example.shosho.dietfood.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//Message
public class MySubscribtionSpecialData implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<MySubscribtionData> data = null;
    @SerializedName("period")
    @Expose
    private int period;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("wait")
    @Expose
    private int wait;
    @SerializedName("arrived")
    @Expose
    private int arrived;
    @SerializedName("inWay")
    @Expose
    private int inWay;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Parcelable.Creator<MySubscribtionSpecialData> CREATOR = new Creator<MySubscribtionSpecialData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MySubscribtionSpecialData createFromParcel(Parcel in) {
            return new MySubscribtionSpecialData(in);
        }

        public MySubscribtionSpecialData[] newArray(int size) {
            return (new MySubscribtionSpecialData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3565472291295964375L;

    protected MySubscribtionSpecialData(Parcel in) {
        in.readList(this.data, (MySubscribtionData.class.getClassLoader()));
        this.period = ((int) in.readValue((int.class.getClassLoader())));
        this.packageName = ((String) in.readValue((String.class.getClassLoader())));
        this.wait = ((int) in.readValue((int.class.getClassLoader())));
        this.arrived = ((int) in.readValue((int.class.getClassLoader())));
        this.inWay = ((int) in.readValue((int.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public MySubscribtionSpecialData() {
    }

    public List<MySubscribtionData> getData() {
        return data;
    }

    public void setData(List<MySubscribtionData> data) {
        this.data = data;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getArrived() {
        return arrived;
    }

    public void setArrived(int arrived) {
        this.arrived = arrived;
    }

    public int getInWay() {
        return inWay;
    }

    public void setInWay(int inWay) {
        this.inWay = inWay;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(period);
        dest.writeValue(packageName);
        dest.writeValue(wait);
        dest.writeValue(arrived);
        dest.writeValue(inWay);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}
