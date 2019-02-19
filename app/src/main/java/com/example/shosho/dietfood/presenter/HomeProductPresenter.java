package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.home.HomeProductResponse;
import com.example.shosho.dietfood.view.HomeProductView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeProductPresenter {
    Context context;
    HomeProductView homeProductView;

    public HomeProductPresenter(Context context, HomeProductView homeProductView) {
        this.context = context;
        this.homeProductView = homeProductView;
    }
    public void getHomeProductList()
    {


        Service service = Client.getClient().create( Service.class );
        Call<HomeProductResponse> call = service.getHomeProductData( );
        call.enqueue( new Callback<HomeProductResponse>() {
            @Override
            public void onResponse(Call<HomeProductResponse> call, Response<HomeProductResponse> response) {
                if(response.isSuccessful()) {
                    homeProductView.showHomeProductResult(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<HomeProductResponse> call, Throwable t) {

                Toast.makeText(context, "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();

            }
        } );
    }
}