package com.example.appquanlybancafe.len;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class BackGroundService extends IntentService {

    public BackGroundService() {
        super("BackGroundService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            ProductService productService= APIClient.getClient().create(ProductService.class);
            Call<List<LoaiMon>> call=productService.findLoai();
            Response<List<LoaiMon>> response=call.execute();
            CategoriesFragment.corporations=response.body();
            Log.d("szie", CategoriesFragment.corporations.size()+"4");
        }
        catch (IOException e){
            Log.d("szie","loi");
            //  e.printStackTrace();
        }
    }
}
