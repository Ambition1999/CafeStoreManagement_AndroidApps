package com.example.appquanlybancafe.len;

import java.util.ArrayList;

public class Utils {
    static ArrayList<datBan> ordered=new ArrayList<>();
    static ArrayList<ThucDon> cart=new ArrayList<>();
    static int sl;
    static int naruto=0;
    static int co=0;
    static double tienGoc=0;
    static double giaMoi=0;
    public static int getSl() {
        return sl;
    }

    public static void setSl(int sl) {
        Utils.sl = sl;
    }
    public ArrayList<datBan> getOrdered(){return ordered;}
    public ArrayList<ThucDon> getCart(){return cart;}
    public double tinhTien(){
        double sum= 0;
        for(int i=0;i<cart.size();i++){
            sum+=cart.get(i).getDonGia()*cart.get(i).getSlDat();
        }
        return sum+tienGoc;
    }
    public int count(){
        int cou=0;
        for (int i=0;i<cart.size();i++){
            cou+=cart.get(i).getSlDat();
        }
        return cou;
    }
}
