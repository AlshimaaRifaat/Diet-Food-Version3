package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.AddToCardResponse;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.view.AddToCardView;
import com.example.shosho.dietfood.view.ProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCardPresenter {
    Context context;
    AddToCardView addToCardView;

    public AddToCardPresenter(Context context, AddToCardView addToCardView) {
        this.context = context;
        this.addToCardView = addToCardView;
    }

    public void getAddToCardResult(String UserToken,String Meal_foods_id)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );
        map.put( "meal_foods_id", Meal_foods_id);
        Service service=Client.getClient().create( Service.class );
        Call<AddToCardResponse> call=service.getAddToCardData( map );
        call.enqueue( new Callback<AddToCardResponse>() {
            @Override
            public void onResponse(Call<AddToCardResponse> call, Response<AddToCardResponse> response) {
                if (response.isSuccessful())
                {

                    addToCardView.showAddToCardResult( response.body().getMessage() );
                }
            }

            @Override
            public void onFailure(Call<AddToCardResponse> call, Throwable t) {
                addToCardView.showError();
            }
        } );
    }
}
