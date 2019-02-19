package com.example.shosho.dietfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.adapter.HomeProductAdapter;
import com.example.shosho.dietfood.adapter.MyOrdersAdapter;
import com.example.shosho.dietfood.model.MyOrdersData;
import com.example.shosho.dietfood.model.OrderDetailsData;
import com.example.shosho.dietfood.presenter.HomeProductPresenter;
import com.example.shosho.dietfood.presenter.MyOrdersPresenter;
import com.example.shosho.dietfood.view.MyOrdersView;
import com.example.shosho.dietfood.view.OnClickOrderDetailsView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrdersFragment extends Fragment implements MyOrdersView,OnClickOrderDetailsView {

    NetworkConnection networkConnection;
    RecyclerView recyclerViewMyOrders;
    MyOrdersAdapter myOrdersAdapter;
    MyOrdersPresenter myOrdersPresenter;


    public MyOrdersFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_my_orders, container, false );
        init();
        networkConnection=new NetworkConnection( getContext() );
        myOrdersPresenter=new MyOrdersPresenter( getContext(),this );
        myOrdersPresenter.getMyOrdersResult(SplashActivity.Login);
        return view;
    }

    private void init() {
        recyclerViewMyOrders=view.findViewById(R.id.my_orders_recycler);
    }

    @Override
    public void showMyOrdersResult(List<MyOrdersData> myOrdersDataList) {
        myOrdersAdapter=new MyOrdersAdapter( getContext(),myOrdersDataList );
        myOrdersAdapter.onClick(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewMyOrders.setLayoutManager(linearLayoutManager);
        recyclerViewMyOrders.setAdapter( myOrdersAdapter );
    }

    @Override
    public void showError() {

    }




    @Override
    public void showOrderDetails(MyOrdersData myOrdersData) {
        DetailsMyOrdersFragment detailsMyOrdersFragment=new DetailsMyOrdersFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable( "meal",myOrdersData );
        detailsMyOrdersFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.my_orders_frame_container,detailsMyOrdersFragment)
                .addToBackStack(null).commit();
    }
}
