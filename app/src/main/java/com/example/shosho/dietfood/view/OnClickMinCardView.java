package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.adapter.CardAdapter;
import com.example.shosho.dietfood.model.CardData;

public interface OnClickMinCardView {
    void showOnClickMinCardResult(CardData cardData, CardAdapter.ViewHolder holder);
}
