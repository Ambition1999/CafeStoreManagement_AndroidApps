package com.example.appquanlybancafe.toan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoUongAdapter extends ArrayAdapter<DoUong> {

    ArrayList<DoUong> arrayListDoUong;
    DoUong doUong;
    public DoUongAdapter(Context context, ArrayList<DoUong> arrayList){
        super(context, 0,arrayList);
        this.arrayListDoUong = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        doUong = getItem(position);
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_do_uong,parent,false);
        TextView tvTen = convertView.findViewById(R.id.themsp_tenSp);
        TextView tvGia = convertView.findViewById(R.id.themsp_giaSp);
        ImageButton btnXoa = convertView.findViewById(R.id.themsp_btnXoa);
        tvTen.setText(doUong.tenMon);
        tvGia.setText(doUong.donGia + "");

        ImageButton imageButton = convertView.findViewById(R.id.themsp_btnXoa);
        imageButton.setFocusable(false);
        imageButton.setFocusableInTouchMode(false);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        return  convertView;
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa sản phẩm");
        builder.setMessage("Bạn có muốn xóa sản phẩm này không?");
        builder.setCancelable(false);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Xử lý sự kiện xóa", Toast.LENGTH_SHORT).show();
                Log.d("maMon",doUong.getMaMon() + "");
                ThucDonServices thucDonServices = APIClient.getClient().create(ThucDonServices.class);
                Call<Void> call = thucDonServices.deleteDoUong(doUong.getMaMon());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
