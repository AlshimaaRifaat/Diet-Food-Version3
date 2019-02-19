package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.home.HomeBannerResponse;
import com.example.shosho.dietfood.view.HomeBannerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeBannerPresenter {
    Context context;
    HomeBannerView homeBannerView;

    public HomeBannerPresenter(Context context, HomeBannerView homeBannerView) {
        this.context = context;
        this.homeBannerView = homeBannerView;
    }
    public void getHomeBannerResult()
    {
        Service service= Client.getClient().create( Service.class );
        Call<HomeBannerResponse> call=service.getHomeBannerData();
        call.enqueue(new Callback<HomeBannerResponse>() {
            @Override
            public void onResponse(Call<HomeBannerResponse> call, Response<HomeBannerResponse> response) {
                if(response.isSuccessful())
                {
                    homeBannerView.showHomeBannerResult(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<HomeBannerResponse> call, Throwable t) {
                Toast.makeText(context, "لا يتوفرانترنت", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
