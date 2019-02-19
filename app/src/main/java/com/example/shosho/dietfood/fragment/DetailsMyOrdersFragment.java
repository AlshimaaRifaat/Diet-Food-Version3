package com.example.shosho.dietfood.fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.adapter.MealComponentAdapter;
import com.example.shosho.dietfood.adapter.MyOrdersDetailsAdapter;
import com.example.shosho.dietfood.model.MyOrdersData;
import com.example.shosho.dietfood.model.OrderDetailsData;
import com.example.shosho.dietfood.presenter.AddToCardPresenter;
import com.example.shosho.dietfood.presenter.MealComponentPresenter;
import com.example.shosho.dietfood.presenter.MyOrdersDetailsPresenter;
import com.example.shosho.dietfood.view.OrderDetailsView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsMyOrdersFragment extends Fragment implements OrderDetailsView {

    RecyclerView recyclerViewOrderDetails;
    MyOrdersDetailsAdapter myOrdersDetailsAdapter;
    MyOrdersDetailsPresenter myOrdersDetailsPresenter;

   MyOrdersData myOrdersData;
    public static   String UserToken;
    Bundle bundle;
    public DetailsMyOrdersFragment() {
        // Required empty public constructor
    }

  View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details_my_orders, container, false);
        init();
        UserToken= SplashActivity.Login;
        bundle=this.getArguments();
        if (bundle!=null)
        {
            myOrdersData=bundle.getParcelable( "meal" );

          //  Toast.makeText(getContext(), UserToken+myOrdersData.getOrderId(), Toast.LENGTH_LONG).show();
            //  homeProductData=bundle.getParcelable( "pd" );

            myOrdersDetailsPresenter=new MyOrdersDetailsPresenter(getContext(),this);
            myOrdersDetailsPresenter.getOrderDetailsResult(myOrdersData.getOrderId(),UserToken);
        }
        return view;
    }

    private void init() {
        recyclerViewOrderDetails=view.findViewById(R.id.details_my_orders_recycler);

    }

    @Override
    public void showOrderDetailsResult(List<OrderDetailsData> orderDetailsDataList) {
        myOrdersDetailsAdapter=new MyOrdersDetailsAdapter( getContext(),orderDetailsDataList );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewOrderDetails.setLayoutManager(linearLayoutManager);
        recyclerViewOrderDetails.setAdapter( myOrdersDetailsAdapter );

    }
}
