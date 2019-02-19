package com.example.shosho.dietfood.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.GPSTracker;
import com.example.shosho.dietfood.Gbs;
import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.activity.ChangePasswordActivity;
import com.example.shosho.dietfood.activity.CheckoutUIActivity;
import com.example.shosho.dietfood.activity.LoginActivity;
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.model.PostOrderResponse;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.MealComponentPresenter;
import com.example.shosho.dietfood.presenter.PostOrderPresenter;
import com.example.shosho.dietfood.view.PostOrderView;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostOrderFragment extends Fragment implements PostOrderView
        ,OnMapReadyCallback, com.google.android.gms.location.LocationListener
        , GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{
    PostOrderPresenter postOrderPresenter;

    EditText userPhone,userAddress;
    Button assignLocationBtn,orderBtn;

    NetworkConnection networkConnection;

    GoogleApiClient mGoogleApiClient;
    final int REQUEST_LOCATION_CODE = 99;
    LocationRequest locationReques;
    private GoogleMap googleMap;
    double latitude,longitude;
    List<Address> addresses;
    String addres;
    EditText ET_address;
    Gbs e;
    GPSTracker gbs;

    Bundle bundle;
    String TotalPrice;

    public PostOrderFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_post_order, container, false);
        init();

        bundle=this.getArguments();

        if (bundle!=null)
        {
            TotalPrice =bundle.getString( "totalPrice" );


          //  Toast.makeText(getContext(), TotalPrice, Toast.LENGTH_SHORT).show();


        }


        postOrderPresenter=new PostOrderPresenter(getContext(),this);
        networkConnection=new NetworkConnection(getContext());
        gbs=new GPSTracker(getContext());
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performOrder();
                Intent i = new Intent(getActivity(),CheckoutUIActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
        });
       /* assignLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //getLocation();
            }
        });*/

        e=new Gbs();
       //get_Intent();
        GetLocation();
        //T_Price.setText(price);
        //order();

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }


        checkLocationPermission();
        return view;
    }



    /*private void getLocation() {
        FUtilsValidation.isEmpty( userPhone,( "من فضلك اترك رقم الجوال!" ));
        FUtilsValidation.isEmpty( userAddress,( "من فضلك اكتب العنوان!"));
        if (networkConnection.isNetworkAvailable(getContext()))
        {
            if(!userPhone.getText().toString().equals("")&&
                    !userAddress.getText().toString().equals("")) {
                User user=new User();
                user.setUserToken(SplashActivity.Login);
                user.setPhone(userPhone.getText().toString());
                user.setAddress(userAddress.getText().toString());
                postOrderPresenter.getPostOrderResult(user);
            }
        }
    }*/

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_LOCATION_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        buildGoogleapiclint();
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED 
                                && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }

                        break;
                    case Activity.RESULT_CANCELED:

                        break;
                    default:
                        break;
                }
                break;
        }
    }

    private synchronized void buildGoogleapiclint() {
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    private void performOrder() {
        FUtilsValidation.isEmpty( userPhone,( "من فضلك اترك رقم الجوال!" ));
        FUtilsValidation.isEmpty( userAddress,( "من فضلك اكتب العنوان!"));
        if (networkConnection.isNetworkAvailable(getContext()))
        {
            if(SplashActivity.Login==null){
                Toast.makeText(getContext(), "سجل دخولك أولا!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);
            }
            if(!userPhone.getText().toString().equals("")&&
                    !userAddress.getText().toString().equals("")) {
                User user=new User();
                user.setUserToken(SplashActivity.Login);
                user.setPhone(userPhone.getText().toString());
                user.setAddress(userAddress.getText().toString());
                postOrderPresenter.getPostOrderResult(user);
            }
        }
    }
    private void init() {
        userPhone=view.findViewById(R.id.post_order_edit_text_phone);
        userAddress=view.findViewById(R.id.post_order_edit_text_address);
        assignLocationBtn=view.findViewById(R.id.post_order_btn_asign_location);
        orderBtn=view.findViewById(R.id.post_order_btn_add_order);
        ET_address=view.findViewById(R.id.post_order_edit_text_address);
    }

    @Override
    public void showPostOrderResult(String Message) {
        Toast.makeText(getContext(), Message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationReques = new LocationRequest();
        locationReques.setSmallestDisplacement(10);
        locationReques.setFastestInterval(10000);

        locationReques.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        int permissionCheck = ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationReques, this);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationReques);

            SettingsClient client = LocationServices.getSettingsClient(getActivity());
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
            task.addOnFailureListener((getActivity()), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    if (e instanceof ResolvableApiException) {
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) e;
                            resolvable.startResolutionForResult(getActivity(),
                                    REQUEST_LOCATION_CODE);
                        } catch (IntentSender.SendIntentException sendEx) {
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


        latitude = location.getLatitude();
        longitude = location.getLongitude();


        try {


            Geocoder geocoder = new Geocoder(getContext());
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            addres = addresses.get(0).getAddressLine(0);
            ET_address.setText(addres + "");

        } catch (IOException d) {
            d.printStackTrace();
        }

    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                new android.app.AlertDialog.Builder(getContext())
                        .setTitle("السماحية:")
                        .setMessage("افتح الGPS")
                        .setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_LOCATION_CODE);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION_CODE);
            }
            return false;
        } else {
            return true;
        }
    }
    public void GetLocation(){

        assignLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buildGoogleapiclint();
              checkLocationPermission();
            }

        });
    }

    @Override
    public void onPause() {
        super.onPause();
        gbs.RemoveCallback();
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        buildGoogleapiclint();
    }
}
