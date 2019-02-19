package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.MyOrdersData;
import com.example.shosho.dietfood.model.home.HomeProductData;

import java.util.List;

public interface MyOrdersView {
    void showMyOrdersResult(List<MyOrdersData> myOrdersDataList);
    void showError();
}
