package com.example.shosho.dietfood.model;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardData implements  Parcelable
{

    @SerializedName("cardId")
    @Expose
    private String cardId;
    @SerializedName("mealFoodId")
    @Expose
    private String mealFoodId;
    @SerializedName("mealFoodName")
    @Expose
    private String mealFoodName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("totalPrice")
    @Expose
    private double totalPrice;
    public final static Parcelable.Creator<CardData> CREATOR = new Creator<CardData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CardData createFromParcel(Parcel in) {
            return new CardData(in);
        }

        public CardData[] newArray(int size) {
            return (new CardData[size]);
        }

    }
            ;
    private final static long serialVersionUID = -1691449550163661103L;

    protected CardData(Parcel in) {
        this.cardId = ((String) in.readValue((String.class.getClassLoader())));
        this.mealFoodId = ((String) in.readValue((String.class.getClassLoader())));
        this.mealFoodName = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.qty = ((String) in.readValue((String.class.getClassLoader())));
        this.totalPrice = ((double) in.readValue((double.class.getClassLoader())));
    }

    public CardData() {
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMealFoodId() {
        return mealFoodId;
    }

    public void setMealFoodId(String mealFoodId) {
        this.mealFoodId = mealFoodId;
    }

    public String getMealFoodName() {
        return mealFoodName;
    }

    public void setMealFoodName(String mealFoodName) {
        this.mealFoodName = mealFoodName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cardId);
        dest.writeValue(mealFoodId);
        dest.writeValue(mealFoodName);
        dest.writeValue(price);
        dest.writeValue(image);
        dest.writeValue(qty);
        dest.writeValue(totalPrice);
    }

    public int describeContents() {
        return 0;
    }

}