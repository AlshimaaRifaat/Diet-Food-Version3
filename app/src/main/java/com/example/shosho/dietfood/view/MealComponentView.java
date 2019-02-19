package com.example.shosho.dietfood.view;

import android.content.Context;

import com.example.shosho.dietfood.model.MealComponentData;

import java.util.List;

public interface MealComponentView {
    void showMealComponentList(List<MealComponentData> mealComponentDataList);
    void showError();
}
