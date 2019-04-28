package com.metacoders.astha.LoginRegSetupModule;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.metacoders.astha.R;
import com.metacoders.astha.astha_manager;

import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OtpTextView;
import ir.samanjafari.easycountdowntimer.CountDownInterface;
import ir.samanjafari.easycountdowntimer.EasyCountDownTextview;

public class Otp_handler extends AppCompatActivity {

    String phone ;


    EasyCountDownTextview circularCountdown ;
    private String verificationid;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private final static  int RC_SIGN_IN =2 ;
    FirebaseAuth.AuthStateListener mAuthListener ;
    SignInButton google_btn ;
    GoogleApiClient mGoogleApiClient ;
    private OtpTextView editText;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks ;
    Button signInBtn ;
    FirebaseUser mUser ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_handler);

        Intent i = getIntent();

        phone = i.getStringExtra("number");


        try{
            getSupportActionBar().hide();
        }
        catch (NullPointerException e ){

            Toast.makeText(getApplicationContext(), "" , Toast.LENGTH_SHORT).show();
        }

        circularCountdown = findViewById(R.id.easyCountDownTextview);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() !=null){
                    Intent i = new Intent(getApplicationContext() , ShopOwnerRegistration.class);
                    startActivity(i);
                }



            }
        };

        sendVerificationCode(phone);
        editText = findViewById(R.id.otp_view);
       // google_btn =  findViewById(R.id.imageView3);

        signInBtn = findViewById(R.id.verifyBtn);
        progressBar = findViewById(R.id.progressBar2_otp_Handler);
        //01648009475
//        google_btn.setVisibility(View.GONE);
        circularCountdown.setOnTick(new CountDownInterface() {
            @Override
            public void onTick(long time) {


            }

            @Override
            public void onFinish() {
                circularCountdown.setTime(0,0,0);
               // google_btn.setVisibility(View.VISIBLE);
            }
        });



        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editText.getOTP();

                if ((code.isEmpty() || code.length() < 6)){

                    //  editText.setError("Enter code...");
                    Toast.makeText(getApplicationContext() , "PLease Enter The 6 Digit Code Properly" , Toast.LENGTH_SHORT)
                            .show();
                }
             //   progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);

            }
        });

    }
    private void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationid, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Intent intent = new Intent(getApplicationContext(), ShopOwnerRegistration.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           // intent.putExtra("GOOGLE" , "Phone");
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(),"Error: "+ task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    private void sendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationid = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
               // progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
            else {

                Toast.makeText(getApplicationContext(),"Error: wrong Code  ", Toast.LENGTH_LONG).show();

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG).show();

        }
    };
}
