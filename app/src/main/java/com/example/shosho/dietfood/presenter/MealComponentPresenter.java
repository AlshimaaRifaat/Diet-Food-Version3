package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.MealComponentResponse;
import com.example.shosho.dietfood.view.MealComponentView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealComponentPresenter
{
    Context context;
    MealComponentView mealComponentView;

    public MealComponentPresenter(Context context, MealComponentView mealComponentView) {
        this.context = context;
        this.mealComponentView = mealComponentView;
    }

    public void getMealComponenttList(String Id)
    {
        Map<String,String> map=new HashMap<>();
        map.put("meal_food_id",Id);
        Service service = Client.getClient().create( Service.class );
        Call<MealComponentResponse> call = service.getMealComponentData(map );
        call.enqueue( new Callback<MealComponentResponse>() {
            @Override
            public void onResponse(Call<MealComponentResponse> call, Response<MealComponentResponse> response) {
                if(response.isSuccessful()) {
                    mealComponentView.showMealComponentList(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<MealComponentResponse> call, Throwable t) {

                Toast.makeText(context, "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();

            }
        } );
    }
}