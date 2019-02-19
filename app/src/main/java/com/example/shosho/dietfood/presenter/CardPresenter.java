package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.model.CardResponse;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.view.CardView;
import com.example.shosho.dietfood.view.ProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardPresenter {
    Context context;
    CardView cardView;

    public CardPresenter(Context context, CardView cardView) {
        this.context = context;
        this.cardView = cardView;
    }

    public void getCardList(String UserToken)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );

        Service service=Client.getClient().create( Service.class );
        Call<CardResponse> call=service.getCardData( map );
        call.enqueue( new Callback<CardResponse>() {
            @Override
            public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                if (response.code()==200) {

                        cardView.showPrice(String.valueOf(response.body().getMessage().getPrice()));
                        cardView.showCardList(response.body().getMessage().getData());


                }else if (response.code()==400)
                {
                    cardView.showEmptyCard();
                }
            }

            @Override
            public void onFailure(Call<CardResponse> call, Throwable t) {
                cardView.showError();
            }
        } );
    }
}



