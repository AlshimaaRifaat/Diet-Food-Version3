package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.LoginResponse;
import com.example.shosho.dietfood.view.LoginView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    Context context;
    LoginView loginView;

    public LoginPresenter(Context context, LoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }

    public void getLoginResult(String Email,String Password)
    {
        HashMap<String,String> hashMap=new HashMap<>(  );
        hashMap.put( "email",Email );
        hashMap.put( "password",Password );

        Service service=Client.getClient().create( Service.class );
        Call<LoginResponse> call=service.getLoginData( hashMap );

        call.enqueue( new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful())
                {

                        loginView.showLoginResult( response.body().getMessage().getUserToken() );

                }else
                {
                    loginView.showError();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginView.showError(  );
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
