package com.example.appquanlybancafe.len;

import com.google.gson.annotations.SerializedName;

public class LoaiMon {
    @SerializedName("maLoai")
    private int maLoai;
    @SerializedName("tenLoai")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
    public LoaiMon(String name){
        this.name=name;
    }
}
