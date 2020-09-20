package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

public class ThemBanMoiAdapterGrid extends ArrayAdapter<Ban> {
    public Context context1;
    public ThemBanMoiAdapterGrid(Context context, ArrayList<Ban> arrayList) {
        super(context, R.layout.item_themban, arrayList);
        this.context1 = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Ban ban = getItem(position);
        ThemBanMoiAdapterGrid.ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ThemBanMoiAdapterGrid.ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_themban, parent, false);
            viewHolder.tvTenBan = convertView.findViewById(R.id.item_themban_tvTenBan);
            viewHolder.tvTrangThai = convertView.findViewById(R.id.item_themban_tvTrangThai);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ThemBanMoiAdapterGrid.ViewHolder)convertView.getTag();
        }
        //Log.d("")
        viewHolder.tvTenBan.setText(ban.getTenBan());
        String trangThaiBan = "Trống";
        if(ban.getTrangThai())
            trangThaiBan = "Đang sử dụng";
        viewHolder.tvTrangThai.setText(trangThaiBan);
        return convertView;
    }

    static class ViewHolder{
        TextView tvTenBan;
        TextView tvTrangThai;
    }

}
