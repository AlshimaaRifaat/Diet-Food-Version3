package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.home.HomeBannerData;

import java.util.List;

public interface HomeBannerView {
    void showHomeBannerResult(List<HomeBannerData> homeBannerDataList);
    void showError();
}
