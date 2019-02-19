package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.DeleteCardResponse;
import com.example.shosho.dietfood.model.MinCardResponse;
import com.example.shosho.dietfood.view.DeleteCardView;
import com.example.shosho.dietfood.view.MinCardView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteCardPresenter {
    Context context;
    DeleteCardView deleteCardView;

    public DeleteCardPresenter(Context context, DeleteCardView deleteCardView) {
        this.context = context;
        this.deleteCardView = deleteCardView;
    }

    public void getDeleteCardResult(String UserToken, String Meal_foods_id)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );
        map.put( "meal_foods_id", Meal_foods_id);
        Service service= Client.getClient().create( Service.class );
        Call<DeleteCardResponse> call=service.getDeleteCardData( map );
        call.enqueue( new Callback<DeleteCardResponse>() {
            @Override
            public void onResponse(Call<DeleteCardResponse> call, Response<DeleteCardResponse> response) {
                if (response.isSuccessful())
                {
                    deleteCardView.showDeleteCardResult( response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<DeleteCardResponse> call, Throwable t) {
                deleteCardView.showError();
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
