package com.example.appquanlybancafe.toan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaiKhoanServices {
    @GET("TblTaiKhoans")
    Call<ArrayList<TaiKhoan>> findAll();
}
