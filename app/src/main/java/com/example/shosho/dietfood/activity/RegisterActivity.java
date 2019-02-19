package com.example.shosho.dietfood.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.RegisterPresenter;
import com.example.shosho.dietfood.view.RegisterView;
import com.fourhcode.forhutils.FUtilsValidation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    Button registerButton;

    ImageView userImage;
    EditText userName,userEmail,userPhone,userAddress,userPassword;


    RegisterPresenter registerPresenter;

    public static int ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE=5469;
    private final int PICK_IMAGE_REQUEST=71;
    public String encImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_register );
        init();


//        checkPermission();

        userImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectUserImageFromGallery();
            }
        } );
        registerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent i = new Intent(getActivity(), HomeActivity.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0,0);*/
                PerformRegister();
            }
        } );
        registerPresenter=new RegisterPresenter( this,(RegisterView) this );
    }


    private void selectUserImageFromGallery() {
        Intent galleryIntent=new Intent( Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI );
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST  );
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            if (!Settings.canDrawOverlays(this))
            {
                Intent intent=new Intent( Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package"+"com.example.shosho.dietfood") );
                startActivityForResult(intent, ACTION_MANAGE_OVERLAY_PERMISSION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode==PICK_IMAGE_REQUEST &&resultCode==RESULT_OK &&data!=null &&data.getData()!=null)
        {
            Uri uri = data.getData();
            if(uri!=null)
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    userImage.setImageBitmap( bitmap );
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, bytes);
                    byte[] dat = bytes.toByteArray();
                    encImage = Base64.encodeToString(dat, Base64.DEFAULT);


                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText( this, "failed", Toast.LENGTH_LONG ).show();
                }
        }
    }

    private void PerformRegister() {
        FUtilsValidation.isEmpty( userName,"من فضلك ادخل اسمك"  );
        FUtilsValidation.isEmpty( userPhone,"من فضك,اكتب رقم التليفون الخاص بك" );
        FUtilsValidation.isEmpty( userEmail,"من فضلك ادخل بريدك الالكترونى" );
        FUtilsValidation.isEmpty( userAddress,"من فضك,اكتب عنوانك" );
        FUtilsValidation.isEmpty( userPassword,"من فضك,ادخل كلمه المرور" );
        validateEmail();
        NetworkConnection networkConnection=new NetworkConnection( this );
        if (networkConnection.isNetworkAvailable( this))
        {
            if(userPassword.getText().toString().length()<6)
            {
                Toast.makeText(this, "من فضلك ادخل كلمه المرور فيما لا يقل عن سته ارقام !", Toast.LENGTH_SHORT).show();
            }
            if (!userName.getText().toString().equals( "" )&&
                    !userPhone.getText().toString().equals( "" )&&
                    !userEmail.getText().toString().equals( "" )&&
                    !userAddress.getText().toString().equals( "" )&&
                    !userPassword.getText().toString().equals( "" )&&
                    FUtilsValidation.isLengthCorrect( userPassword.getText().toString(),6,16 )&&
                    validateEmail()) {
                User user=new User(  );
                user.setBase64( encImage );
                user.setName( userName.getText().toString() );
                user.setPhone( userPhone.getText().toString() );
                user.setEmail( userEmail.getText().toString() );
                user.setAddress( userAddress.getText().toString() );
                user.setPassword( userPassword.getText().toString() );
                registerPresenter.getRegisterResult( user );

            }else
            {
                Toast.makeText( this, "من فضلك ,املا البيانات الخاصه بك", Toast.LENGTH_SHORT ).show();
            }
        }else
        {
            Toast.makeText( this, "تأكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();
        }
    }

    private Boolean validateEmail() {
        String Email=userEmail.getText().toString().trim();
        if (Email.isEmpty()||!isValidEmail(Email)) {
            userEmail.setError( "البريد الالكتروني خاطئ" );
            return false;
        }else if(!userEmail.getText().toString().matches( "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+" ))
        {
            userEmail.setError("البريد الالكتروني خاطئ"   );
            return false;
        }
        return true;
    }

    private boolean isValidEmail(String Email) {
        return !TextUtils.isEmpty(Email) && android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }

    private void init() {
        registerButton=findViewById( R.id.register_btn_register );

        userImage=findViewById( R.id.register_image );
        userName=findViewById( R.id.register_edit_text_name );
        userPhone=findViewById( R.id.register_edit_text_phone);
        userEmail=findViewById( R.id.register_edit_text_email );
        userAddress=findViewById( R.id.register_edit_text_address);
        userPassword=findViewById( R.id.register_edit_text_password);


    }

    @Override
    public void showRegisterResult() {
        Toast.makeText( this, "تم التسجيل", Toast.LENGTH_SHORT ).show();
        Intent intent=new Intent( RegisterActivity.this,LoginActivity.class );
        startActivity( intent );
    }

    @Override
    public void showEmailAlreadyToken(String Message) {
        Toast.makeText(this, "The Email has already been token!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText( this, "فشل التسجيل", Toast.LENGTH_SHORT ).show();
    }
}
