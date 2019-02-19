package com.example.shosho.dietfood.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.ChangePasswordPresenter;
import com.example.shosho.dietfood.view.ChangePasswordView;
import com.fourhcode.forhutils.FUtilsValidation;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordView {
ChangePasswordPresenter changePasswordPresenter;
EditText oldPassword,newPassword;
Button changePassword;

SharedPreferences sharedPref;
public String UserToken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialog=new Dialog( this );
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable( false );
        setContentView(R.layout.activity_change_password);
        init();
        sharedPref=getSharedPreferences( "default", Context.MODE_PRIVATE );
        UserToken=sharedPref.getString( "login_to_home","" );
        changePasswordPresenter=new ChangePasswordPresenter(getApplicationContext(),this);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performChangePassword();
            }
        });
    }

    private void init() {
        oldPassword=findViewById(R.id.change_password_edit_text_password_old);
        newPassword=findViewById(R.id.change_password_edit_text_password_new);
        changePassword=findViewById(R.id.change_password_btn);
    }


    private void performChangePassword() {
        NetworkConnection networkConnection = new NetworkConnection(getApplicationContext());
        FUtilsValidation.isEmpty( oldPassword,"من فضلك ,ادخل كلمه المرور القديمة" );
        FUtilsValidation.isEmpty( newPassword,"من فضلك ,ادخل كلمه المرور الجديدة");
        if (networkConnection.isNetworkAvailable(getApplicationContext())) {
            if (!oldPassword.getText().toString().equals("") &&
                    !newPassword.getText().toString().equals("")) {

                changePasswordPresenter.getchangePasswordResult(UserToken, oldPassword.getText().toString(), newPassword.getText().toString());
            } else  {
                Toast.makeText(this, "من فضلك ادخل كلمه المرور", Toast.LENGTH_SHORT).show();
            }
        }

    }




    @Override
    public void showCorrectPasswordResult(String Message) {
        Toast.makeText(this, "تم تغيير كلمة المرور", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showwrongPasswordResult(String Message) {
        Toast.makeText(this, "من فضلك,ادخل كلمه المرور الصحيحة", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError() {

    }
}
