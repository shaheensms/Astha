package com.metacoders.astha;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class SalesDetailsPage extends AppCompatActivity {

    String  pname , Pmodel , Pprice, PWarranty, Pcomment, PImageLink, BuyerName, qrCode, purchageDate, PShopName, PshopNumber, shopmail ;

    TextView shopmail_in, shopnum_in, productname_in, productmodel_in, comment_in, warranty_in,
            productbuyername_in, shop_name_in, shop_adress_in, shopuid_in, qrcode_in ,purchageDate_in ,productprice_in, userPhone ;
    FirebaseAuth mauth ;
    String uid ;
    ImageView iamge ;
    Button dwnloadbtn ;
    Bitmap bitmap ;
    private  static  final  int WRITE_EXTERNAL_STORAGE_CODE =1;
    FirebaseUser currentUser ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details_page);
        mauth = FirebaseAuth.getInstance();
        currentUser = mauth.getCurrentUser();
        uid = currentUser.getUid() ;

        try {
            getSupportActionBar().setTitle("Product Details");
        }
        catch (NullPointerException e ){

            getSupportActionBar();
        }
        // getting The data from the Views



        iamge = (ImageView)findViewById(R.id.image_product_salesDetails);
        shop_adress_in = (TextView)findViewById(R.id.shop_ADRESS_SalesDEtails) ;
        shopmail_in = (TextView)findViewById(R.id.shop_Email_Address_SalesDEtails) ;
        shop_name_in = (TextView) findViewById(R.id.ShopnameTV_SalesDEtails);
        shopnum_in = (TextView) findViewById(R.id.shop_phone_number_SalesDEtails);
        productname_in = (TextView) findViewById(R.id.productName_SalesDEtails);
        productmodel_in=(TextView) findViewById(R.id.productModel_SalesDEtails);
        productprice_in = (TextView) findViewById(R.id.productPrice_SalesDEtails);
        purchageDate_in = (TextView) findViewById(R.id.productPurchaseDate_SalesDEtails);
        comment_in = (TextView) findViewById(R.id.product_comment_SalesDEtails);
        warranty_in= (TextView) findViewById(R.id.productWarranty_SalesDEtails);
        shopuid_in = (TextView) findViewById(R.id.shopuidTV_SalesDEtails);
        qrcode_in = (TextView) findViewById(R.id.productID_SalesDEtails);
        productbuyername_in = (TextView) findViewById(R.id.productBuyerName_SalesDEtails);
        dwnloadbtn = (Button) findViewById(R.id.dwldBtn_SalesDEtails);
        userPhone = findViewById(R.id.productBuyerCell_SalesDEtails);




        Intent i  = getIntent();

        pname = i.getStringExtra("NAME");
        Pmodel = i.getStringExtra("MODEL");
        Pprice = i.getStringExtra("PRICE");
        PWarranty = i.getStringExtra("WARANTY");
        Pcomment = i.getStringExtra("COMMENT");
        PImageLink=i.getStringExtra("IMAGELINK");
        BuyerName=i.getStringExtra("BUYERNAME");
        qrCode=i.getStringExtra("QRCODE");
        purchageDate=i.getStringExtra("PURCHAGEDATE");
        PShopName=i.getStringExtra("SHOPNAME");
        PshopNumber=i.getStringExtra("SHOPNUMBER");
        shopmail = i.getStringExtra("SHOPMAIL");
        String pshopAdress = i.getStringExtra("ADRESS");
        String buyerPhone = i.getStringExtra("BUYERPH");





        shopuid_in.setText("Shop uID :"+uid);
        shopmail_in.setText("Email : "+shopmail);
        shopnum_in.setText("Shop Number:"+PshopNumber);
        productname_in.setText("Product Name:"+pname);
        productmodel_in.setText("Product Model:"+Pmodel);
        comment_in.setText("Comment:"+Pcomment);
        warranty_in.setText("Product Warranty:"+PWarranty);
        productbuyername_in.setText("Buyer Name:"+BuyerName);
        shop_name_in.setText("Shop Name :"+PShopName);
        shop_adress_in.setText("Shop Adress :"+pshopAdress);
        qrcode_in.setText("Qr Code :"+qrCode);
        purchageDate_in.setText("Purchage Date:"+purchageDate);
        productprice_in.setText("Product Price:"+Pprice);
        userPhone.setText("Buyer Phone :"+ buyerPhone);


//        Glide.with(SalesDetailsPage.this)
          //      .load(PImageLink)
           //     .centerCrop()
            //    .placeholder(R.drawable.loadign)
             //   .into(iamge);

        Picasso.get()
                .load(PImageLink)
                .resize(350 , 350)
                .centerCrop()
                .into(iamge);

        dwnloadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //downlad Image from the server
                iamge.invalidate();
                //Bitmap bmp = ((GlideBitmapDrawable)holder.wallPaperImageView.getDrawable().getCurrent()).getBitmap();
               // bitmap = ((BitmapDrawable)((LayerDrawable)iamge.getDrawable()).getDrawable(0)).getBitmap();

               bitmap  = ((BitmapDrawable)iamge.getDrawable()).getBitmap() ;
                // checking permisssion

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED){
                        String [] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission , WRITE_EXTERNAL_STORAGE_CODE);

                    }
                    else {
                        saveImage();
                    }


                }
                else {
                    saveImage();
                }




            }
        });


    }
    private void saveImage() {

        File path = Environment.getExternalStorageDirectory() ;

        File dir = new File(path+"/Astha/");
        dir.mkdirs();

        String ImageName = pname+".JPEG";
        File file = new File(dir , ImageName);
        OutputStream out ;
        try {

            out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG , 100 , out);
            out.flush();
            out.close();

            Toast.makeText(this , ImageName + " Saved To "+ dir , Toast.LENGTH_SHORT ).show();
        }
        catch (Exception e ){

        }




    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE_CODE : {
                if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_DENIED){
                    saveImage();

                }

                else {

                    Toast.makeText(this , "Enable Storage Permission & Press The Save Button Again  ", Toast.LENGTH_SHORT ).show();

                }


            }

        }
    }
}
