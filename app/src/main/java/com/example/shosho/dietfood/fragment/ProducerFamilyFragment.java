package com.example.shosho.dietfood.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.adapter.ProducerFamilyAdapter;
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.presenter.ProducerFamilyPresenter;
import com.example.shosho.dietfood.view.DetailsProducerFamilyView;
import com.example.shosho.dietfood.view.ProducerFamilyView;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProducerFamilyFragment extends Fragment implements ProducerFamilyView
        ,DetailsProducerFamilyView {

    NetworkConnection networkConnection;
    RecyclerView recyclerViewProducerFamily;
    ProducerFamilyAdapter producerFamilyAdapter;
    ProducerFamilyPresenter producerFamilyPresenter;
    private ShimmerFrameLayout mShimmerViewContainer;

    public static String Id;

    public ProducerFamilyFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_producer_family, container, false );
        init();
        producerFamilyPresenter=new ProducerFamilyPresenter(getContext(),this);
        producerFamilyPresenter.getProducerFamilyList();
        return view;
    }

    private void init() {
        recyclerViewProducerFamily=view.findViewById(R.id.prod_family_recycler);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);

    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    @Override
    public void showProducerFamilyData(List<ProducerFamilyData> producerFamilyDataList) {

        producerFamilyAdapter=new ProducerFamilyAdapter( getContext(),producerFamilyDataList );
        producerFamilyAdapter.onClick(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewProducerFamily.setLayoutManager(gridLayoutManager);
        recyclerViewProducerFamily.setAdapter( producerFamilyAdapter );
        mShimmerViewContainer.setVisibility(View.GONE);

        /* */
    }

    /*@Override
    public void showMealComponentList(List<ProducerFamilyData> Id) {
        if(Id!=null){
            id= Id.get( 0 ).getId() ;
            DetailsProducerFamilyFragment detailsHomeProductFragment=new DetailsProducerFamilyFragment();
            Bundle bundle=new Bundle( );
            bundle.putString( "id",id);
            detailsHomeProductFragment.setArguments( bundle );
            Toast.makeText(getContext(), "idddd", Toast.LENGTH_SHORT).show();
            getFragmentManager().beginTransaction().add( R.id.home_frame_container,
                    detailsHomeProductFragment )
                    .addToBackStack( null).commit();

        }
    }*/


    @Override
    public void showError() {

    }


    @Override
    public void showProducerFamilyDetails(ProducerFamilyData producerFamilyData) {
        DetailsProducerFamilyFragment detailsHomeProductFragment=new DetailsProducerFamilyFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable( "product",producerFamilyData );
        detailsHomeProductFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.home_frame_container,detailsHomeProductFragment)
                .addToBackStack(null).commit();

    }
}
