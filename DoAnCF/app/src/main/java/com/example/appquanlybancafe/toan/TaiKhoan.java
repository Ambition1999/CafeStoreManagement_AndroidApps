package com.example.appquanlybancafe.toan;

import com.google.gson.annotations.SerializedName;

public class TaiKhoan {
    @SerializedName("maNv")
    String maNv;
    @SerializedName("tenDn")
    String tenDn;
    @SerializedName("matKhau")
    String matKhau;
    @SerializedName("quyen")
    String quyen;

    public TaiKhoan() {
    }

    public TaiKhoan(String maNv, String tenDn, String matKhau, String quyen) {
        this.maNv = maNv;
        this.tenDn = tenDn;
        this.matKhau = matKhau;
        this.quyen = quyen;
    }

    public String getMaNv() {
        return maNv;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getTenDn() {
        return tenDn;
    }

    public void setTenDn(String tenDn) {
        this.tenDn = tenDn;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getQuyen() {
        return quyen;
    }

    public void setQuyen(String quyen) {
        this.quyen = quyen;
    }
}
