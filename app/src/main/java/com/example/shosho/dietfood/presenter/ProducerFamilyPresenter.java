package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ProducerFamilyResponse;
import com.example.shosho.dietfood.view.ProducerFamilyView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerFamilyPresenter {
    Context context;
    ProducerFamilyView producerFamilyView;

    public ProducerFamilyPresenter(Context context, ProducerFamilyView producerFamilyView) {
        this.context = context;
        this.producerFamilyView = producerFamilyView;
    }

    public void getProducerFamilyList()
    {


        Service service = Client.getClient().create( Service.class );
        Call<ProducerFamilyResponse> call = service.getProducerFamilyData( );
        call.enqueue( new Callback<ProducerFamilyResponse>() {
            @Override
            public void onResponse(Call<ProducerFamilyResponse> call, Response<ProducerFamilyResponse> response) {
                if(response.isSuccessful()) {
                    producerFamilyView.showProducerFamilyData(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ProducerFamilyResponse> call, Throwable t) {

                Toast.makeText(context, "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();

            }
        } );
    }

}
