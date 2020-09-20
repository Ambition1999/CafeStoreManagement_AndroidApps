package com.example.appquanlybancafe.toan;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetDataByID {

    ArrayList<NhanVien> arrayListNV;
    ArrayList<Ban> arrayListBan, arrBan;
    Ban ban;
    public GetDataByID() {
    }

    public String getTenNV(int maNv){
        NhanVienServices nhanVienServices = APIClient.getClient().create(NhanVienServices.class);

        nhanVienServices.find(maNv).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrayListNV = (ArrayList<NhanVien>) response.body();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                return;
            }
        });
        if(arrayListNV.size() == 0){
            return "-1";
        }else
        return arrayListNV.get(0).getTenNv();
    }

    public Ban getBan(final int maBan){
        Log.wtf("mabannnn",maBan + "");
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call<List<Ban>> call = productService.findBanById(maBan);
        call.enqueue(new Callback<List<Ban>>() {
            @Override
            public void onResponse(Call<List<Ban>> call, Response<List<Ban>> response) {
                if(response.isSuccessful()){
                    List<Ban> listBan = (List<Ban>) response.body();
                    Log.wtf("sizee",listBan.size()+ "");
                    for (Ban items: listBan) {
                        if(items.getMaBan() == maBan) {
                            ban = new Ban();
                            ban = (Ban) items;
                        }
                    }
                }
                else{
                    Log.wtf("mabannn","vô mà sai");
                }
            }
            @Override
            public void onFailure(Call<List<Ban>> call, Throwable t) {
                Log.wtf("eoror","error network");
                return;
            }
        });
        Log.wtf("sl", ban.getTenBan().toString());
        return ban;
    }

    public Ban getBan2(int maBan){
        final int maBanTemp = maBan;
        final Ban[] bantemp = new Ban[1];
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call<Ban> call = productService.find(maBan);
        call.enqueue(new Callback<Ban>() {
            @Override
            public void onResponse(Call<Ban> call, Response<Ban> response) {
                Log.d("rs", "kết nối thành công");
                bantemp[0] = (Ban) response.body();
            }

            @Override
            public void onFailure(Call<Ban> call, Throwable t) {
                t.printStackTrace();
                Log.d("errorBan", "kết nối thất bại");
            }
        });
        Log.wtf("sl", bantemp[0].getTenBan().toString());
        return bantemp[0];
    }
}
