package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ChangePasswordResponse;
import com.example.shosho.dietfood.model.UpdateProfileResponse;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.view.ChangePasswordView;
import com.example.shosho.dietfood.view.UpdateProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordPresenter {
    Context context;
    ChangePasswordView changePasswordView;

    public ChangePasswordPresenter(Context context, ChangePasswordView changePasswordView) {
        this.context = context;
        this.changePasswordView = changePasswordView;
    }

    public void getchangePasswordResult(String UserToken,String OldPassword, String NewPassword)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );
        map.put("old_password",OldPassword);
        map.put( "new_password",NewPassword );

        Service service=Client.getClient().create( Service.class );
        Call<ChangePasswordResponse> call=service.getChangePasswordData( map );
        call.enqueue( new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful())
                {
                    if(response.body().getMessage().equals( "Password changed successfully" )){
                        changePasswordView.showCorrectPasswordResult(response.body().getMessage() );
                    }else if(response.body().getMessage().equals("please enter password correct"))
                    {
                        changePasswordView.showwrongPasswordResult(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                changePasswordView.showError();
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
