package com.example.appquanlybancafe.len;

public class RecyclerViewItem {

    private int drawableId;
    private String name;
    private long gia;
    private int slDat;
    public RecyclerViewItem(int drawableId, String name) {
        this.drawableId = drawableId;
        this.name = name;
        this.gia=0;
        this.slDat=0;
    }
    public RecyclerViewItem(int drawableId, String name,long gia) {
        this.drawableId = drawableId;
        this.name = name;
        this.gia=gia;
    }

    public int getSlDat() {
        return slDat;
    }

    public void setSlDat(int slDat) {
        this.slDat = slDat;
    }

    public long getGia(){return gia;}
    public int getDrawableId() {
        return drawableId;
    }
    public void setGia(long gia){this.gia=gia;}
    public String getName() {
        return name;
    }
}