package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.OrderDetailsResponse;
import com.example.shosho.dietfood.model.PackageDetailsResponse;
import com.example.shosho.dietfood.view.OrderDetailsView;
import com.example.shosho.dietfood.view.PackageDetailsView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersDetailsPresenter {
    Context context;
    OrderDetailsView orderDetailsView;

    public MyOrdersDetailsPresenter(Context context, OrderDetailsView orderDetailsView) {
        this.context = context;
        this.orderDetailsView = orderDetailsView;
    }

    public void getOrderDetailsResult(String OrderId,String UserToken)
    {
        Map<String,String> map=new HashMap<>();
        map.put("order_id",OrderId);
        map.put("user_token",UserToken);
        Service service = Client.getClient().create( Service.class );
        Call<OrderDetailsResponse> call = service.getMyOrderDetailsData(map );
        call.enqueue( new Callback<OrderDetailsResponse>() {
            @Override
            public void onResponse(Call<OrderDetailsResponse> call, Response<OrderDetailsResponse> response) {
                if(response.isSuccessful()) {
                    orderDetailsView.showOrderDetailsResult(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsResponse> call, Throwable t) {

                Toast.makeText(context, "لا يتوفر بيانات", Toast.LENGTH_SHORT).show();

            }
        } );
    }
}
