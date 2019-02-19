package com.example.shosho.dietfood.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shosho.dietfood.R;
import com.example.shosho.dietfood.SplashActivity;
import com.example.shosho.dietfood.adapter.CardAdapter;
import com.example.shosho.dietfood.fragment.CardFragment;
import com.example.shosho.dietfood.fragment.HomeFragment;
import com.example.shosho.dietfood.fragment.MyOrdersFragment;
import com.example.shosho.dietfood.fragment.ProfileFragment;
import com.example.shosho.dietfood.model.CardData;
import com.example.shosho.dietfood.presenter.CardPresenter;
import com.example.shosho.dietfood.view.CardView;
import com.example.shosho.dietfood.view.DetailsCardView;
import com.example.shosho.dietfood.view.OnClickMinCardView;
import com.example.shosho.dietfood.view.OnDeleteIconClickView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements CardView {
    public static TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView cartNotification;

    CardPresenter cardPresenter;
    String UserToken=SplashActivity.Login;

    public TextView textView;
    public void setNotification(int count){
        cartNotification.setText(Integer.toString(count) );
    }

    public int getNotification(){
        return Integer.parseInt(  cartNotification.getText().toString() );
    }

   // SharedPreferences sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        init();

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
       onSelectedTab();
      cardPresenter=new CardPresenter(this,this);
      cardPresenter.getCardList(UserToken);
        setupTabIcons();



    }

    private void setupTabIcons() {
        View view1=getLayoutInflater().inflate( R.layout.tab_icon_home,null );

        View view2=getLayoutInflater().inflate( R.layout.tab_icon_card,null );
        //cartNotification=view2.findViewById( R.id.cart_notification );

        View view3=getLayoutInflater().inflate( R.layout.tab_icon_my_orders,null );
        View view4=getLayoutInflater().inflate( R.layout.tab_icon_profile,null );
        tabLayout.getTabAt( 0 ).setCustomView( view1 );
        tabLayout.getTabAt( 1 ).setCustomView( view2 );
        tabLayout.getTabAt( 2 ).setCustomView( view3 );
        tabLayout.getTabAt( 3).setCustomView( view4 );
    }
    public void onSelectedTab(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
//                switch(tab.getPosition()) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
            }
        });
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag( new HomeFragment(),"" );
        viewPagerAdapter.addFrag( new CardFragment(),"" );
        viewPagerAdapter.addFrag( new MyOrdersFragment(),"" );
        viewPagerAdapter.addFrag( new ProfileFragment(),"" );

        viewPager.setAdapter( viewPagerAdapter );
    }

    private void init() {
        viewPager =findViewById(R.id.view_pager_container);
        tabLayout =findViewById(R.id.tabs);

    }

    @Override
    public void showCardList(List<CardData> cardDataList) {
        if(cardDataList.size()>=0) {

                TabLayout.Tab tab = tabLayout.getTabAt(1); // fourth tab
                View tabView = tab.getCustomView();
                textView = tabView.findViewById(R.id.cart_notification);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }



    @Override
    public void onStop() {
        super.onStop();
//        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }


}
