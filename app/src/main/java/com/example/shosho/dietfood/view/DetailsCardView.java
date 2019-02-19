package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.adapter.CardAdapter;
import com.example.shosho.dietfood.model.CardData;

public interface DetailsCardView {
    void showCardDetails(CardData cardData, CardAdapter.ViewHolder holder);
}
