package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.ProducerFamilyData;

import java.util.List;

public interface ProducerFamilyView {
    void showProducerFamilyData(List<ProducerFamilyData> producerFamilyDataList);
    //void showMealComponentList(List<ProducerFamilyData> Id);
    void showError();
}
