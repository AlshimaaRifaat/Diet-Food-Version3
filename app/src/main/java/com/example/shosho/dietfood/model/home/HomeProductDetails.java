package com.example.shosho.dietfood.model.home;

public class HomeProductDetails {
    private String Image;
    private String Mealname;
    private String Price;
    private String MealComponentName;
    private String quantity;
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public HomeProductDetails() {
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getMealname() {
        return Mealname;
    }

    public void setMealname(String mealname) {
        Mealname = mealname;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMealComponentName() {
        return MealComponentName;
    }

    public void setMealComponentName(String mealComponentName) {
        MealComponentName = mealComponentName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
