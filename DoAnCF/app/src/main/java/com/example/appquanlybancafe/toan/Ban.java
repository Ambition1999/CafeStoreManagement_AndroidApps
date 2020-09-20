package com.example.appquanlybancafe.toan;

import com.google.gson.annotations.SerializedName;

public class Ban {
    @SerializedName("maBan")
    int maBan;
    @SerializedName("tenBan")
    String tenBan;
    @SerializedName("trangThai")
    boolean trangThai;
    @SerializedName("khuVuc")
    String khuVuc;


    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public Ban(int maBan, String tenBan, Boolean trangThai, String khuVuc) {
        this.maBan = maBan;
        this.tenBan = tenBan;
        this.trangThai = trangThai;
        this.khuVuc = khuVuc;
    }

    public Ban() {
    }


}
