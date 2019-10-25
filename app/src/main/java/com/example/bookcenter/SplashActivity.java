package com.example.bookcenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;



public class SplashActivity extends Activity {
    SharedPreferences prf;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        prf = getSharedPreferences("user_detail",MODE_PRIVATE);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                boolean isLogged=prf.getBoolean("isLogged", false);
                if(isLogged){
                    startActivity(new Intent(SplashActivity.this, TabsActivity.class));
                    finish();

                }
                else{

                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();

                }
            }
        },3000);

    }
}