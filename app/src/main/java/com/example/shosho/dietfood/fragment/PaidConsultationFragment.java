package com.example.shosho.dietfood.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.activity.CheckoutUIActivity;
import com.example.shosho.dietfood.model.User;
import com.example.shosho.dietfood.presenter.PaidConsultationPresenter;
import com.example.shosho.dietfood.view.PaidConsultationView;
import com.fourhcode.forhutils.FUtilsValidation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaidConsultationFragment extends Fragment implements PaidConsultationView {
PaidConsultationPresenter paidConsultationPresenter;
EditText userName,userPhone,userEmail,userMsg;
Button sendBtn;

    public PaidConsultationFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_paid_consultation, container, false);
        init();
        paidConsultationPresenter=new PaidConsultationPresenter(getContext(),this);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSendingConsultation();
            }
        });
        return view;
    }
    public static boolean isValidEmail(String Email)
    {
        return !TextUtils.isEmpty( Email )&&  android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    private void performSendingConsultation() {
        FUtilsValidation.isEmpty( userName,"من فضلك ادخل اسمك"  );
        FUtilsValidation.isEmpty( userPhone,"من فضك,اكتب رقم التليفون الخاص بك" );
        FUtilsValidation.isEmpty( userEmail,"من فضلك ادخل بريدك الالكترونى" );
        FUtilsValidation.isEmpty( userMsg,"من فضك,اترك رسالتك" );

        validateEmail();
        NetworkConnection networkConnection=new NetworkConnection( getContext() );
        if (networkConnection.isNetworkAvailable( getContext() ))
        {
            if(!userName.getText().toString().equals("")&&
                    !userPhone.getText().toString().equals("")&&
                    !userEmail.getText().toString().equals( "" )&&
                    !userMsg.getText().toString().equals("")&&
                    validateEmail())
            {
                User user=new User();
                user.setName(userName.getText().toString());
                user.setPhone(userPhone.getText().toString());
                user.setEmail(userEmail.getText().toString());
                user.setMsg(userMsg.getText().toString());
                paidConsultationPresenter.getPaidConsultationResult(user);
            }

            else
            {
                Toast.makeText( getContext(),"من فضلك فضلك املأ البيانات ", Toast.LENGTH_SHORT ).show();
            }

        }else {
            Toast.makeText( getContext(), "تأكد من اتصالك بالانترنت", Toast.LENGTH_SHORT ).show();
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
        userName=view.findViewById(R.id.paid_consult_edit_text_name);
        userPhone=view.findViewById(R.id.paid_consult_edit_text_phone);
        userEmail=view.findViewById(R.id.paid_consult_edit_text_email);
        userMsg=view.findViewById(R.id.paid_consult_edit_text_msg);
        sendBtn=view.findViewById(R.id.paid_consult_btn_send);

    }

    @Override
    public void showPaidConsultationResult(String Msg) {
      //  Toast.makeText(getContext(), Msg, Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "تم ارسال البيانات", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getActivity(),CheckoutUIActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0,0);
    }

    @Override
    public void showError() {

    }
}
