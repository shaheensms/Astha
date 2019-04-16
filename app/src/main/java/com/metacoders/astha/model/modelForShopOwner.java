package com.metacoders.astha.model;

public class modelForShopOwner {


    String shopName , shopAdress , shopPhone , shopEmail ,  memberId , fbId
            , ownerId ,ownerPhone ,ownerEmail,ownerNID , uid  , isverified;


    public modelForShopOwner() {
    }

    public modelForShopOwner(String shopName, String shopAdress, String shopPhone, String shopEmail, String memberId, String fbId, String ownerId, String ownerPhone, String ownerEmail, String ownerNID, String uid, String isverified) {
        this.shopName = shopName;
        this.shopAdress = shopAdress;
        this.shopPhone = shopPhone;
        this.shopEmail = shopEmail;
        this.memberId = memberId;
        this.fbId = fbId;
        this.ownerId = ownerId;
        this.ownerPhone = ownerPhone;
        this.ownerEmail = ownerEmail;
        this.ownerNID = ownerNID;
        this.uid = uid;
        this.isverified = isverified;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAdress() {
        return shopAdress;
    }

    public void setShopAdress(String shopAdress) {
        this.shopAdress = shopAdress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerNID() {
        return ownerNID;
    }

    public void setOwnerNID(String ownerNID) {
        this.ownerNID = ownerNID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getIsverified() {
        return isverified;
    }

    public void setIsverified(String isverified) {
        this.isverified = isverified;
    }
}
