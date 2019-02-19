package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.AddToCardData;

public interface AddToCardView {
    void showAddToCardResult(AddToCardData addToCardData);
    void showError();
}
