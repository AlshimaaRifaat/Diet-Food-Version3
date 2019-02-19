package com.example.shosho.dietfood.presenter;

import android.content.Context;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.CardResponse;
import com.example.shosho.dietfood.model.CheckOut.CheckOutResponse;
import com.example.shosho.dietfood.view.CardView;
import com.example.shosho.dietfood.view.CheckOutView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckOutPresenter {
    Context context;
    CheckOutView checkOutView;

    public CheckOutPresenter(Context context, CheckOutView checkOutView) {
        this.context = context;
        this.checkOutView = checkOutView;
    }

    public void getCheckOutResult(String UserId,String Password,String EntityId,String Amount)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "userId",UserId );
        map.put( "password",Password );
        map.put( "entityId",EntityId );
        map.put( "amount",Amount );

        Service service= Client.getClient().create( Service.class );
        Call<CheckOutResponse> call=service.getCheckOutData( map );
        call.enqueue( new Callback<CheckOutResponse>() {
            @Override
            public void onResponse(Call<CheckOutResponse> call, Response<CheckOutResponse> response) {
                if (response.isSuccessful()) {

                    checkOutView.showCheckOutResult(response.body().getResult().getDescription());

                }
            }

            @Override
            public void onFailure(Call<CheckOutResponse> call, Throwable t) {
                checkOutView.showError();
            }
        } );
    }
}
