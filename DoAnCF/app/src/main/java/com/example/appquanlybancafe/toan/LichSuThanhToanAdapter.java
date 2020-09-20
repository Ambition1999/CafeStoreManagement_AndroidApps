package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appquanlybancafe.R;

import java.text.ParseException;
import java.util.ArrayList;

public class LichSuThanhToanAdapter extends ArrayAdapter<HoaDon> {
    ArrayList<HoaDon> arrayList;
    HoaDon hoaDon;
    public LichSuThanhToanAdapter(Context context, ArrayList<HoaDon> arrayList){
        super(context, 0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        hoaDon = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_lichsu_thanhtoan,parent,false);
        TextView tvMaHD = convertView.findViewById(R.id.lstt_tvMaHD);
        TextView tvNgayVao = convertView.findViewById(R.id.lstt_tvNgayVao);
        //TextView tvNgayRa = convertView.findViewById(R.id.lstt_tvNgayRa);
        TextView tvTongTien = convertView.findViewById(R.id.lstt_tvTongTien);

        tvMaHD.setText("Mã hóa đơn: " + String.valueOf(hoaDon.getMaHd()));
        ConvertFunction convertF = new ConvertFunction();
        try {
            String tgVao = convertF.dateString(hoaDon.getTgvao());
            tvNgayVao.setText("Thời gian vào: " + tgVao);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.wtf("convertNvao",e.getMessage());
        }
//        try {
//            String tgRa = convertF.dateString(hoaDon.getTgra());
//            tvNgayRa.setText("Thời gian ra: "+ tgRa);
//        } catch (ParseException e) {
//            e.printStackTrace();
//            Log.wtf("convertNra",e.getMessage());
//        }
        tvTongTien.setText("Tổng tiền: " + hoaDon.getTongTienTt().toString() + " VNĐ");

        return  convertView;
    }
}
