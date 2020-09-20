package com.example.appquanlybancafe.toan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ThucDonServices {
    @GET("TblThucDons")
    Call<ArrayList<DoUong>> findAll();

    @GET("TblThucDons/getTheoPhoBien")
    Call<ArrayList<DoUong>> findDoUongPhoBien();

    @GET("find/{maMon}")
    Call<DoUong> find(@Query("maMon") int maMon);

    @GET("TblThucDons/getTheoLoai/{maLoai}")
    Call<ArrayList<DoUong>> findTheoMaLoai(@Path("maLoai") int maLoai);

    @POST("TblThucDons/create")
    Call<Void> create(@Body DoUong doUong);

    @POST("TblThucDons")
    Call<DoUong> save(@Body DoUong doUong);

    @PUT("TblThucDons/{id}")
    Call<DoUong> update(@Path("id") int maMon, @Body DoUong doUong);

    @DELETE("TblThucDons/{id}")
    Call<Void> delete(@Query("id") int maMon);

    @DELETE("TblThucDons/{id}")
    Call<Void> deleteDoUong(@Path("id") int maMon);
}




