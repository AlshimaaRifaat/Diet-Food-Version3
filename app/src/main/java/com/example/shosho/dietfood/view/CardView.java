package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.model.CardSpecialData;

import java.util.List;

public interface CardView {
    void showCardList(List<CardData> cardDataList);
    void showPrice(String price);
    void showEmptyCard();
    void showError();
}