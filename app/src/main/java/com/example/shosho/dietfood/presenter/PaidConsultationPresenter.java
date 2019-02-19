package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.PaidConsultationResponse;
import com.example.shosho.dietfood.model.PostOrderResponse;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.view.PaidConsultationView;
import com.example.shosho.dietfood.view.PostOrderView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaidConsultationPresenter {
    Context context;
    PaidConsultationView paidConsultationView;

    public PaidConsultationPresenter(Context context, PaidConsultationView paidConsultationView) {
        this.context = context;
        this.paidConsultationView = paidConsultationView;
    }

    public void getPaidConsultationResult(User user)
    {
        HashMap<String,String> hashMap=new HashMap<>(  );
        hashMap.put( "name",user.getName());
        hashMap.put( "email",user.getEmail() );
        hashMap.put( "phone",user.getPhone() );
        hashMap.put( "message",user.getMsg() );


        Service service= Client.getClient().create( Service.class );
        Call<PaidConsultationResponse> call=service.getPaidConsultationData( hashMap );

        call.enqueue( new Callback<PaidConsultationResponse>() {
            @Override
            public void onResponse(Call<PaidConsultationResponse> call, Response<PaidConsultationResponse> response) {
                if(response.isSuccessful())
                {
                    paidConsultationView.showPaidConsultationResult(response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<PaidConsultationResponse> call, Throwable t) {
                paidConsultationView.showError(  );
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}

