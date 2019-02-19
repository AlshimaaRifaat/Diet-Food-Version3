package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.OrderDetailsData;
import com.example.shosho.dietfood.model.ProducerFamilyData;

import java.util.List;

public interface OrderDetailsView {
    void showOrderDetailsResult(List<OrderDetailsData> orderDetailsDataList);
}
