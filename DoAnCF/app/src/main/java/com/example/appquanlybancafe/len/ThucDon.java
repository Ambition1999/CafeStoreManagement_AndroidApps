package com.example.appquanlybancafe.len;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;

public class ThucDon {
    @SerializedName("maMon")
    String maMon;
    @SerializedName("tenMon")
    String tenMon;
    @SerializedName("maLoai")
    int maLoai;
    @SerializedName("donViTinh")
    String dvt;
    @SerializedName("donGia")
    double donGia;
    @SerializedName("hinhAnh")
    String hinhAnh;
    public int slDat;
    String tuyChonThem;
    boolean phoBien;

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTuyChonThem() {
        return tuyChonThem;
    }

    public void setTuyChonThem(String tuyChonThem) {
        this.tuyChonThem = tuyChonThem;
    }

    public boolean isPhoBien() {
        return phoBien;
    }

    public void setPhoBien(boolean phoBien) {
        this.phoBien = phoBien;
    }
    public int getSlDat() {
        return slDat;
    }

    public void setSlDat(int slDat) {
        this.slDat = slDat;
    }
    public ThucDon(String maMon, String tenMon, double donGia){
        this.maMon=maMon;
        this.tenMon=tenMon;
        this.donGia=donGia;
        this.slDat=1;
    }
    public ThucDon(String maMon, String tenMon, double donGia,int slDat){
        this.maMon=maMon;
        this.tenMon=tenMon;
        this.donGia=donGia;
        this.slDat=slDat;
    }
    public static Bitmap convertStringToBitmapFromAccess(Context context, String filename)
    {
        AssetManager assetmanager=context.getAssets();
        try {
            InputStream is=assetmanager.open(filename);
            Bitmap bitmap= BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
