package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.register.RegisterResponse;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.view.RegisterView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {
    Context context;
    RegisterView registerView;

    public RegisterPresenter(Context context, RegisterView registerView) {
        this.context = context;
        this.registerView = registerView;
    }
    public void getRegisterResult(User user)
    {
        HashMap<String,String> hashMap=new HashMap<>(  );
        hashMap.put( "name",user.getName() );
        hashMap.put( "email",user.getEmail() );
        hashMap.put( "password",user.getPassword() );
        hashMap.put( "address",user.getAddress() );
        hashMap.put( "phone",user.getPhone() );
        hashMap.put( "image",user.getBase64() );

        Service service=Client.getClient().create( Service.class );
        Call<RegisterResponse> call=service.getRegisterData( hashMap );

        call.enqueue( new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful())
                {
                    if (response.body().getMessage().equals("The email has already been taken."))
                    {
                        registerView.showEmailAlreadyToken(response.message());
                    }else {
                        registerView.showRegisterResult();
                    }



                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                registerView.showError(  );
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
