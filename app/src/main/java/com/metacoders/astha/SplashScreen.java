package com.metacoders.astha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.metacoders.astha.LoginRegSetupModule.LoginPage;



public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = new Intent(this , LoginPage.class);
        startActivity(i);
        finish();


        /*
        getSupportActionBar().hide();
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)

                .withFullScreen()
                .withTargetActivity(Home_Activity.class)
                .withSplashTimeOut(2000)
                .withFooterText("\nBuild By MetaCoders")

                .withFullScreen()
                .withBackgroundResource(R.drawable.splash_background);


       // config.getHeaderTextView().setTextColor(android.graphics.Color.WHITE);
        config.getFooterTextView().setTextColor(android.graphics.Color.WHITE);
       // config.getAfterLogoTextView().setTextColor(android.graphics.Color.WHITE);
       // config.getBeforeLogoTextView().setTextColor(android.graphics.Color.WHITE);
        View view = config.create();


        setContentView(view);

*/

}}
