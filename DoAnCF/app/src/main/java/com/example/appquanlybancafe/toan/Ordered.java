package com.example.appquanlybancafe.toan;

public class Ordered {
    int maHD;
    int maBan;
    int maNV;
    String tenBan;
    String tgVao;
    String tgRa;
    String tenNV;
    String khuVuc;
    String trangThai;


    public Ordered() {
    }

    public Ordered(int maHD, int maBan, int maNV, String tenBan, String tgVao, String tgRa, String tenNV, String khuVuc, String trangThai) {
        this.maHD = maHD;
        this.maBan = maBan;
        this.maNV = maNV;
        this.tenBan = tenBan;
        this.tgVao = tgVao;
        this.tgRa = tgRa;
        this.tenNV = tenNV;
        this.khuVuc = khuVuc;
        this.trangThai = trangThai;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaBan() {
        return maBan;
    }

    public void setMaBan(int maBan) {
        this.maBan = maBan;
    }

    public int getMaNV() {
        return maNV;
    }

    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public String getTgVao() {
        return tgVao;
    }

    public void setTgVao(String tgVao) {
        this.tgVao = tgVao;
    }

    public String getTgRa() {
        return tgRa;
    }

    public void setTgRa(String tgRa) {
        this.tgRa = tgRa;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
