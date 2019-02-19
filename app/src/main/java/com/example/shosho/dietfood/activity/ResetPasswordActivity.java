package com.example.shosho.dietfood.activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.presenter.ResetPasswordPresenter;
import com.example.shosho.dietfood.view.ResetPasswordView;
import com.fourhcode.forhutils.FUtilsValidation;

public class ResetPasswordActivity extends AppCompatActivity implements ResetPasswordView {
ResetPasswordPresenter resetPasswordPresenter;
EditText userEmail;
Button resetPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Dialog dialog=new Dialog( this );
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable( false );
        setContentView(R.layout.activity_reset_password);
        init();
        resetPasswordPresenter=new ResetPasswordPresenter(getApplicationContext(),this);
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performResetPassword();
            }
        });

    }

    private void performResetPassword() {

        NetworkConnection networkConnection = new NetworkConnection(getApplicationContext());
        FUtilsValidation.isEmpty( userEmail,"من فضلك ,ادخل بريدك الالكترونى!" );

        if (networkConnection.isNetworkAvailable(getApplicationContext())) {
            if (!userEmail.getText().toString().equals("")) {

                resetPasswordPresenter.getResetPasswordResult( userEmail.getText().toString());
            }else
            {
                Toast.makeText(this, "من فضلك ,ادخل بريدك الالكترونى!", Toast.LENGTH_SHORT).show();
            }
        }else
        {
            Toast.makeText(this, "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();
        }
    }

    private void init() {
        userEmail=findViewById(R.id.reset_password_edit_text_email);
        resetPassword=findViewById(R.id.reset_password_btn);
    }

    @Override
    public void showCorrectResetPasswordResult(String Message) {
        Toast.makeText(this, "تم استرداد كلمه المرور", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showWrongResetPasswordResult(String Message) {

        Toast.makeText(this, "من فضلك, تحقق من ادخال البريد الالكترونى بشكل صحيح !", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showError() {

    }
}
