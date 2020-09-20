package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.appquanlybancafe.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class OrderedAdapterGridTemp extends ArrayAdapter<HoaDon> {
    public Context context1;
    Ban bantemp;
    public OrderedAdapterGridTemp(Context context, ArrayList<HoaDon> arrayList) {
        super(context, R.layout.ordered_item, arrayList);
        this.context1 = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        HoaDon hd = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
            viewHolder.tvTenBan = convertView.findViewById(R.id.trangorder_tvTenBan);
            viewHolder.tvTenNV = convertView.findViewById(R.id.trangorder_tvTenNV);
            viewHolder.tvTGVao = convertView.findViewById(R.id.trangorder_tvThoiGian);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
//        GetDataByID getDataByID = new GetDataByID();
//        Log.wtf("mabannnn",hd.getMaBan() + "");
//        // Ban ban = getDataByID.getBan(hd.getMaBan());
//        Ban ban = getDataByID.getBan2(hd.getMaBan());
//        String tenNV = getDataByID.getTenNV(hd.getMaNv());
//
//        viewHolder.tvTenBan.setText(ban.getTenBan());
//        viewHolder.tvTenNV.setText(tenNV);
//        viewHolder.tvTGVao.setText(hd.getTgvao().toString());

        viewHolder.tvTenBan.setText(String.valueOf(hd.getMaBan()));
        viewHolder.tvTenNV.setText(String.valueOf(hd.getMaNv()));

        Log.wtf("abcd",hd.getTgvao());
        try {
            String tmp = dateString(hd.getTgvao());
            viewHolder.tvTGVao.setText(tmp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    private static class ViewHolder{
        TextView tvTenBan,tvTenNV,tvTGVao;
    }

    public String dateString(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(dateStr);
        String formattedTime = output.format(d);
        return  formattedTime;
    }

}
