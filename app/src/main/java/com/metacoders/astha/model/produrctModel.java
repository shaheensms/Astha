package com.metacoders.astha.model;

public class produrctModel {

    String shopmail, shopnum, productname, productmodel, comment, warranty,
            productbuyername, shop_name, shop_adress, shopuid, qrcode, imageurl ,purchageDate ,productprice, userphone;

    public produrctModel() {
    }

       public produrctModel(String shopmail, String shopnum, String productname, String productmodel, String comment, String warranty, String productbuyername, String shop_name, String shop_adress, String shopuid, String qrcode, String imageurl, String purchageDate, String productprice, String userphone) {
        this.shopmail = shopmail;
        this.shopnum = shopnum;
        this.productname = productname;
        this.productmodel = productmodel;
        this.comment = comment;
        this.warranty = warranty;
        this.productbuyername = productbuyername;
        this.shop_name = shop_name;
        this.shop_adress = shop_adress;
        this.shopuid = shopuid;
        this.qrcode = qrcode;
        this.imageurl = imageurl;
        this.purchageDate = purchageDate;
        this.productprice = productprice;
        this.userphone = userphone;
    }

    public String getShopmail() {
        return shopmail;
    }

    public void setShopmail(String shopmail) {
        this.shopmail = shopmail;
    }

    public String getShopnum() {
        return shopnum;
    }

    public void setShopnum(String shopnum) {
        this.shopnum = shopnum;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductmodel() {
        return productmodel;
    }

    public void setProductmodel(String productmodel) {
        this.productmodel = productmodel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getProductbuyername() {
        return productbuyername;
    }

    public void setProductbuyername(String productbuyername) {
        this.productbuyername = productbuyername;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_adress() {
        return shop_adress;
    }

    public void setShop_adress(String shop_adress) {
        this.shop_adress = shop_adress;
    }

    public String getShopuid() {
        return shopuid;
    }

    public void setShopuid(String shopuid) {
        this.shopuid = shopuid;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPurchageDate() {
        return purchageDate;
    }

    public void setPurchageDate(String purchageDate) {
        this.purchageDate = purchageDate;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone;
    }
}
