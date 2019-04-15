package com.metacoders.astha.LoginRegSetupModule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.metacoders.astha.R;
import com.metacoders.astha.astha_manager;
import com.metacoders.astha.model.modelForShopOwner;

public class ShopOwnerRegistration extends AppCompatActivity {

    EditText ShopNameEditText , ShopAdressEditText , ShopPhoneEditText , ShopEmailEditText ,  MemberIdEditText , FbIdEditText
            , OwnerIdEditText ,OwnerPhoneEditText ,OwnerEmailEditText ,OwnerNIDEditText  ;

    String ShopName , ShopAdress , ShopPhone , ShopEmail ,  MemberId , FbId
            , OwnerId ,OwnerPhone ,OwnerEmail,OwnerNID  ;

    Button RegiserBtn ;

    FirebaseAuth mauth ;
    DatabaseReference mref ;
    String uid ;
    String postId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_owner_registration);

        try{

            getSupportActionBar().hide();

        }
        catch (NullPointerException e ){
           // getSupportActionBar() ;
        }

        mauth = FirebaseAuth.getInstance();
        uid = mauth.getUid();

        mref = FirebaseDatabase.getInstance().getReference("shopOwner");



        //setting up views

        ShopNameEditText = findViewById(R.id.input_shopName_setup) ;
        ShopAdressEditText  = findViewById(R.id.input_address_setup) ;
        ShopPhoneEditText = findViewById(R.id.input_phone_setup) ;
        ShopEmailEditText = findViewById(R.id.email_id) ;
        MemberIdEditText = findViewById(R.id.membership_id) ;
        FbIdEditText = findViewById(R.id.facebook_id) ;
        OwnerIdEditText = findViewById(R.id.owner_name_id) ;
        OwnerPhoneEditText = findViewById(R.id.Owner_ph_id) ;
        OwnerEmailEditText = findViewById(R.id.ownerEmail_id) ;
        OwnerNIDEditText  = findViewById(R.id.national_id) ;
        RegiserBtn  = findViewById(R.id.finish_btn) ;


        RegiserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                ShopName = ShopNameEditText.getText().toString();
                ShopAdress = ShopAdressEditText.getText().toString() ;
                ShopPhone= ShopPhoneEditText.getText().toString() ;
                ShopEmail = ShopEmailEditText.getText().toString() ;
                MemberId = MemberIdEditText.getText().toString() ;
                FbId = FbIdEditText.getText().toString();
                OwnerId = OwnerIdEditText.getText().toString() ;
                OwnerPhone = OwnerPhoneEditText.getText().toString() ;
                OwnerEmail = OwnerEmailEditText.getText().toString() ;
                OwnerNID = OwnerNIDEditText.getText().toString()  ;
                int len = ShopPhone.length() ;
                int lenth =OwnerPhone.length() ;


                if(TextUtils.isEmpty(ShopName)|| TextUtils.isEmpty(ShopAdress)|| TextUtils.isEmpty(ShopEmail)|| TextUtils.isEmpty(MemberId)
                        || TextUtils.isEmpty(FbId)||TextUtils.isEmpty(OwnerId)
                        || TextUtils.isEmpty(OwnerPhone)|| TextUtils.isEmpty(OwnerEmail)|| TextUtils.isEmpty(OwnerNID))
                {

                    Toast.makeText(getApplicationContext() , "Please Enter All The Value Properly" , Toast.LENGTH_SHORT)
                            .show();
                }
                else if (len != 11 || lenth!= 11){


                    Toast.makeText(getApplicationContext() , "Please Enter All The Number  Properly" , Toast.LENGTH_SHORT)
                            .show();

                }
                else {
                    showProgressDialog();

                    modelForShopOwner  model = new modelForShopOwner(ShopName , ShopAdress , ShopPhone , ShopEmail ,  MemberId , FbId
                            , OwnerId ,OwnerPhone ,OwnerEmail,OwnerNID, uid);

                    mref.child(uid).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {



                            Intent i = new Intent(getApplicationContext() , astha_manager.class);

                            startActivity(i);
                            finish();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hidePrgressDialogue();

                            Toast.makeText(getApplicationContext(), "Error : "+e.getMessage() , Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });








                }



            }
        });








    }
    private  void hidePrgressDialogue(){

        new AwesomeProgressDialog(this)
                .hide();
    }

    private void showProgressDialog() {
        new AwesomeProgressDialog(this)
                .setTitle(R.string.uploaginTitle)
                .setCancelable(false)
                .show();
    }

}
