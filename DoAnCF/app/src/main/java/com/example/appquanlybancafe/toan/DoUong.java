package com.example.appquanlybancafe.toan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DoUong implements Serializable, Parcelable {
    @SerializedName("maMon")
    int maMon;
    @SerializedName("tenMon")
    String tenMon;
    @SerializedName("maLoai")
    int maLoai;
    @SerializedName("donViTinh")
    String donViTinh;
    @SerializedName("donGia")
    double donGia;
    @SerializedName("hinhAnh")
    String hinhAnh;
    @SerializedName("tuyChonThem")
    String tuyChonThem;
    @SerializedName("phoBien")
    boolean phoBien;


    public DoUong() {
    }

    public DoUong(int maMon, String tenMon, int maLoai, String donViTinh, double donGia, String hinhAnh, String tuyChonThem, boolean phoBien) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.maLoai = maLoai;
        this.donViTinh = donViTinh;
        this.donGia = donGia;
        this.hinhAnh = hinhAnh;
        this.tuyChonThem = tuyChonThem;
        this.phoBien = phoBien;
    }

    protected DoUong(Parcel in) {
        maMon = in.readInt();
        tenMon = in.readString();
        maLoai = in.readInt();
        donViTinh = in.readString();
        donGia = in.readDouble();
        hinhAnh = in.readString();
        tuyChonThem = in.readString();
        phoBien = in.readByte() != 0;
    }

    public static final Creator<DoUong> CREATOR = new Creator<DoUong>() {
        @Override
        public DoUong createFromParcel(Parcel in) {
            return new DoUong(in);
        }

        @Override
        public DoUong[] newArray(int size) {
            return new DoUong[size];
        }
    };

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
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

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maMon);
        dest.writeString(tenMon);
        dest.writeInt(maLoai);
        dest.writeString(donViTinh);
        dest.writeDouble(donGia);
        dest.writeString(hinhAnh);
        dest.writeString(tuyChonThem);
        dest.writeByte((byte) (phoBien ? 1 : 0));
    }
}
