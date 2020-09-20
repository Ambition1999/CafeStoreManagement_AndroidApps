package com.example.appquanlybancafe.toan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

public class TableAdapterGrid extends ArrayAdapter<Ban> {
    public Context context1;
    public TableAdapterGrid(Context context, ArrayList<Ban> arrayList) {
        super(context, R.layout.table_item, arrayList);
        this.context1 = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Ban ban = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.table_item, parent, false);
            viewHolder.imgView = convertView.findViewById(R.id.imgBan);
            viewHolder.textView = convertView.findViewById(R.id.tvBan);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.imgView.setImageBitmap(ConvertFunction.convertStringToBitmapFromAcccess(context1,"cafetable.png"));
        viewHolder.textView.setText(ban.tenBan);
        return convertView;
    }

    static class ViewHolder{
        ImageView imgView;
        TextView textView;
    }


}
