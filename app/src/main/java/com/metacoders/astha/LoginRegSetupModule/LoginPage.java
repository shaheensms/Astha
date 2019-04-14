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

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.metacoders.astha.R;
import com.metacoders.astha.astha_manager;

public class LoginPage extends AppCompatActivity {

    EditText  phoneNumberIN ;
  Button  nextBtn ;
  FirebaseAuth mauth ;
  FirebaseUser muser  ;
    private String verificationid;

    private final static  int RC_SIGN_IN =2 ;
    FirebaseAuth.AuthStateListener mAuthListener ;
    SignInButton google_btn ;
    GoogleApiClient mGoogleApiClient ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpageactivity);

        mauth = FirebaseAuth.getInstance();


        google_btn = findViewById(R.id.google_login);
        phoneNumberIN =  findViewById(R.id.editTextPhone);
        nextBtn = findViewById(R.id.buttonContinue);


        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phonenumber ;

                phonenumber = phoneNumberIN.getText().toString();
                int len = phoneNumberIN.length();

                if(TextUtils.isEmpty(phonenumber))
                {

                    Toast.makeText(getApplicationContext()  , "Please Enter Your Phone ", Toast.LENGTH_SHORT).show();

                }
                else if(len==11 ){

                    phonenumber = "+88"+phonenumber ;

                    Intent i = new Intent(getApplicationContext() , Otp_handler.class);
                    i.putExtra("number", phonenumber);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext()  , "Please Enter Valid Phone Number", Toast.LENGTH_SHORT).show();

                }




            }

        });





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("143547199369-roo03l06g2i1jv14106v7ks7m2b7a66t.apps.googleusercontent.com")
                .requestEmail()
                .build();

        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signGoogle();

            }
        });


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {


                        //error
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }
    private  void firebaseAuthGoogle(GoogleSignInAccount account){

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken() , null);
        mauth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext(), astha_manager.class);
                            //i.putExtra("GOOGLE" , "GOOGLE");
                            startActivity(i);
                            finish();
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "Authentication Went Wrong WIth Google ",Toast.LENGTH_LONG).show();

                        }

                    }
                });



    }
    public void signGoogle(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent , RC_SIGN_IN);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {

                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthGoogle(account);
            } else {
                String e = result.getStatus().toString();
                Toast.makeText(getApplicationContext(), "Authentication WENT WRONG From Gmail" + e, Toast.LENGTH_LONG).show();


            }
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
        muser = mauth.getCurrentUser();
        if(muser!= null){
            Intent i = new Intent(getApplicationContext() , astha_manager.class);
            startActivity(i);
            finish();
        }

    }
}
