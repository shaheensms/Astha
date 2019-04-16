package com.metacoders.astha;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class astha_manager extends AppCompatActivity {


    private CardView home;
    private CardView new_Service;
    private CardView history;
    private CardView profile;
    IntentIntegrator QRSCANNER  ;
    String  QRSTRING ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astha_manager);

        home = findViewById(R.id.home_card);
        new_Service = findViewById(R.id.new_service_card);
        history = findViewById(R.id.history_card);
        profile = findViewById(R.id.profile_card);

        QRSCANNER = new IntentIntegrator(this) ;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(astha_manager.this, astha_manager.class);
                startActivity(intent);
            }
        });
        new_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //search for Qr Code ....
                                // Intent intent = new Intent(astha_manager.this, New_Service.class);
                               //  startActivity(intent);
                QRSCANNER.setOrientationLocked(false);
                QRSCANNER.initiateScan();

            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(astha_manager.this, Service_history.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(astha_manager.this, Profile_activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                QRSTRING = result.getContents();
                //  Toast.makeText(this, "Scanned: " + qrData, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext() , New_Service.class);
                i.putExtra("QRDATA",QRSTRING);
                startActivity(i);

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
