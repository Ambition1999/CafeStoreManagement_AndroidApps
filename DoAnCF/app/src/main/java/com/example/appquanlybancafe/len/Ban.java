package com.example.appquanlybancafe.len;

import com.google.gson.annotations.SerializedName;

public class Ban {
    @SerializedName("maBan")
    private int ma;
    @SerializedName("tenBan")
    private String ten;
    @SerializedName("trangThai")
    private String tt;
    @SerializedName("khuVuc")
    private String kv;
    public Ban(int ma,String ten, String tt,String kv){
        this.ma=ma;
        this.ten=ten;
        this.tt=tt;
        this.kv=kv;
    }
    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public String getKv() {
        return kv;
    }

    public void setKv(String kv) {
        this.kv = kv;
    }

}
