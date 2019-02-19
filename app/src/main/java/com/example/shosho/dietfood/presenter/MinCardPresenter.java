package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.AddToCardResponse;
import com.example.shosho.dietfood.model.MinCardResponse;
import com.example.shosho.dietfood.view.AddToCardView;
import com.example.shosho.dietfood.view.MinCardView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MinCardPresenter {
    Context context;
    MinCardView minCardView;

    public MinCardPresenter(Context context, MinCardView minCardView) {
        this.context = context;
        this.minCardView = minCardView;
    }

    public void getMinCardResult(String UserToken, String Meal_foods_id)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );
        map.put( "meal_foods_id", Meal_foods_id);
        Service service=Client.getClient().create( Service.class );
        Call<MinCardResponse> call=service.getMinCardData( map );
        call.enqueue( new Callback<MinCardResponse>() {
            @Override
            public void onResponse(Call<MinCardResponse> call, Response<MinCardResponse> response) {
                if (response.isSuccessful())
                {
                    minCardView.showMinCardResult( response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<MinCardResponse> call, Throwable t) {
                minCardView.showError();
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
