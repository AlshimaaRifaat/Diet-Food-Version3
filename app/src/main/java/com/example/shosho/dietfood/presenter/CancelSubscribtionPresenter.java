package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.CancelSubscribtionResponse;
import com.example.shosho.dietfood.model.CardResponse;
import com.example.shosho.dietfood.view.CancelSubscribtionView;
import com.example.shosho.dietfood.view.CardView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CancelSubscribtionPresenter {
    Context context;
    CancelSubscribtionView cancelSubscribtionView;

    public CancelSubscribtionPresenter(Context context, CancelSubscribtionView cancelSubscribtionView) {
        this.context = context;
        this.cancelSubscribtionView = cancelSubscribtionView;
    }

    public void getCancelSubscribtionResult(String UserToken)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );

        Service service= Client.getClient().create( Service.class );
        Call<CancelSubscribtionResponse> call=service.getCancelSubscribtionData( map );
        call.enqueue( new Callback<CancelSubscribtionResponse>() {
            @Override
            public void onResponse(Call<CancelSubscribtionResponse> call, Response<CancelSubscribtionResponse> response) {
                if (response.isSuccessful())
                {
                    cancelSubscribtionView.showCancelSubscribtionResult
                            (response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<CancelSubscribtionResponse> call, Throwable t) {
                Toast.makeText(context, "لا توجد بيانات", Toast.LENGTH_SHORT).show();
            }
        } );
    }
}



