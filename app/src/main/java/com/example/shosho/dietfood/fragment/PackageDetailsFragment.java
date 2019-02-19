package com.example.shosho.dietfood.fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.activity.LoginActivity;
import com.example.shosho.dietfood.adapter.HomeBannerAdapter;
import com.example.shosho.dietfood.adapter.PackageDetailsBannerAdapter;
import com.example.shosho.dietfood.model.PackageDetailsData;
import com.example.shosho.dietfood.model.PackageDetailsImageData;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.PackageDetailsPresenter;
import com.example.shosho.dietfood.presenter.SubscribtionPresenter;
import com.example.shosho.dietfood.view.PackageDetailsView;
import com.example.shosho.dietfood.view.SubscribtionView;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class PackageDetailsFragment extends Fragment implements PackageDetailsView ,SubscribtionView {

    TextView name, price,type, details;
    PackageDetailsPresenter packageDetailsPresenter;

    List<PackageDetailsImageData> banners = new ArrayList();
    PackageDetailsBannerAdapter packageDetailsBannerAdapter;
    RecyclerView recyclerViewBanner;

    int position;
    boolean end;

    SubscribtionPresenter subscribtionPresenter;
    Button subscribeBtn;
    String UserToken;
    PackageDetailsData packageDetailsData;
    public PackageDetailsFragment() {
        // Required empty public constructor
    }

    String PackageType;


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_package_details, container, false);
        init();

        UserToken=SplashActivity.Login;
        subscribtionPresenter=new SubscribtionPresenter( getContext(),this );


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            PackageType = bundle.getString("type");

         /*   Image=bundle.getString("image");
            Name=bundle.getString("name");
            Price=bundle.getString("price");
            Id=bundle.getString( "id" );

         */
            packageDetailsPresenter = new PackageDetailsPresenter(getContext(), this);
            packageDetailsPresenter.getPackageDetailsResult(PackageType);
        }

subscribeBtn.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        performSubscribtion();
    }
} );
        return view;
    }

    private void performSubscribtion() {
        NetworkConnection networkConnection=new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext()))
        {
            if(UserToken==null){
                Toast.makeText(getContext(), "سجل دخولك أولا!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }else {
                //Toast.makeText( getContext(), UserToken+"id  "+packageDetailsData.getId(), Toast.LENGTH_SHORT ).show();
                subscribtionPresenter.getSubscribtionResult( UserToken,packageDetailsData.getId() );
            }

        }else
        {
            Toast.makeText( getContext(), "تأكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();
        }
    }

    private void init() {

        name = view.findViewById(R.id.package_details_prod_name);
        price = view.findViewById(R.id.package_details_prod_price);
        details = view.findViewById(R.id.package_details_prod_details);
        recyclerViewBanner=view.findViewById(R.id.package_details_recycler_banner);
        type=view.findViewById(R.id.package_details_type);
        subscribeBtn=view.findViewById( R.id.package_details_btn_subscribe );
    }

    @Override
    public void showPackageDetails(PackageDetailsData packageDetailsData) {
        this.packageDetailsData=packageDetailsData;
        Typeface customFontMedium = Typeface.createFromAsset(getContext().getAssets(), "Fonts/SST Arabic Medium.ttf");
        name.setText(packageDetailsData.getName());


        price.setText(packageDetailsData.getPrice() + " ريال");
        price.setTypeface(customFontMedium);

        type.setText(packageDetailsData.getType());
        type.setTypeface(customFontMedium);

       details.setText( packageDetailsData.getDescription());
        details.setTypeface(customFontMedium);


    }

    @Override
    public void showBannerImages(List<PackageDetailsImageData> packageDetailsImageDataList) {
        banners = packageDetailsImageDataList;
        packageDetailsBannerAdapter = new PackageDetailsBannerAdapter(getContext(), packageDetailsImageDataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewBanner.setLayoutManager(linearLayoutManager);
        recyclerViewBanner.setAdapter(packageDetailsBannerAdapter);

        if (banners.size() > 1) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new AutoScrollTask(), 4000, 4000);
        }
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
                recyclerViewBanner.smoothScrollToPosition(position);
            } catch (Exception e) {

            }
        }
    }

    @Override
    public void showSubscribtionResult(String Message) {
        Toast.makeText( getContext(), Message, Toast.LENGTH_SHORT ).show();
    }

    @Override
    public void showError() {

    }
}