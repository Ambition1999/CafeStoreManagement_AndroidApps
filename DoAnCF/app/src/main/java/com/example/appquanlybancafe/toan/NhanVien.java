package com.example.appquanlybancafe.toan;

import com.google.gson.annotations.SerializedName;

public class NhanVien {
    @SerializedName("maNv")
    int maNv;
    @SerializedName("tenNv")
    String tenNv;
    @SerializedName("diaChi")
    String diaChi;
    @SerializedName("sdt")
    String sdt;
    @SerializedName("maLoaiNv")
    int maLoaiNv;
    @SerializedName("taiKhoan")
    String taiKhoan;

    public NhanVien() {

    }

    public NhanVien(int maNv, String tenNv, String diaChi, String sdt, int maLoaiNv, String taiKhoan) {
        this.maNv = maNv;
        this.tenNv = tenNv;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.maLoaiNv = maLoaiNv;
        this.taiKhoan = taiKhoan;
    }

    public int getMaNv() {
        return maNv;
    }

    public void setMaNv(int maNv) {
        this.maNv = maNv;
    }

    public String getTenNv() {
        return tenNv;
    }

    public void setTenNv(String tenNv) {
        this.tenNv = tenNv;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getMaLoaiNv() {
        return maLoaiNv;
    }

    public void setMaLoaiNv(int maLoaiNv) {
        this.maLoaiNv = maLoaiNv;
    }

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
