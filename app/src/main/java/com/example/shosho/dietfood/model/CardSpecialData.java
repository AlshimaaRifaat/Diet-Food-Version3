package com.example.shosho.dietfood.model;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardSpecialData implements  Parcelable
{

    @SerializedName("data")
    @Expose
    private List<CardData> data = null;
    @SerializedName("price")
    @Expose
    private double price;
    public final static Parcelable.Creator<CardSpecialData> CREATOR = new Creator<CardSpecialData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardSpecialData createFromParcel(Parcel in) {
            return new CardSpecialData(in);
        }

        public CardSpecialData[] newArray(int size) {
            return (new CardSpecialData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 2287379897980134575L;

    protected CardSpecialData(Parcel in) {
        in.readList(this.data, (CardData.class.getClassLoader()));
        this.price = ((double) in.readValue((double.class.getClassLoader())));
    }

    public CardSpecialData() {
    }

    public List<CardData> getData() {
        return data;
    }

    public void setData(List<CardData> data) {
        this.data = data;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(price);
    }

    public int describeContents() {
        return 0;
    }

}