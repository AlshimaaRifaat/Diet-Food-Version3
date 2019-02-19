package com.example.shosho.dietfood.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.activity.ChangePasswordActivity;
import com.example.shosho.dietfood.model.ProfileData;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.ProfilePresenter;
import com.example.shosho.dietfood.presenter.UpdateProfilePresenter;
import com.example.shosho.dietfood.view.ProfileView;
import com.example.shosho.dietfood.view.UpdateProfileView;
import com.fourhcode.forhutils.FUtilsValidation;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements ProfileView,UpdateProfileView
        ,SwipeRefreshLayout.OnRefreshListener{
    private ImageView imageProfile;
    EditText userName,userEmail,userPhone,userAddress;

    ProfilePresenter profilePresenter;

    SharedPreferences sharedPref;

    TextView textEdit;
    UpdateProfilePresenter updateProfilePresenter;
    ProfileData userData;


    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE=5469;
    private final int PICK_IMAGE_REQUEST=71;
    public String encImage;


    public static String Login;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public String UserToken;

    private TextView changePassword;
    Button saveEdit;
    View view;
    TextView viewMySubscribtions;

    NetworkConnection networkConnection;
    SwipeRefreshLayout swipeRefreshLayout;
    User user;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_profile, container, false );
        init();
        networkConnection=new NetworkConnection(getContext());
        imageProfile.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectUserImageFromGallery();
            }
        } );
        sharedPref=this.getActivity().getSharedPreferences( "default", Context.MODE_PRIVATE );
        UserToken=sharedPref.getString( "login_to_home","" );
        profilePresenter=new ProfilePresenter( getContext(),this );
        profilePresenter.getProfileResult(UserToken);

        updateProfilePresenter=new UpdateProfilePresenter( getContext(),this );

        textEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performUpdateProfile();
            }
        } );

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);

            }
        });

        saveEdit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSaveUpdateProfile();
            }
        } );
        viewMySubscribtions.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace( R.id.profile_frame_container,
                        new MySubscribtionFragment()).addToBackStack( null ).commit();
            }
        } );
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
                    profilePresenter.getProfileResult(UserToken);

                }
            }
        } );
    }

    private void selectUserImageFromGallery() {
        Intent galleryIntent=new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST  );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode==PICK_IMAGE_REQUEST &&resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            Uri uri = data.getData();
            if(uri!=null)
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                    imageProfile.setImageBitmap( bitmap );
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
                    byte[] dat = bytes.toByteArray();
                    encImage = Base64.encodeToString(dat, Base64.DEFAULT);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText( getContext(), "failed", Toast.LENGTH_LONG ).show();
                }
        }
    }

    private void performUpdateProfile() {
        userName.setText( userData.getName() );
        userEmail.setText(userData.getEmail() );
        userPhone.setText( userData.getPhone() );
        userAddress.setText( userData.getAddress() );

        userName.setEnabled( true );
        userAddress.setEnabled( true );
        userPhone.setEnabled( true );
        userEmail.setEnabled( true );
        saveEdit.setVisibility( View.VISIBLE );
    }

    public void performSaveUpdateProfile(){
        NetworkConnection networkConnection=new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() ))
        {
            if(!userName.getText().toString().equals( "" )&&
                    !userPhone.getText().toString().equals("")&&
                    !userEmail.getText().toString().equals( "" )&&
                    !userAddress.getText().toString().equals("")&&
                    validateEmail())
            {
                 user=new User();
                user.setName(  userName.getText().toString() );
                user.setEmail(userEmail.getText().toString() );
                user.setPhone( userPhone.getText().toString() );
                user.setAddress( userAddress.getText().toString() );
                user.setUserToken(   UserToken );
                updateProfilePresenter.getUpdatedProfileResult( user );
                saveEdit.setVisibility( View.GONE );
                userName.setEnabled( false );
                userAddress.setEnabled( false );
                userPhone.setEnabled( false );
                userEmail.setEnabled( false );
            }
            else
            {
                Toast.makeText( getContext(),   "من فضلك املا البيانات الخاصه بك", Toast.LENGTH_SHORT ).show();
            }

        }
        else
        {
            Toast.makeText( getContext(), "تاكد من اتصالك بالانترنت"
                    , Toast.LENGTH_SHORT ).show();
        }


    }

    private boolean validateEmail() {
        String EMAIL=userEmail.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            userEmail.setError(("خطأ ف البريد الالكترونى"));

            return false;
        }else if(!userEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            userEmail.setError(("خطأ ف البريد الالكترونى"));
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String Email) {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }

    private void init() {
        imageProfile=view.findViewById( R.id.profile_image );
        userName=view.findViewById( R.id.profile_edit_text_name );
        userName.setEnabled( false );
        userEmail=view.findViewById( R.id.profile_edit_text_email );
        userEmail.setEnabled( false );
        userPhone=view.findViewById( R.id.profile_edit_text_phone );
        userPhone.setEnabled( false );
        userAddress=view.findViewById( R.id.profile_edit_text_address );
        userAddress.setEnabled( false );
        textEdit=view.findViewById( R.id.profile_text_edit );
        changePassword=view.findViewById(R.id.profile_text_change_password);
        saveEdit=view.findViewById( R.id.profile_btn_save_edit );
        viewMySubscribtions=view.findViewById( R.id.profile_text_view );
        swipeRefreshLayout=view.findViewById(R.id.profile_swip_refresh);
    }

    @Override
    public void showProfileResult(ProfileData profileData) {

        userData=profileData;
        Glide.with( getContext() ).load( "http://dietfoodksa.com/site"+profileData.getImage())
                .into( imageProfile );
        userName.setText("الاسم: "+ profileData.getName() );
        userEmail.setText("الميل: " +profileData.getEmail() );
        userPhone.setText("الجوال: "+ profileData.getPhone() );
        userAddress.setText( "العنوان: "+profileData.getAddress() );

        swipeRefreshLayout.setRefreshing( false );


    }


    @Override
    public void showupdatedProfile(String Message) {
        Toast.makeText( getContext(), "Profile updated successfully", Toast.LENGTH_SHORT ).show();
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
            profilePresenter.getProfileResult(UserToken);

        }else
        {
            Toast.makeText(getContext(), "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();
        }
    }
}
