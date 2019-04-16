package com.metacoders.astha.vieholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.metacoders.astha.R;


public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setDetails(Context ctx, String ShopMail, String Rshopnum, String Rproductname, String Rproductmodel, String Rcomment, String Rwarranty, String Rproductbuyername, String Rshop_name, String Rshop_adress, String Rshopuid, String Rqrcode, String Rimageurl, String RpurchageDate, String Rproductprice){
        //Views
        TextView pname = mView.findViewById(R.id.rProductName);
        TextView price = mView.findViewById(R.id.r_price_Tv);
        TextView pmodel = mView.findViewById(R.id.rProductModel);
        TextView pWarranty = mView.findViewById(R.id.rProductWarranty);
        TextView buyerNmae = mView.findViewById(R.id.rProductBuyerTv);
        TextView purcahgeDate =mView.findViewById(R.id.rProductPurchageDate);
        TextView shopName = mView.findViewById(R.id.rShopNameTv);
        TextView imageUrl = mView.findViewById(R.id.r_imageurl_Tv);
        TextView shopnumber =mView.findViewById(R.id.r_shopnum_Tv);
        TextView qrid = mView.findViewById(R.id.r_quid_TV);
        TextView commentTV = mView.findViewById(R.id.r_comment_TV);
        TextView AdressTV = mView.findViewById(R.id.r_Adress_TV);
        TextView shopMailTv = mView.findViewById(R.id.r_ShopMail_TV);





        //set data to views



        pname.setText("Product Name :"+Rproductname);
        pmodel.setText("Model :"+Rproductmodel);
        pWarranty.setText("Warranty: "+Rwarranty);
        buyerNmae.setText("Buyer Name :"+Rproductbuyername);
        purcahgeDate.setText("Date :"+RpurchageDate);
        shopName.setText(Rshop_name);
        shopnumber.setText(Rshopnum);
        imageUrl.setText(Rimageurl);
        qrid.setText(Rqrcode);
        commentTV.setText(Rcomment);
        price.setText("Price :"+Rproductprice);
        AdressTV.setText(Rshop_adress);
        shopMailTv.setText(ShopMail);

      //  Picasso.get().load(image).error(R.drawable.loading).into(mImageIv);


    }


    private ViewHolder.ClickListener mClickListener;

    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }




}
