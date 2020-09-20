package com.example.appquanlybancafe.toan;

public class OrderedTemp {
    public String soBan;
    public String tgVao;
    public String tenNV;

    public OrderedTemp() {
    }

    public OrderedTemp(String soBan, String tgVao, String tenNV) {
        this.soBan = soBan;
        this.tgVao = tgVao;
        this.tenNV = tenNV;
    }

    public String getSoBan() {
        return soBan;
    }

    public void setSoBan(String soBan) {
        this.soBan = soBan;
    }

    public String getTgVao() {
        return tgVao;
    }

    public void setTgVao(String tgVao) {
        this.tgVao = tgVao;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }
}
