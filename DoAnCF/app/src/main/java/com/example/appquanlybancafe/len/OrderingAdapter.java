package com.example.appquanlybancafe.len;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

public class OrderingAdapter extends ArrayAdapter<ThucDon> {
    Utils utils;
    public OrderingAdapter(@NonNull Context context, ArrayList<ThucDon> arrayList) {
        super(context,0, arrayList);
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ThucDon chooseItem=getItem(position);
        if(convertView==null)
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_ordering,parent,false);
        TextView txtTen=convertView.findViewById(R.id.gioHang_TenHang);
        final TextView txtSL=convertView.findViewById(R.id.gioHang_slDat);
        final TextView txtTien=convertView.findViewById(R.id.gioHang_GiaSP);
        utils=new Utils();
        txtTen.setText(chooseItem.getTenMon());
        txtSL.setText(chooseItem.getSlDat()+"");
        txtTien.setText(Integer.parseInt(txtSL.getText().toString())*chooseItem.getDonGia()+"");

        //img1.setImageResource(chooseItem.getDrawableId());
        Button btTang=convertView.findViewById(R.id.gioHang_Tang);
        btTang.setTag(new Integer(position));
        btTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl=chooseItem.getSlDat();
                chooseItem.setSlDat(sl+1);
                txtSL.setText((sl+1)+"");
                txtTien.setText(Integer.parseInt(txtSL.getText().toString())*chooseItem.getDonGia()+"");
                Main4Activity.txtThanhToan.setText(utils.tinhTien()+"");

                if(chooseItem.getSlDat()==0)
                    utils.getCart().remove(chooseItem);
            }
        });
        Button btGiam=convertView.findViewById(R.id.gioHang_Giam);
        final View finalConvertView = convertView;
        btGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl=chooseItem.getSlDat();
                chooseItem.setSlDat(sl-1);

                if(sl==1){
                    finalConvertView.setVisibility(View.INVISIBLE);
                }
                txtSL.setText((sl-1)+"");
                txtTien.setText(Integer.parseInt(txtSL.getText().toString())*chooseItem.getDonGia()+"");
                Main4Activity.txtThanhToan.setText(utils.tinhTien()+"");

                if(chooseItem.getSlDat()==0)
                    utils.getCart().remove(chooseItem);
            }
        });
        return convertView;
    }
}
