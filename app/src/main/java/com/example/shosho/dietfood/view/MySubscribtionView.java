package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.MySubscribtionData;
import com.example.shosho.dietfood.model.MySubscribtionSpecialData;
import com.example.shosho.dietfood.model.PackageDetailsData;

import java.util.List;

public interface MySubscribtionView {
    void showMySubscribtionsResult(MySubscribtionSpecialData mySubscribtionSpecialData);
    void showMySubscribtionsList(List<MySubscribtionData> mySubscribtionDataList);
    void showError();
}
