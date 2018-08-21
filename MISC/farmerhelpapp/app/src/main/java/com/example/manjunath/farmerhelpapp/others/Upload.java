package com.example.manjunath.farmerhelpapp.others;

import com.google.firebase.database.Exclude;

public class Upload {
    private String id;
    private String mname;
    private String contactno;
    private String district;
    private String rentcost;
    private String description;
    private String category;
    private String pincode;
    private String mimageurl;
    private String memailId;
    private String mkey;

    public Upload(){

    }

    public Upload(String id,String mname, String contactno, String district, String pincode,String rentcost
            ,String category,String mimageurl, String memailId,String description) {
        this.mname = mname;
        this.contactno = contactno;
        this.district = district;
        this.pincode = pincode;
        this.mimageurl = mimageurl;
        this.memailId = memailId;
        this.id = id;
        this.rentcost = rentcost;
        this.description = description;
        this.category = category;
    }

    public String getMname() {
        return mname;
    }

    public String getMimageurl() {
        return mimageurl;
    }

    public String getContactno() {
        return contactno;
    }

    public String getId() {
        return id;
    }

    public String getRentcost() {
        return rentcost;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDistrict() {
        return district;
    }

    public String getPincode() {
        return pincode;
    }

    public String getMemailId() {
        return memailId;
    }

    @Exclude
    public String getkey(){
        return mkey;
    }
    @Exclude
     public void setkey(String key){
        mkey = key;
     }
}
