package com.example.shosho.dietfood.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.dietfood.NetworkConnection;
import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.activity.HomeActivity;
import com.example.shosho.dietfood.adapter.CardAdapter;
import com.example.shosho.dietfood.model.AddToCardData;
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.model.CardSpecialData;
import com.example.shosho.dietfood.model.ProducerFamilyData;
import com.example.shosho.dietfood.presenter.AddToCardPresenter;
import com.example.shosho.dietfood.presenter.CardPresenter;
import com.example.shosho.dietfood.presenter.DeleteCardPresenter;
import com.example.shosho.dietfood.presenter.MinCardPresenter;
import com.example.shosho.dietfood.view.AddToCardView;
import com.example.shosho.dietfood.view.CardView;
import com.example.shosho.dietfood.view.DeleteCardView;
import com.example.shosho.dietfood.view.DetailsCardView;
import com.example.shosho.dietfood.view.MinCardView;
import com.example.shosho.dietfood.view.OnClickMinCardView;
import com.example.shosho.dietfood.view.OnDeleteIconClickView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment implements CardView ,AddToCardView
        ,DetailsCardView,MinCardView,OnClickMinCardView,DeleteCardView,OnDeleteIconClickView,SwipeRefreshLayout.OnRefreshListener {

    RecyclerView recyclerViewCard;
    CardAdapter cardAdapter;
    public static CardPresenter cardPresenter;
    AddToCardPresenter addToCardPresenter;
    TextView textTotalPrice;

    //public String UserToken;

    Bundle bundle;
    ProducerFamilyData producerFamilyData;


    MinCardPresenter minCardPresenter;

    DeleteCardPresenter deleteCardPresenter;

    Button paymentBtn;
    RelativeLayout totalPricerelativeLayout;

   // int TotalPrice=0;
    String TotalPrice;
    NetworkConnection networkConnection;
    SwipeRefreshLayout swipeRefreshLayout;

    SharedPreferences sharedPref;
    public static String Login;
    public CardFragment() {
        // Required empty public constructor
    }

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate( R.layout.fragment_card, container, false );
        init();
        networkConnection=new NetworkConnection(getContext());
        cardPresenter=new CardPresenter(getContext(),this);
        minCardPresenter=new MinCardPresenter( getContext(),this );

        bundle=this.getArguments();
        addToCardPresenter=new AddToCardPresenter(getContext(),this);
        deleteCardPresenter=new DeleteCardPresenter(getContext(),this);

        sharedPref=this.getActivity().getSharedPreferences( "default", Context.MODE_PRIVATE );
        Login=sharedPref.getString( "login_to_home",null );
       paymentBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               goToPostOrderPage();
           }
       });
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
                    cardPresenter.getCardList(Login);
                }
            }
        } );
    }


    private void init() {
        recyclerViewCard=view.findViewById(R.id.card_recycler);
        textTotalPrice=view.findViewById( R.id.card_text_total_price );
        paymentBtn=view.findViewById(R.id.card_btn_payment);
        totalPricerelativeLayout=view.findViewById(R.id.card_relative);
        swipeRefreshLayout=view.findViewById(R.id.card_swip_refresh);

    }



    @Override
    public void showCardList(List<CardData> cardDataList) {

       /* for (int i=0;i<cardDataList.size();i++)
        {
            TotalPrice += cardDataList.get( i ).getTotalPrice() ;
        }
        textTotalPrice.setText(String.valueOf(  TotalPrice)  );*/

        TabLayout.Tab tab = HomeActivity.tabLayout.getTabAt(1); // fourth tab
        View tabView = tab.getCustomView();
        TextView textView = tabView.findViewById(R.id.cart_notification);
        textView.setVisibility(View.VISIBLE);
        textView.setText(cardDataList.size()+"");


        cardAdapter=new CardAdapter( getContext(),cardDataList );
        cardAdapter.onClick( (DetailsCardView)this );
        cardAdapter.onClick( (OnClickMinCardView) this );
        cardAdapter.onClick( (OnDeleteIconClickView) this );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewCard.setLayoutManager(linearLayoutManager);
        recyclerViewCard.setAdapter( cardAdapter );
        totalPricerelativeLayout.setVisibility(View.VISIBLE);
        paymentBtn.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing( false );

    }

    @Override
    public void showPrice(String price) {
        TotalPrice=price;
        textTotalPrice.setText( price);
    }

    @Override
    public void showEmptyCard() {
        totalPricerelativeLayout.setVisibility(View.GONE);
        paymentBtn.setVisibility(View.GONE);


            TabLayout.Tab tab = HomeActivity.tabLayout.getTabAt(1); // fourth tab
            View tabView = tab.getCustomView();
            TextView textView = tabView.findViewById(R.id.cart_notification);
            textView.setVisibility(View.GONE);
        Toast.makeText(getContext(), "السله فارغة!", Toast.LENGTH_SHORT).show();

    }


    private void goToPostOrderPage() {
        PostOrderFragment postOrderFragment=new PostOrderFragment();
        Bundle bundle=new Bundle();
        bundle.putString( "totalPrice",TotalPrice);
        postOrderFragment.setArguments(bundle);
        getFragmentManager().beginTransaction().add(R.id.card_frame_container,postOrderFragment)
                .addToBackStack(null).commit();
        getFragmentManager().beginTransaction().replace(R.id.card_frame_container
                ,new PostOrderFragment()).addToBackStack(null).commit();
    }



    @Override
    public void showMinCardResult(String Message) {
        //Toast.makeText( getContext(), MySubscribtionSpecialData, Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void showAddToCardResult(AddToCardData addToCardData) {

        //Toast.makeText( getContext(), addToCardData.getMessage(), Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void showDeleteCardResult(String Message) {
        cardPresenter.getCardList( Login );
    }

    @Override
    public void showError() {

        swipeRefreshLayout.setRefreshing( false );
    }

    @Override
    public void showCardDetails(CardData cardData, CardAdapter.ViewHolder holder) {
        addToCardPresenter.getAddToCardResult( Login,cardData.getMealFoodId());
        holder.quantity.setText(  Integer.toString(  Integer.parseInt(  holder.quantity.getText().toString() )+1 ));
        holder.price.setText(Double.toString(  Double.parseDouble(holder.quantity.getText().toString())*Double.parseDouble(  cardData.getPrice()) )+" SR");
        /*int TotalPrice=Integer.parseInt(  textTotalPrice.getText().toString())+Integer.parseInt(  cardData.getPrice());
        textTotalPrice.setText( Integer.toString( TotalPrice ) );*/

        double TotalPrice=Double.parseDouble(  textTotalPrice.getText().toString())+Double.parseDouble(  cardData.getPrice());
        textTotalPrice.setText(String.valueOf(TotalPrice) );

    }

    @Override
    public void showOnClickMinCardResult(CardData cardData, CardAdapter.ViewHolder holder) {

        if(!holder.quantity.getText().toString().equals( "0" )) {
            minCardPresenter.getMinCardResult( SplashActivity.Login, cardData.getMealFoodId() );
            holder.quantity.setText( Integer.toString( Integer.parseInt( holder.quantity.getText().toString() ) - 1 ) );
            holder.price.setText( Double.toString( Double.parseDouble( holder.quantity.getText().toString() ) * Double.parseDouble( cardData.getPrice() ) ) + " SR" );
            Double TotalPrice=Double.parseDouble(  textTotalPrice.getText().toString())-Double.parseDouble(  cardData.getPrice());
            /*textTotalPrice.setText( Integer.toString( TotalPrice ) );*/
            textTotalPrice.setText(String.valueOf(TotalPrice) );
        }else
            Toast.makeText( getContext(), "لا يمكنك انقاص المزيد", Toast.LENGTH_SHORT ).show();

    }


    @Override
    public void showOnDeleteIconClickResult(String MealFoodId,int position) {
        deleteCardPresenter.getDeleteCardResult(SplashActivity.Login,MealFoodId);
        

    }

    @Override
    public void onRefresh() {
        if(networkConnection.isNetworkAvailable( getContext() ))
        {
            swipeRefreshLayout.setRefreshing( true );
            cardPresenter.getCardList(Login);

        }else
        {
            Toast.makeText(getContext(), "لا يتوفر انترنت", Toast.LENGTH_SHORT).show();
        }
    }
}