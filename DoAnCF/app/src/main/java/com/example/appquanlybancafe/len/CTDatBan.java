package com.example.appquanlybancafe.len;

import com.google.gson.annotations.SerializedName;

public class CTDatBan {
    @SerializedName("maHd")
    private int MaHD;
    @SerializedName("maMon")
    private String MaMon;
    @SerializedName("soLuong")
    private int sl;
    @SerializedName("tongTien")
    private double tong;

    public int getMaHD() {
        return MaHD;
    }

    public void setMaHD(int maHD) {
        MaHD = maHD;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String maMon) {
        MaMon = maMon;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }

    public CTDatBan(String maMon, int sl, double tong) {
        MaMon = maMon;
        this.sl = sl;
        this.tong = tong;
    }
    public CTDatBan(int maHD,String maMon, int sl, double tong) {
        this.MaHD=maHD;
        MaMon = maMon;
        this.sl = sl;
        this.tong = tong;
    }
}
