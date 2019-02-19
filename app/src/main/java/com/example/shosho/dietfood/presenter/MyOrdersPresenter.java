package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.MyOrdersResponse;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.view.MyOrdersView;
import com.example.shosho.dietfood.view.ProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrdersPresenter {
    Context context;
    MyOrdersView myOrdersView;

    public MyOrdersPresenter(Context context, MyOrdersView myOrdersView) {
        this.context = context;
        this.myOrdersView = myOrdersView;
    }

    public void getMyOrdersResult(String UserToken)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );

        Service service= Client.getClient().create( Service.class );
        Call<MyOrdersResponse> call=service.getMyOrdersData( map );
        call.enqueue( new Callback<MyOrdersResponse>() {
            @Override
            public void onResponse(Call<MyOrdersResponse> call, Response<MyOrdersResponse> response) {
                if (response.isSuccessful())
                {
                    myOrdersView.showMyOrdersResult( response.body().getMessage() );
                }
            }

            @Override
            public void onFailure(Call<MyOrdersResponse> call, Throwable t) {

                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
