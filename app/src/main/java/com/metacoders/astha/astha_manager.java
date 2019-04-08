package com.metacoders.astha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class astha_manager extends AppCompatActivity {


    private CardView home;
    private CardView new_Service;
    private CardView history;
    private CardView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astha_manager);

        home = findViewById(R.id.home_card);
        new_Service = findViewById(R.id.new_service_card);
        history = findViewById(R.id.history_card);
        profile = findViewById(R.id.profile_card);

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
                Intent intent = new Intent(astha_manager.this, New_Service.class);
                startActivity(intent);
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
}
