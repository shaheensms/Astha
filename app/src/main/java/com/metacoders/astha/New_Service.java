package com.metacoders.astha;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeErrorDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metacoders.astha.model.modelForShopOwner;
import com.metacoders.astha.model.produrctModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import id.zelory.compressor.Compressor;

public class New_Service extends AppCompatActivity {
    TextView textView ;

    String QRCODE="Default" ;
    String shopUid="sadfasdf"  ,qrCode="Default" ;
    String shopMail = "No Email" , shopNum , userPhone , productName , productModel , productComment , productWarranty , productBuyerName , shopname , shopadress ,purchageDate ,productPrice ;
    EditText pname , shopnumber , pwarran , pmodel , pbuyName , pcomment ,pprice, userphoneInput ;
    ImageView invoicePic ;
    Button fupload ;
    FirebaseAuth mauth ;

    private DatabaseReference UserRef  ;
    private StorageReference storageRef ;
    private Bitmap compressedImageFile;
    Uri postImageUri  = null ;
    private DatabaseReference userDb , postRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__service);

        try{
            getSupportActionBar().setTitle("Start Service");

        }
        catch (NullPointerException e ){

            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT)
                    .show();
        }

        Intent i = getIntent();
         QRCODE = i.getStringExtra("QRDATA");
         mauth = FirebaseAuth.getInstance();
         shopUid = mauth.getUid();



      //setting up views
        pname = (EditText)  findViewById(R.id.input_productName_upload);
        pwarran = (EditText)  findViewById(R.id.input_buyer_Warranty_upload);
        pmodel = (EditText)  findViewById(R.id.input_model_Num_upload);
        pbuyName = (EditText)  findViewById(R.id.input_buyer_name_upload);
        pcomment = (EditText)findViewById(R.id.input_buyer_Warranty_Commnet) ;
        shopnumber = (EditText)  findViewById(R.id.input_phone_shopupload);
        pprice = (EditText)findViewById(R.id.input_buyer_price);
        fupload = findViewById(R.id.Data_uploadBtn);
        invoicePic = findViewById(R.id.imageView_add_invoice);
        userphoneInput  = findViewById(R.id.input_buyer_phone);


        //setting up Database

        storageRef = FirebaseStorage.getInstance().getReference("invoicePic") ;
        UserRef  = FirebaseDatabase.getInstance().getReference("Products");
        userDb = FirebaseDatabase.getInstance().getReference("shopOwner").child(shopUid);



        invoicePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if (ContextCompat.checkSelfPermission(New_Service.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                        ActivityCompat.requestPermissions(New_Service.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        BringImagePicker();

                    }

                } else {

                    BringImagePicker();

                }

            }

        });




        fupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showLoadingDialogue();

                productName = pname.getText().toString() ;
                productModel = pmodel.getText().toString();
                productComment = pcomment.getText().toString();
                shopNum =shopnumber.getText().toString();
                productWarranty = pwarran.getText().toString();
                productBuyerName = pbuyName.getText().toString();
                productPrice = pprice.getText().toString();
                userPhone = userphoneInput.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                //    System.out.println(dateFormat.format(date));
                purchageDate=dateFormat.format(date);


                //           Toast.makeText(getApplicationContext() , "SHop Name"+shopname + " "+shopadress , Toast.LENGTH_SHORT ).show();

                if (!TextUtils.isEmpty(productName) && postImageUri != null &&!TextUtils.isEmpty(productModel) &&
                        !TextUtils.isEmpty(shopNum) && !TextUtils.isEmpty(productWarranty) &&!TextUtils.isEmpty(productBuyerName)
                ) {

                    final String randomName = UUID.randomUUID().toString();

                    // PHOTO UPLOAD
                    File newImageFile = new File(postImageUri.getPath());

                    try {

                        compressedImageFile = new Compressor(New_Service.this)
                                .setMaxHeight(920)
                                .setMaxWidth(920)
                                .setQuality(50)
                                .compressToBitmap(newImageFile);

                    } catch (IOException e) {

                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                    byte[] imageData = baos.toByteArray();
                    UploadTask filePath = storageRef.child(randomName+shopUid + ".jpg").putBytes(imageData);
                    filePath.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            //     viewDialog.hideDialog();
                            showCompletedialogue();
                            //     viewDialog.hideDialog();

                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            Uri downloaduri = uriTask.getResult();



                            produrctModel uploadModel = new  produrctModel(shopMail ,shopNum , productName , productModel,productComment,productWarranty,productBuyerName
                                    ,shopname,shopadress,shopUid,QRCODE, downloaduri.toString() ,purchageDate,productPrice, userPhone );

                            String ts =UserRef.push().getKey() ;
                            UserRef.child(ts).setValue(uploadModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                   // sendTonextAcitvity();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@android.support.annotation.NonNull Exception e) {
                                    hideLoadingDialoge();
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });


                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            hideLoadingDialoge();
                            new AwesomeErrorDialog(New_Service.this)
                                    .setTitle("Error")
                                    .setMessage(e.getMessage())
                                    .setColoredCircle(R.color.dialogErrorBackgroundColor)
                                    .setDialogIconAndColor(R.drawable.ic_dialog_error, R.color.white)
                                    .setCancelable(true).setButtonText(getString(R.string.dialog_ok_button))
                                    .setButtonBackgroundColor(R.color.dialogErrorBackgroundColor)
                                    .setButtonText(getString(R.string.dialog_ok_button))
                                    .setErrorButtonClick(new Closure() {
                                        @Override
                                        public void exec() {
                                            // click
                                            new AwesomeErrorDialog(New_Service.this)
                                                    .hide();
                                            recreate();
                                           // new AwesomeProgressDialog(New_Service.this).hide();
                                        }
                                    })
                                    .show();
                                hideLoadingDialoge();

                         //   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();


                        }

                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        showLoadingDialogue();
                         //   viewDialog.showDialog();

                        }
                    });



                } else {
                    hideLoadingDialoge();
                    Toast.makeText(getApplicationContext(), "Please Select image or add image Name ", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private void showCompletedialogue() {

        new AwesomeSuccessDialog(this)
                .setTitle(R.string.uploadSuccssful)
                .setMessage(R.string.uploadAgina)
                .setColoredCircle(R.color.dialogSuccessBackgroundColor)
                .setDialogIconAndColor(R.drawable.ic_dialog_info, R.color.white)
                .setCancelable(true)
                .setPositiveButtonText(getString(R.string.dialog_yes_button))
                .setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                .setPositiveButtonTextColor(R.color.white)
               .setNegativeButtonText(getString(R.string.dialog_no_button))
               .setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor)
                .setNegativeButtonTextColor(R.color.white)
              .setPositiveButtonClick(new Closure() {
                    @Override
                    public void exec() {
                        //click
                        new AwesomeSuccessDialog(New_Service.this)
                                .hide();
                        Intent mIntent = getIntent();
                        finish();
                        startActivity(mIntent);
                    }
                })
                .setNegativeButtonClick(new Closure() {
                    @Override
                    public void exec() {

                        new AwesomeSuccessDialog(New_Service.this)
                                .hide();
                        sendTonextAcitvity();
                    }
                })

                .show();

    }

    public  void  getdataFromFirebase(){

        ValueEventListener postlistenr = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                modelForShopOwner ShopInfo = dataSnapshot.getValue(modelForShopOwner.class);

                shopadress = ShopInfo.getShopAdress();
                shopname = ShopInfo.getShopName();








            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {



            }

        };


        userDb.addListenerForSingleValueEvent(postlistenr);



    }
    @Override
    protected void onStart() {
        super.onStart();

       getdataFromFirebase() ;

    }
    private void BringImagePicker () {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setInitialCropWindowPaddingRatio(0)
                .setCropShape(CropImageView.CropShape.RECTANGLE) //shaping the image
                .start(New_Service.this);

    }
    private void sendTonextAcitvity() {

        Intent i = new Intent(getApplicationContext(),astha_manager.class);
        startActivity(i);
        finish();


    }




   private  void showLoadingDialogue()
   {
       new AwesomeProgressDialog(this)
               .setTitle(R.string.uploaginTitle)
               .setCancelable(false)
               .show();

   }
   private  void hideLoadingDialoge()

   {
                new AwesomeProgressDialog(this);
   }



    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                invoicePic.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();
                Toast.makeText(this, error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }

    }


}



