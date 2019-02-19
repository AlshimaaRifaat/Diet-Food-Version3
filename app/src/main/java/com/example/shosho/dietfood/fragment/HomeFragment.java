package com.example.shosho.dietfood.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.adapter.HomeBannerAdapter;
import com.example.shosho.dietfood.adapter.HomeProductAdapter;
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.model.home.HomeBannerData;
import com.example.shosho.dietfood.model.home.HomeProductData;
import com.example.shosho.dietfood.presenter.HomeBannerPresenter;
import com.example.shosho.dietfood.presenter.HomeProductPresenter;
import com.example.shosho.dietfood.view.DetailsHomeProductView;
import com.example.shosho.dietfood.view.HomeBannerView;
import com.example.shosho.dietfood.view.HomeProductView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements
        HomeProductView,HomeBannerView,DetailsHomeProductView,SwipeRefreshLayout.OnRefreshListener {
ImageView producerFamilies,subscribtions,consultation;

    NetworkConnection networkConnection;
    RecyclerView recyclerViewHomeProduct;
    HomeProductAdapter homeProductAdapter;
    HomeProductPresenter homeProductPresenter;


    RecyclerView recyclerViewHomeBanner;
    HomeBannerAdapter homeBannerAdapter;
    HomeBannerPresenter homeBannerPresenter;

    int position;
    List<HomeBannerData> banners =new ArrayList();
    boolean end;

    RelativeLayout relativeLayoutFamily;
    RelativeLayout relativeLayoutSubscribtion;
    RelativeLayout relativeLayoutConsultation;

    private SwipeRefreshLayout swipeRefreshLayout;


    public HomeFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_home, container, false );
        getActivity().getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        init();
        homeBannerPresenter=new HomeBannerPresenter(getContext(),this);
        Banner();
        networkConnection=new NetworkConnection( getContext() );
        homeProductPresenter=new HomeProductPresenter( getContext(),this );
        homeProductPresenter.getHomeProductList();



        swipRefresh();
        int currentApiVersion = Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if(currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getActivity().getWindow().getDecorView();
            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(flags);
                    }
                }
            });
        }

        relativeLayoutFamily.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           getFragmentManager().beginTransaction().replace(R.id.home_frame_container
            ,new ProducerFamilyFragment()).addToBackStack(null).commit();


            }
        } );
        relativeLayoutSubscribtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.home_frame_container,
                        new MonthlySubscribtionsFragment()).addToBackStack(null).commit();
            }
        });
        relativeLayoutConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.home_frame_container,
                        new PaidConsultationFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }

    private void Banner() {

        homeBannerPresenter.getHomeBannerResult();
    }

    private void init() {
        /*producerFamilies=view.findViewById( R.id.home_nav_producer_fam );*/
      /*  subscribtions=view.findViewById( R.id.home_nav_subscribtions );
        consultation=view.findViewById( R.id.home_nav_consultation );*/
        recyclerViewHomeProduct=view.findViewById(R.id.home_recycler_product);
        recyclerViewHomeBanner=view.findViewById(R.id.home_recycler_banner);
        relativeLayoutFamily=view.findViewById(R.id.relative_fam);
        relativeLayoutSubscribtion=view.findViewById(R.id.relative_subscribe);
        relativeLayoutConsultation=view.findViewById(R.id.relative_consult);
        swipeRefreshLayout=view.findViewById( R.id.home_swip_refresh );
    }


    @Override
    public void showHomeProductResult(List<HomeProductData> homeProductDataList) {

        homeProductAdapter=new HomeProductAdapter( getContext(),homeProductDataList );
        homeProductAdapter.onClick(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerViewHomeProduct.setLayoutManager(gridLayoutManager);
        recyclerViewHomeProduct.setAdapter( homeProductAdapter );
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void showHomeBannerResult(List<HomeBannerData> homeBannerDataList) {
        banners=homeBannerDataList;
        homeBannerAdapter=new HomeBannerAdapter( getContext(),homeBannerDataList );
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager( getContext() );
        linearLayoutManager.setOrientation( LinearLayoutManager.HORIZONTAL );
        recyclerViewHomeBanner.setLayoutManager( linearLayoutManager );
        recyclerViewHomeBanner.setAdapter( homeBannerAdapter );

        if(banners.size()>1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate( new AutoScrollTask(), 4000, 7000 );
        }
        swipeRefreshLayout.setRefreshing( false );
    }


    @Override
    public void showError() {
        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void showHomeProductDetails(HomeProductData homeProductData) {

        DetailsProducerFamilyFragment detailsProducerFamilyFragment=new DetailsProducerFamilyFragment();
        Bundle bundle=new Bundle();
        bundle.putParcelable("product", homeProductData );
        detailsProducerFamilyFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.home_frame_container,
                detailsProducerFamilyFragment).addToBackStack(null).commit();
    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            homeBannerPresenter.getHomeBannerResult();
            homeProductPresenter.getHomeProductList();

        }else
        {
            Toast.makeText(getContext(), "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();
        }
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
                    homeBannerPresenter.getHomeBannerResult();
                    homeProductPresenter.getHomeProductList();
                }
            }
        } );
    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            try {
                if (position == banners.size() - 1) {
                    end = true;
                } else if (position == 0) {
                    end = false;
                }

                if (!end) {
                    position++;
                } else {
                    position--;
                }
                recyclerViewHomeBanner.smoothScrollToPosition( position );
            }catch (Exception e)
            {

            }
        }
    }
}
