package com.example.shosho.dietfood.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.activity.LoginActivity;
import com.example.shosho.dietfood.adapter.CardAdapter;
import com.example.shosho.dietfood.adapter.MySubscribtionAdapter;
import com.example.shosho.dietfood.model.MySubscribtionData;
import com.example.shosho.dietfood.model.MySubscribtionSpecialData;
import com.example.shosho.dietfood.presenter.CancelSubscribtionPresenter;
import com.example.shosho.dietfood.presenter.MySubscribtionPresenter;
import com.example.shosho.dietfood.view.CancelSubscribtionView;
import com.example.shosho.dietfood.view.DetailsCardView;
import com.example.shosho.dietfood.view.MySubscribtionView;
import com.example.shosho.dietfood.view.OnClickMinCardView;
import com.example.shosho.dietfood.view.OnDeleteIconClickView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MySubscribtionFragment extends Fragment implements
        MySubscribtionView,CancelSubscribtionView,SwipeRefreshLayout.OnRefreshListener{
MySubscribtionPresenter mySubscribtionPresenter;

ImageView imageView;
TextView packageName,inWay,arrived,subscribtionCapacity;

MySubscribtionAdapter mySubscribtionAdapter;
RecyclerView recyclerViewMySubcribtion;

CancelSubscribtionPresenter cancelSubscribtionPresenter;
TextView cancelSubscribtion;

NetworkConnection networkConnection;
SwipeRefreshLayout swipeRefreshLayout;
    public MySubscribtionFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_my_subscribtion, container, false );

        init();
        networkConnection=new NetworkConnection(getContext());
        mySubscribtionPresenter=new MySubscribtionPresenter( getContext(),this );
        mySubscribtionPresenter.getMySubscribtionsResult( SplashActivity.Login );

        cancelSubscribtionPresenter=new CancelSubscribtionPresenter(getContext(),this);
        cancelSubscribtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performCancelSubscribtion();
            }
        });
        swipRefresh();
        return view;
    }

    private void swipRefresh() {
        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_green_dark );
        swipeRefreshLayout.setEnabled( true );
        swipeRefreshLayout.setOnRefreshListener( this );
        swipeRefreshLayout.post( new Runnable() {
            @Override
            public void run() {
                if(networkConnection.isNetworkAvailable( getContext() ))
                {
                    swipeRefreshLayout.setRefreshing( true );
                    mySubscribtionPresenter.getMySubscribtionsResult( SplashActivity.Login );
                   // cancelSubscribtionPresenter.getCancelSubscribtionResult(SplashActivity.Login);
                }
            }
        } );
    }

    private void performCancelSubscribtion() {
        if(SplashActivity.Login==null){
            Toast.makeText(getContext(), "سجل دخولك أولا!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0,0);
        }else {
            cancelSubscribtionPresenter.getCancelSubscribtionResult(SplashActivity.Login);
        }
       // Toast.makeText(getContext(), "تم الغاء الاشتراك فى الباقة بنجاح!", Toast.LENGTH_SHORT).show();
    }

    private void init() {
        imageView=view.findViewById( R.id.my_subscribtion_image );
        packageName=view.findViewById( R.id.my_subscribtion_name );
        inWay=view.findViewById( R.id.my_subscribtion_text_in_way ) ;
        arrived=view.findViewById( R.id.my_subscribtion_text_arrived );
        subscribtionCapacity=view.findViewById( R.id.my_subscribtion_text_capacity );
        recyclerViewMySubcribtion=view.findViewById( R.id.my_subscribtion_recycler );
        cancelSubscribtion=view.findViewById(R.id.my_subscribtion_cancel_subscribtion);
        swipeRefreshLayout=view.findViewById( R.id.my_subscribtion_swip_refresh );

    }

    @Override
    public void showMySubscribtionsResult(MySubscribtionSpecialData mySubscribtionSpecialData) {
        Glide.with( getContext() ).load( "http://dietfoodksa.com/site/"
                +mySubscribtionSpecialData.getImage() ).into(imageView);
        //Toast.makeText(getContext(), "http://dietfoodksa.com/site"+mySubscribtionSpecialData.getImage(), Toast.LENGTH_LONG).show();
       // Typeface customFontMedium = Typeface.createFromAsset(getContext().getAssets(), "Fonts/SST Arabic Medium.ttf");

        packageName.setText(  mySubscribtionSpecialData.getPackageName()  );
        inWay.setText(String.valueOf( mySubscribtionSpecialData.getInWay() )  );


        arrived.setText(String.valueOf(mySubscribtionSpecialData.getArrived()));


        subscribtionCapacity.setText(String.valueOf(mySubscribtionSpecialData.getPeriod()));
        //type.setTypeface(customFontMedium);

        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void showMySubscribtionsList(List<MySubscribtionData> mySubscribtionDataList) {
        mySubscribtionAdapter=new MySubscribtionAdapter( getContext(),mySubscribtionDataList );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewMySubcribtion.setLayoutManager(linearLayoutManager);
        recyclerViewMySubcribtion.setAdapter( mySubscribtionAdapter );
        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void showCancelSubscribtionResult(String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void showError() {

    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            mySubscribtionPresenter.getMySubscribtionsResult( SplashActivity.Login );
            //cancelSubscribtionPresenter.getCancelSubscribtionResult(SplashActivity.Login);

        }else
        {
            Toast.makeText(getContext(), "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();
        }
    }
}
