package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.model.SubscribtionResponse;
import com.example.shosho.dietfood.view.ProfileView;
import com.example.shosho.dietfood.view.SubscribtionView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribtionPresenter {
    Context context;
    SubscribtionView subscribtionView;

    public SubscribtionPresenter(Context context, SubscribtionView subscribtionView) {
        this.context = context;
        this.subscribtionView = subscribtionView;
    }

    public void getSubscribtionResult(String UserToken,String PackageId)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );
        map.put("package_id" ,PackageId );

        Service service=Client.getClient().create( Service.class );
        Call<SubscribtionResponse> call=service.getSubscribtionData( map );
        call.enqueue( new Callback<SubscribtionResponse>() {
            @Override
            public void onResponse(Call<SubscribtionResponse> call, Response<SubscribtionResponse> response) {
                if (response.isSuccessful())
                {

                    subscribtionView.showSubscribtionResult( response.body().getMessage() );
                }
            }

            @Override
            public void onFailure(Call<SubscribtionResponse> call, Throwable t) {

                Toast.makeText( context, "لا توجد بيانات", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
