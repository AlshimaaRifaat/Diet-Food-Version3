package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ChangePasswordResponse;
import com.example.shosho.dietfood.model.ResetPasswordResponse;
import com.example.shosho.dietfood.view.ChangePasswordView;
import com.example.shosho.dietfood.view.ResetPasswordView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordPresenter {
    Context context;
    ResetPasswordView resetPasswordView;

    public ResetPasswordPresenter(Context context, ResetPasswordView resetPasswordView) {
        this.context = context;
        this.resetPasswordView = resetPasswordView;
    }

    public void getResetPasswordResult( String Email)
    {
        Map<String,String> map=new HashMap<>(  );

        map.put( "email",Email );

        Service service= Client.getClient().create( Service.class );
        Call<ResetPasswordResponse> call=service.getResetPasswordData( map );
        call.enqueue( new Callback<ResetPasswordResponse>() {
            @Override
            public void onResponse(Call<ResetPasswordResponse> call, Response<ResetPasswordResponse> response) {
                if (response.isSuccessful())
                {
                    if(response.body().getMessage().equals( "We have e-mailed your password reset link!" )){
                        resetPasswordView.showCorrectResetPasswordResult(response.body().getMessage() );
                    }
                    else if(response.body().getStatus()==false){
                        resetPasswordView.showWrongResetPasswordResult( response.body().getMessage() );
                    }
                }
                else if(response.code()==404){
                    resetPasswordView.showWrongResetPasswordResult("hamda" );

                }
            }

            @Override
            public void onFailure(Call<ResetPasswordResponse> call, Throwable t) {
                resetPasswordView.showError();

                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
