package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.MealComponentResponse;
import com.example.shosho.dietfood.model.PackageDetailsResponse;
import com.example.shosho.dietfood.view.MealComponentView;
import com.example.shosho.dietfood.view.PackageDetailsView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageDetailsPresenter {
    Context context;
    PackageDetailsView packageDetailsView;

    public PackageDetailsPresenter(Context context, PackageDetailsView packageDetailsView) {
        this.context = context;
        this.packageDetailsView = packageDetailsView;
    }

    public void getPackageDetailsResult(String Type)
    {
        Map<String,String> map=new HashMap<>();
        map.put("type",Type);
        Service service = Client.getClient().create( Service.class );
        Call<PackageDetailsResponse> call = service.getPackageDetailsData(map );
        call.enqueue( new Callback<PackageDetailsResponse>() {
            @Override
            public void onResponse(Call<PackageDetailsResponse> call, Response<PackageDetailsResponse> response) {
                if(response.isSuccessful()) {
                    packageDetailsView.showPackageDetails(response.body().getMessage());
                    packageDetailsView.showBannerImages(response.body().getMessage().getImagePackages());
                }
            }

            @Override
            public void onFailure(Call<PackageDetailsResponse> call, Throwable t) {

                Toast.makeText(context, "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();

            }
        } );
    }
}