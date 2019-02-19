package com.example.shosho.dietfood.view;

import com.example.shosho.dietfood.model.PackageDetailsData;
import com.example.shosho.dietfood.model.PackageDetailsImageData;

import java.util.List;

public interface PackageDetailsView {
    void showPackageDetails(PackageDetailsData packageDetailsData);
    void showBannerImages(List<PackageDetailsImageData> packageDetailsImageDataList);
    void showError();
}
