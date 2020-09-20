package com.example.appquanlybancafe.toan;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class HoaDon implements Parcelable {
    @SerializedName("maHd")
    int maHd;
    @SerializedName("tgvao")
    String tgvao;
    @SerializedName("tgra")
    String tgra;
    @SerializedName("maBan")
    int maBan;
    @SerializedName("maNv")
    int maNv;
    @SerializedName("trangThai")
    String trangThai;
    @SerializedName("tongTienTt")
    Double tongTienTt;
    @SerializedName("phuongThucTt")
    String phuongThucTt;

    public HoaDon() {
    }

    public HoaDon(int maHd, String tgvao, String tgra, int maBan, int maNv, String trangThai, Double tongTienTt, String phuongThucTt) {
        this.maHd = maHd;
        this.tgvao = tgvao;
        this.tgra = tgra;
        this.maBan = maBan;
        this.maNv = maNv;
        this.trangThai = trangThai;
        this.tongTienTt = tongTienTt;
        this.phuongThucTt = phuongThucTt;
    }

    protected HoaDon(Parcel in) {
        maHd = in.readInt();
        tgvao = in.readString();
        tgra = in.readString();
        maBan = in.readInt();
        maNv = in.readInt();
        trangThai = in.readString();
        if (in.readByte() == 0) {
            tongTienTt = null;
        } else {
            tongTienTt = in.readDouble();
        }
        phuongThucTt = in.readString();
    }

    public static final Creator<HoaDon> CREATOR = new Creator<HoaDon>() {
        @Override
        public HoaDon createFromParcel(Parcel in) {
            return new HoaDon(in);
        }

        @Override
        public HoaDon[] newArray(int size) {
            return new HoaDon[size];
        }
    };

    public int getMaHd() {
        return maHd;
    }

    public void setMaHd(int maHd) {
        this.maHd = maHd;
    }

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

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Double getTongTienTt() {
        return tongTienTt;
    }

    public void setTongTienTt(Double tongTienTt) {
        this.tongTienTt = tongTienTt;
    }

    public String getPhuongThucTt() {
        return phuongThucTt;
    }

    public void setPhuongThucTt(String phuongThucTt) {
        this.phuongThucTt = phuongThucTt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(maHd);
        dest.writeString(tgvao);
        dest.writeString(tgra);
        dest.writeInt(maBan);
        dest.writeInt(maNv);
        dest.writeString(trangThai);
        if (tongTienTt == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(tongTienTt);
        }
        dest.writeString(phuongThucTt);
    }
}