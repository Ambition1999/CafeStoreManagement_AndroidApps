package com.example.appquanlybancafe.len;



import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class datBan implements Parcelable {
    @SerializedName("maHd")
    private int maHD;
    @SerializedName("maBan")
    private String soBan;
    @SerializedName("trangThai")
    private String tt;
    @SerializedName("tgvao")
    private String tgvao;
    @SerializedName("tgra")
    private String tgra;
    @SerializedName("phuongThucTt")
    private String phuongThuc;
    @SerializedName("maNv")
    private String manv;
    @SerializedName("tongTienTt")
    private double tong;


    protected datBan(Parcel in) {
        maHD = in.readInt();
        soBan = in.readString();
        tt = in.readString();
        tgvao = in.readString();
        tgra = in.readString();
        phuongThuc = in.readString();
        manv = in.readString();
        tong = in.readDouble();
    }

    public static final Creator<datBan> CREATOR = new Creator<datBan>() {
        @Override
        public datBan createFromParcel(Parcel in) {
            return new datBan(in);
        }

        @Override
        public datBan[] newArray(int size) {
            return new datBan[size];
        }
    };

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getSoBan() {
        return soBan;
    }

    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }
//    public datBan(ThucDon td,String soBan){
//        this.td=td;
//        this.soBan=soBan;
//    }

    public String getTt() {
        return tt;
    }

    public void setTt(String tt) {
        this.tt = tt;
    }

    public datBan(String soBan, String tt, String tgvao, String manv, double tong) {
        this.soBan = soBan;
        this.tt = tt;
        this.tgvao = tgvao;
        this.manv = manv;
        this.tong = tong;
    }
    public datBan(String soBan, String tt, String tgvao,String tgra, String manv, double tong,String phuongThuc) {
        this.soBan = soBan;
        this.tt = tt;
        this.tgvao = tgvao;
        this.manv = manv;
        this.tong = tong;
        this.tgra=tgra;
        this.phuongThuc=phuongThuc;
    }
    public datBan(int hd,String soBan, String tt, String tgvao,String tgra, String manv, double tong,String phuongThuc) {
        this.soBan = soBan;
        this.tt = tt;
        this.tgvao = tgvao;
        this.manv = manv;
        this.tong = tong;
        this.tgra=tgra;
        this.phuongThuc=phuongThuc;
        this.maHD=hd;
    }

    public datBan(){}
    public String getTgvao() {
        return tgvao;
    }

    public void setTgvao(String tgvao) {
        this.tgvao = tgvao;
    }

    public String getTgra() {
        return tgra;
    }

    public void setTgra(String tgra) {
        this.tgra = tgra;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public double getTong() {
        return tong;
    }

    public void setTong(double tong) {
        this.tong = tong;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maHD);
        dest.writeString(soBan);
        dest.writeString(tt);
        dest.writeString(tgvao);
        dest.writeString(tgra);
        dest.writeString(phuongThuc);
        dest.writeString(manv);
        dest.writeDouble(tong);
    }


//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(maHD);
//        dest.writeString(soBan);
//        dest.writeString(tgvao);
//        dest.writeString(tgra);
//        dest.writeString(phuongThuc);
//        dest.writeString(manv);
//        dest.writeDouble(tong);
//    }
}

