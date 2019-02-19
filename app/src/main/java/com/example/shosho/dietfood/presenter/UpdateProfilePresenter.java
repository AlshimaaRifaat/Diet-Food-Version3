package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.model.UpdateProfileResponse;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.view.ProfileView;
import com.example.shosho.dietfood.view.UpdateProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfilePresenter {

    Context context;
    UpdateProfileView updateProfileView;

    public UpdateProfilePresenter(Context context, UpdateProfileView updateProfileView) {
        this.context = context;
        this.updateProfileView = updateProfileView;
    }

    public void getUpdatedProfileResult(User user)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",user.getUserToken() );
        map.put( "name",user.getName() );
        map.put( "email",user.getEmail() );
        map.put( "phone",user.getPhone() );
        map.put( "address",user.getAddress() );
        map.put( "image",user.getBase64() );
        Service service=Client.getClient().create( Service.class );
        Call<UpdateProfileResponse> call=service.getUpdatedProfileData( map );
        call.enqueue( new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful())
                {
                    if(response.body().getMessage().equals( "Profile successfully edited" )){
                    updateProfileView.showupdatedProfile(response.body().getMessage() );

                }
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                updateProfileView.showError();
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
