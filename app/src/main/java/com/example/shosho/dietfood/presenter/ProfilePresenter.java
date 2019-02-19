package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.ProfileResponse;
import com.example.shosho.dietfood.view.ProfileView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {
    Context context;
    ProfileView profileView;

    public ProfilePresenter(Context context, ProfileView profileView) {
        this.context = context;
        this.profileView = profileView;
    }
   public void getProfileResult(String UserToken)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );

        Service service=Client.getClient().create( Service.class );
        Call<ProfileResponse> call=service.getProfileData( map );
        call.enqueue( new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful())
                {

                    profileView.showProfileResult( response.body().getMessage() );
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                profileView.showError();
                Toast.makeText( context, "لا يتوفر انترنت", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
