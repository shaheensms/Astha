package com.metacoders.astha;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.metacoders.astha.model.produrctModel;
import com.metacoders.astha.vieholders.ViewHolder;

public class Service_history extends AppCompatActivity {


    LinearLayoutManager mLayoutManager; //for sorting

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;

    DatabaseReference mRef;
    private ProgressBar Sales_progressBar ;

    FirebaseAuth mauth ;
    FirebaseUser muser ;
    public    String uid;
    FirebaseRecyclerAdapter<produrctModel, ViewHolder>firebaseRecyclerAdapter ;
    FirebaseRecyclerOptions<produrctModel>options ;
    Query firebaseSearchQuery ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_history);
        mauth = FirebaseAuth.getInstance() ;
        muser = FirebaseAuth.getInstance().getCurrentUser();

        uid = muser.getUid() ;
        //RecyclerView
        mRecyclerView = findViewById(R.id.recycle_shopHistroy);
        Sales_progressBar = findViewById(R.id.progressbar_salesHistroy);

       // Sales_progressBar.setVisibility(View.VISIBLE);

        //set layout as LinearLayout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Products");
         firebaseSearchQuery = mRef.orderByChild("shopuid").startAt(uid).endAt(uid + "\uf8ff");

        mRef.keepSynced(true);

       // Toast.makeText(getApplicationContext(), "We Are Loading Data", Toast.LENGTH_SHORT).show();

        //load Data
        showData();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

              if(mLayoutManager.getItemCount()==0){
                  Sales_progressBar.setVisibility(View.GONE);
                  Toast.makeText(getApplicationContext(), "You Do not Have Any Service", Toast.LENGTH_SHORT).show();


              }

            }
        }, 6000);




    }

    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<produrctModel>()
                .setQuery(  mRef , produrctModel.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<produrctModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull produrctModel model) {


                holder.setDetails(getApplicationContext(), model.getShopmail(), model.getShopnum(), model.getProductname() , model.getProductmodel(), model.getComment()
                        , model.getWarranty() , model.getProductbuyername() , model.getShop_name() , model.getShop_adress() , model.getShopuid() , model.getQrcode()
                        ,model.getImageurl() , model.getPurchageDate() , model.getProductprice());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Sales_progressBar.setVisibility(View.GONE);

                    }
                }, 3000);

            }



            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

                ViewHolder viewHolder = new ViewHolder(itemVIew);

                //itemClicklistener
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);





                        String Pname = getItem(position).getProductname();
                        String Pmodel = getItem(position).getProductmodel();
                        String Pprice = getItem(position).getProductprice();
                        String PWarranty = getItem(position).getWarranty();
                        String Pcomment = getItem(position).getComment();
                        String PImageLink = getItem(position).getImageurl();
                        String BuyerName  = getItem(position).getProductbuyername() ;
                        String qrCode = getItem(position).getQrcode() ;
                        String purchageDate = getItem(position).getPurchageDate();
                        String PShopName = getItem(position).getShop_name();
                        String PshopNumber = getItem(position).getShopnum() ;
                        String PshoAdres = getItem(position).getShop_adress() ;
                        String shopmail = getItem(position).getShopmail();

                        Intent i = new Intent(view.getContext() , SalesDetailsPage.class);
                        i.putExtra("NAME", Pname);
                        i.putExtra("MODEL", Pmodel);
                        i.putExtra("PRICE", Pprice);
                        i.putExtra("WARANTY", PWarranty);
                        i.putExtra("COMMENT", Pcomment);
                        i.putExtra("IMAGELINK", PImageLink);
                        i.putExtra("BUYERNAME", BuyerName);
                        i.putExtra("QRCODE", qrCode);
                        i.putExtra("PURCHAGEDATE", purchageDate);
                        i.putExtra("SHOPNAME", PShopName);
                        i.putExtra("SHOPNUMBER", PshopNumber);
                        i.putExtra("ADRESS", PshoAdres);
                        i.putExtra("SHOPMAIL", shopmail);


                        startActivity(i);


                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });



                return viewHolder;
            }
        };


        mRecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }



}
