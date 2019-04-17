package com.metacoders.astha;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.metacoders.astha.model.modelForShopOwner;

public class Profile_activity extends AppCompatActivity {

    TextView shoppName , shopphone , shopmail,memberId , shopadress , userName , UseNID , UserPhone ;
    DatabaseReference mref ;
    FirebaseAuth mauth ;
    String uid ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();

        mref = FirebaseDatabase.getInstance().getReference("shopOwner");

        //Setting UP Views
        shoppName = findViewById(R.id.shopNameProfile);
        shopphone = findViewById(R.id.PhoneProfile);
        shopmail = findViewById(R.id.emailProfile);
        memberId = findViewById(R.id.membershipProfile);
        shopadress = findViewById(R.id.addressProfile);
        userName = findViewById(R.id.ownerNameProfile);
        UseNID = findViewById(R.id.NidProfile);
        UserPhone = findViewById(R.id.OnwerPhoneProfile);

        mref.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForShopOwner model = dataSnapshot.getValue(modelForShopOwner.class);

                shoppName.setText(model.getShopName());
                shopphone.setText(model.getShopPhone());
                shopmail.setText(model.getShopEmail());
                memberId.setText(model.getMemberId());
                shopadress.setText(model.getShopAdress());
                userName.setText(model.getOwnerId());
                UseNID.setText(model.getOwnerNID());
                UserPhone.setText(model.getOwnerPhone());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }
}
