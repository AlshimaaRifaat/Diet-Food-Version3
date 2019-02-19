package com.example.shosho.dietfood.fragment;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.presenter.PackageDetailsPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MonthlySubscribtionsFragment extends Fragment {

RelativeLayout strength,diet,healthy;
TextView strengthText,dietText,healthyText;

    public MonthlySubscribtionsFragment() {
        // Required empty public constructor
    }

View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_monthly_subscribtions, container, false );
        init();

        strength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetailsFragment packageDetailsFragment=new PackageDetailsFragment();

                Bundle bundle=new Bundle();
                bundle.putString("type","a1");
                packageDetailsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.home_frame_container,packageDetailsFragment)
                        .addToBackStack(null).commit();


            }
        });
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetailsFragment packageDetailsFragment=new PackageDetailsFragment();
                Bundle bundle=new Bundle();
                bundle.putString("type","a2");
                packageDetailsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.home_frame_container,packageDetailsFragment)
                        .addToBackStack(null).commit();

            }
        });
        healthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageDetailsFragment packageDetailsFragment=new PackageDetailsFragment();
                Bundle bundle=new Bundle();
                bundle.putString("type","a3");
                packageDetailsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.home_frame_container,packageDetailsFragment)
                        .addToBackStack(null).commit();

            }
        });
        Typeface customFontMedium = Typeface.createFromAsset(getContext().getAssets(), "Fonts/SST Arabic Medium.ttf");
        strengthText.setTypeface(customFontMedium);

        dietText.setTypeface(customFontMedium);
        healthyText.setTypeface(customFontMedium);
        return view;
    }

    private void init() {
        strength=view.findViewById(R.id.monthly_subscribe_relative1);
        diet=view.findViewById(R.id.monthly_subscribe_relative2);
        healthy=view.findViewById(R.id.monthly_subscribe_relative3);

        strengthText=view.findViewById(R.id.monthly_subscribe_title1);
        dietText=view.findViewById(R.id.monthly_subscribe_title2);
       healthyText=view.findViewById(R.id.monthly_subscribe_title3);
    }


}
