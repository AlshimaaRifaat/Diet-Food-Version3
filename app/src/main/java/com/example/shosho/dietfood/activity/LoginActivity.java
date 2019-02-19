package com.example.shosho.dietfood.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.fragment.HomeFragment;
import com.example.shosho.dietfood.fragment.ProfileFragment;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.ChangePasswordPresenter;
import com.example.shosho.dietfood.presenter.LoginPresenter;
import com.example.shosho.dietfood.view.ChangePasswordView;
import com.example.shosho.dietfood.view.LoginView;
import com.fourhcode.forhutils.FUtilsValidation;

public class LoginActivity extends AppCompatActivity implements LoginView{
    TextView registerText;
    TextView completeAsVisitor;

    EditText userEmail,userPassword;
    LoginPresenter loginPresenter;

    Button loginBtn;
    SharedPreferences.Editor sharedPref;

    TextView forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_login );

        init();
        loginPresenter=new LoginPresenter( this,this );

        sharedPref=this.getSharedPreferences("default",Context.MODE_PRIVATE ).edit();

        registerText.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent( LoginActivity.this,RegisterActivity.class );
                startActivity( intent );

            }
        } );

        completeAsVisitor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent( LoginActivity.this,HomeActivity.class );
               startActivity( intent );

            }
        } );

        loginBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
               // Toast.makeText( LoginActivity.this, "تم الدخول", Toast.LENGTH_SHORT ).show();

            }
        } );
        forgetPassword.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,ResetPasswordActivity.class);
                startActivity(intent);

            }
        } );
    }



    public static boolean isValidEmail(String Email)
    {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    private void performLogin() {

        FUtilsValidation.isEmpty( userEmail,"من فضلك ادخل بريدك الالكترونى" );
        FUtilsValidation.isEmpty( userPassword,"من فضك,ادخل كلمه المرور" );
        validateEmail();
        NetworkConnection networkConnection=new NetworkConnection( this );
        if (networkConnection.isNetworkAvailable( this ))
        {

            if(!userEmail.getText().toString().equals( "" )&&
                    !userPassword.getText().toString().equals("")&&
                    validateEmail())
            {
                loginPresenter.getLoginResult( userEmail.getText().toString(),
                        userPassword.getText().toString() );
            }

            else
            {
                Toast.makeText( this,"من فضلك فضلك املأ البيانات ", Toast.LENGTH_SHORT ).show();
            }

        }else {
            Toast.makeText( this, "تأكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();
        }
    }
    private Boolean validateEmail(){
        String EMAIL=userEmail.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            userEmail.setError("البريد الالكتروني خاطئ!");

            return false;
        }else if(!userEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            userEmail.setError(("البريد الالكتروني خاطئ!"));
            return false;
        }
        return true;
    }
    private void init() {
       registerText=findViewById( R.id.login_text_register);
      completeAsVisitor=findViewById( R.id.login_text_complete_as_visitor );
       userEmail=findViewById( R.id.login_edit_text_email );
        userPassword=findViewById( R.id.login_edit_text_password );

        loginBtn=findViewById( R.id.login_btn_login );
        forgetPassword=findViewById( R.id.login_text_forgot_password );


    }


    @Override
    public void showLoginResult(String UserToken) {
        sharedPref.putString( "login_to_home",UserToken );
        sharedPref.apply();
        SplashActivity.Login=UserToken;
        Intent intent=new Intent( LoginActivity.this,HomeActivity.class );
        startActivity( intent );
        this.finish();
    }



    @Override
    public void showError() {
        Toast.makeText( this, "فشل الدخول", Toast.LENGTH_SHORT ).show();
    }

}
