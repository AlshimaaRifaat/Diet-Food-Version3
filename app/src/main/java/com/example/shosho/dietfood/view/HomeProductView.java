package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.home.HomeProductData;

import java.util.List;

public interface HomeProductView {
    void showHomeProductResult(List<HomeProductData> homeProductDataList);
    void showError();
}
