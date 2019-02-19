package com.example.shosho.dietfood.fragment;


import android.content.Context;
import android.content.SharedPreferences;
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

import com.bumptech.glide.Glide;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.activity.HomeActivity;
import com.example.shosho.dietfood.adapter.MealComponentAdapter;
import com.example.shosho.dietfood.model.AddToCardData;
import com.example.shosho.dietfood.model.MealComponentData;
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.model.home.HomeProductData;
import com.example.shosho.dietfood.presenter.AddToCardPresenter;
import com.example.shosho.dietfood.presenter.MealComponentPresenter;
import com.example.shosho.dietfood.view.AddToCardView;
import com.example.shosho.dietfood.view.MealComponentView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsHomeProductFragment /*extends Fragment implements MealComponentView,AddToCardView */{
/*

    ImageView mealImage;
    TextView mealName,mealPrice;


    RecyclerView recyclerViewMealComponent;
    MealComponentAdapter mealComponentAdapter;
    MealComponentPresenter mealComponentPresenter;


    Button addToCard;
    public static AddToCardPresenter addToCardPresenter;
    SharedPreferences sharedPref;
    public String UserToken;

    Bundle bundle;
    HomeProductData homeProductData;
    ProducerFamilyData producerFamilyData;

    View view;

    public DetailsHomeProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_details_home_product, container, false );
        init();
        sharedPref=this.getActivity().getSharedPreferences( "default", Context.MODE_PRIVATE );
        UserToken=sharedPref.getString( "login_to_home","" );
        addToCardPresenter=new AddToCardPresenter( getContext(), this);
        bundle=this.getArguments();

        if (bundle!=null)
        {
            homeProductData=bundle.getParcelable( "product" );


            //  homeProductData=bundle.getParcelable( "pd" );
            Glide.with(getContext()).load("http://dietfoodksa.com/site"+homeProductData.getImage()).into(mealImage);
            Typeface customFontMedium = Typeface.createFromAsset(getContext().getAssets(), "Fonts/SST Arabic Medium.ttf" );

            mealName.setText(homeProductData.getName());
            mealName.setTypeface( customFontMedium );

            mealPrice.setText(homeProductData.getPrice()+" SR");
            mealPrice.setTypeface( customFontMedium );
            mealComponentPresenter=new MealComponentPresenter(getContext(),this);
            mealComponentPresenter.getMealComponenttList(producerFamilyData.getId());
        }

        */
/*mealComponentPresenter.getMealComponenttList(Id);*//*

        addToCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performAddingToCard();
            }
        } );
        return view;
    }

    private void performAddingToCard() {
        Toast.makeText( getContext(), "تمت الاضافة الي السله بنجاح", Toast.LENGTH_SHORT ).show();

        addToCardPresenter.getAddToCardResult( UserToken,producerFamilyData.getId() );

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
        Toast.makeText( getContext(), addToCardData.getMessage()+" Cart Id:"+addToCardData.getCartId(), Toast.LENGTH_LONG).show();
        int oldCartNumber=((HomeActivity)this.getActivity()).getNotification();
        ((HomeActivity)this.getActivity()).setNotification( oldCartNumber+1 );
        CardFragment.cardPresenter.getCardList( UserToken );

    }

    @Override
    public void showError() {

    }

*/

}
