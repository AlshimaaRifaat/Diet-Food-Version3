package com.example.shosho.dietfood.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderDetailsData implements  Parcelable
{

    @SerializedName("mealFoodsId")
    @Expose
    private String mealFoodsId;
    @SerializedName("mealFoodsName")
    @Expose
    private String mealFoodsName;
    @SerializedName("mealFoodsPrice")
    @Expose
    private String mealFoodsPrice;
    @SerializedName("qty")
    @Expose
    private String qty;
    public final static Parcelable.Creator<OrderDetailsData> CREATOR = new Creator<OrderDetailsData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OrderDetailsData createFromParcel(Parcel in) {
            return new OrderDetailsData(in);
        }

        public OrderDetailsData[] newArray(int size) {
            return (new OrderDetailsData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 4142110281856031912L;

    protected OrderDetailsData(Parcel in) {
        this.mealFoodsId = ((String) in.readValue((String.class.getClassLoader())));
        this.mealFoodsName = ((String) in.readValue((String.class.getClassLoader())));
        this.mealFoodsPrice = ((String) in.readValue((String.class.getClassLoader())));
        this.qty = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OrderDetailsData() {
    }

    public String getMealFoodsId() {
        return mealFoodsId;
    }

    public void setMealFoodsId(String mealFoodsId) {
        this.mealFoodsId = mealFoodsId;
    }

    public String getMealFoodsName() {
        return mealFoodsName;
    }

    public void setMealFoodsName(String mealFoodsName) {
        this.mealFoodsName = mealFoodsName;
    }

    public String getMealFoodsPrice() {
        return mealFoodsPrice;
    }

    public void setMealFoodsPrice(String mealFoodsPrice) {
        this.mealFoodsPrice = mealFoodsPrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mealFoodsId);
        dest.writeValue(mealFoodsName);
        dest.writeValue(mealFoodsPrice);
        dest.writeValue(qty);
    }

    public int describeContents() {
        return 0;
    }

}
