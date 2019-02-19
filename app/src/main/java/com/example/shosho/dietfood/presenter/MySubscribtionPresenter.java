package com.example.shosho.dietfood.presenter;

import android.content.Context;
import android.widget.Toast;

import com.example.shosho.dietfood.api.Client;
import com.example.shosho.dietfood.api.Service;
import com.example.shosho.dietfood.model.MyOrdersResponse;
import com.example.shosho.dietfood.model.MySubscribtionResponse;
import com.example.shosho.dietfood.view.MyOrdersView;
import com.example.shosho.dietfood.view.MySubscribtionView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MySubscribtionPresenter {
    Context context;
    MySubscribtionView mySubscribtionView;

    public MySubscribtionPresenter(Context context, MySubscribtionView mySubscribtionView) {
        this.context = context;
        this.mySubscribtionView = mySubscribtionView;
    }

    public void getMySubscribtionsResult(String UserToken)
    {
        Map<String,String> map=new HashMap<>(  );
        map.put( "user_token",UserToken );

        Service service= Client.getClient().create( Service.class );
        Call<MySubscribtionResponse> call=service.getMySubscribtionData( map );
        call.enqueue( new Callback<MySubscribtionResponse>() {
            @Override
            public void onResponse(Call<MySubscribtionResponse> call, Response<MySubscribtionResponse> response) {
                if (response.isSuccessful())
                {
                    mySubscribtionView.showMySubscribtionsResult( response.body().getMessage() );
                    mySubscribtionView.showMySubscribtionsList( response.body().getMessage().getData() );
                }
            }

            @Override
            public void onFailure(Call<MySubscribtionResponse> call, Throwable t) {

                Toast.makeText( context, "لا توجد بيانات", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
