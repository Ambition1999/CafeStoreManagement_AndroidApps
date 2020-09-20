package com.example.appquanlybancafe.toan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {

    @GET("TblBans")
    Call<ArrayList<Ban>> findAll();

    @GET("TblBans/{id}")
    Call<Ban> find(@Path("id") int maBan);

    // 0 là tầng trệt, 1 là tầng 1
    @GET("TblBans/getBanKhuVuc/{khuvuc}")
    Call<ArrayList<Ban>> findBanByKhuVuc(@Path("khuvuc") int maKhuVuc);

    @GET("TblBans/{id}")
    Call<List<Ban>> findBanById(@Path("id") int maBan);

    @POST("create")
    Call<Void> create(@Body Ban ban);

    @POST("TblBans")
    Call<Ban> save(@Body Ban ban);

    @DELETE("TblBans/{id}")
    Call<Void> deleteBan(@Path("id") int maBan);


}

