package com.example.shosho.dietfood.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
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

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.activity.HomeActivity;
import com.example.shosho.dietfood.activity.LoginActivity;
import com.example.shosho.dietfood.adapter.MealComponentAdapter;
import com.example.shosho.dietfood.model.AddToCardData;
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.model.MealComponentData;
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.model.home.HomeProductData;
import com.example.shosho.dietfood.presenter.AddToCardPresenter;
import com.example.shosho.dietfood.presenter.CardPresenter;
import com.example.shosho.dietfood.presenter.MealComponentPresenter;
import com.example.shosho.dietfood.view.AddToCardView;
import com.example.shosho.dietfood.view.CardView;
import com.example.shosho.dietfood.view.MealComponentView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsProducerFamilyFragment extends Fragment implements MealComponentView ,AddToCardView
,CardView{

    ImageView mealImage;
    TextView mealName,mealPrice;


    RecyclerView recyclerViewMealComponent;
    MealComponentAdapter mealComponentAdapter;
    MealComponentPresenter mealComponentPresenter;
    public  static ProducerFamilyData producerFamilyData;
    Button addToCard;
    public static AddToCardPresenter addToCardPresenter;

    public static   String UserToken;

    Bundle bundle;
    HomeProductData homeProductData;
    SharedPreferences sharedPref;
    public static String Login;

    CardPresenter cardPresenter;
    public DetailsProducerFamilyFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_details_producer_family, container, false);
        init();
        UserToken=SplashActivity.Login;
        sharedPref=getActivity().getSharedPreferences( "default",Context.MODE_PRIVATE );
        Login=sharedPref.getString( "login_to_home",null );

        addToCardPresenter=new AddToCardPresenter( getContext(), this);
        bundle=this.getArguments();

        if (bundle!=null)
        {
            producerFamilyData=bundle.getParcelable( "product" );


          //  homeProductData=bundle.getParcelable( "pd" );
            Glide.with(getContext()).load("http://dietfoodksa.com/site"+producerFamilyData.getImage()).into(mealImage);
            Typeface customFontMedium = Typeface.createFromAsset(getContext().getAssets(), "Fonts/SST Arabic Medium.ttf" );

            mealName.setText(producerFamilyData.getName());
            mealName.setTypeface( customFontMedium );

            mealPrice.setText(producerFamilyData.getPrice()+" SR");
            mealPrice.setTypeface( customFontMedium );
            mealComponentPresenter=new MealComponentPresenter(getContext(),this);
            mealComponentPresenter.getMealComponenttList(producerFamilyData.getId());
        }

        /*mealComponentPresenter.getMealComponenttList(Id);*/
        addToCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAddingToCard();
            }
        } );

        return view;
    }

    private void performAddingToCard() {
        if(Login==null){
            Toast.makeText(getContext(), "سجل دخولك أولا!", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getActivity(), LoginActivity.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0,0);
        }else {


            addToCardPresenter.getAddToCardResult(UserToken, producerFamilyData.getId());
        }


    }

    private void init() {
        mealImage=view.findViewById(R.id.details_prod_image);
        mealName=view.findViewById(R.id.details_prod_name);
        mealPrice=view.findViewById(R.id.details_prod_price);

        recyclerViewMealComponent=view.findViewById(R.id.details_prod_recycler);
        addToCard=view.findViewById( R.id.details_prod_btn_add_card );


    }

    @Override
    public void showMealComponentList(List<MealComponentData> mealComponentDataList) {

        mealComponentAdapter=new MealComponentAdapter( getContext(),mealComponentDataList );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewMealComponent.setLayoutManager(linearLayoutManager);
        recyclerViewMealComponent.setAdapter( mealComponentAdapter );
    }

    @Override
    public void showAddToCardResult(AddToCardData addToCardData) {
        //cardPresenter=new CardPresenter(getContext(),this);
        CardFragment.cardPresenter.getCardList(UserToken);
        Toast.makeText(getContext(), addToCardData.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showCardList(List<CardData> cardDataList) {
        if(cardDataList.size()>=0) {
            TabLayout.Tab tab = HomeActivity.tabLayout.getTabAt(1); // fourth tab
            View tabView = tab.getCustomView();
            TextView textView = tabView.findViewById(R.id.cart_notification);
            textView.setVisibility(View.VISIBLE);
            textView.setText(cardDataList.size()+"");

        }
    }

    @Override
    public void showPrice(String price) {

    }

    @Override
    public void showEmptyCard() {

    }

    @Override
    public void showError() {

    }


}
