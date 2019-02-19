package com.example.shosho.dietfood.model;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProducerFamilyData implements  Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    public final static Parcelable.Creator<ProducerFamilyData> CREATOR = new Creator<ProducerFamilyData>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProducerFamilyData createFromParcel(Parcel in) {
            return new ProducerFamilyData(in);
        }

        public ProducerFamilyData[] newArray(int size) {
            return (new ProducerFamilyData[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6977744824159967735L;

    protected ProducerFamilyData(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.price = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
    }

    public ProducerFamilyData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(price);
        dest.writeValue(image);
    }

    public int describeContents() {
        return 0;
    }

}