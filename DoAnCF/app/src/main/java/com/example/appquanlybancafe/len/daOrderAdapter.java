package com.example.appquanlybancafe.len;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class daOrderAdapter extends ArrayAdapter<CTDatBan> {
    public daOrderAdapter(@NonNull Context context, ArrayList<CTDatBan> arrayList) {
        super(context, 0,arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final CTDatBan ct=getItem(position);
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_da_order,parent,false);
        }
        final TextView txtSL=convertView.findViewById(R.id.da_order_sldat);
        final TextView txtTen=convertView.findViewById(R.id.da_order_ten);
        final TextView txtGia=convertView.findViewById(R.id.da_order_tien);
        ProductService productService =APIClient.getClient().create(ProductService.class);
        Call call=productService.getThucDonByMa(Integer.parseInt(ct.getMaMon()));
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ThucDon td= (ThucDon) response.body();
                txtTen.setText(td.getTenMon());
                txtSL.setText(ct.getSl()+"");
                txtGia.setText(ct.getTong()+"");
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
//        Call call=productService.findTD();
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                ArrayList<ThucDon> arrayList= (ArrayList<ThucDon>) response.body();
//                for(int i=0;i<arrayList.size();i++){
//                    if(arrayList.get(i).getMaMon().equals(ct.getMaMon())){
//                        txtTen.setText(arrayList.get(i).getTenMon());
//                        break;
//                    }
//                }
//                txtSL.setText(ct.getSl()+"");
//                txtGia.setText(ct.getTong()+"");
//            }
//            @Override
//            public void onFailure(Call call, Throwable t) {
//
//            }
//        });
        return convertView;
    }
}
