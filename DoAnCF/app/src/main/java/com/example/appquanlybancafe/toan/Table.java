package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class Table {

    String soBan;
    String tenBan;
    String imgBan;

    public String getSoBan() {
        return soBan;
    }

    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }

    public String getImgBan() {
        return imgBan;
    }

    public void setImgBan(String imgBan) {
        this.imgBan = imgBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public  Table(){}

    public  Table(String soBan, String tenBan, String imgBan){
        this.soBan = soBan;
        this.tenBan = tenBan;
        this.imgBan = imgBan;
    }




}
