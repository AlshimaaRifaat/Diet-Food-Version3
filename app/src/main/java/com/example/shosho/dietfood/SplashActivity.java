package com.example.shosho.dietfood;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.shosho.dietfood.activity.HomeActivity;
import com.example.shosho.dietfood.activity.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    /*ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    int[]images;
    String[] Names;
    String [] Descriptions;

    private static int CURRENT_PAGE=0;
    private static int NUM_PAGES=0;

   Button loginBtn,letsGoBtn;*/


    SharedPreferences sharedPref;
    public static String Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView( R.layout.activity_splash );


        sharedPref=getSharedPreferences( "default",Context.MODE_PRIVATE );
        Login=sharedPref.getString( "login_to_home",null );



        Thread timer=new Thread(  )
        {
            @Override
            public void run() {
                super.run();
                try {
                    sleep( 2000 );

                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }finally {

                    if (Login!=null)
                    {
                        Intent intent=new Intent( SplashActivity.this,HomeActivity.class);
                        startActivity( intent );
                    }else
                    {
                        Intent intent=new Intent( SplashActivity.this,LoginActivity.class);
                        startActivity( intent );
                    }
                    finish();
                }
            }
        };

        timer.start();

    }

}
