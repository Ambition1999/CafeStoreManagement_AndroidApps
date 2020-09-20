package com.example.appquanlybancafe.toan;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NhanVienServices {
    @GET("TblNhanViens")
    Call<ArrayList<NhanVien>> findAll();

    @GET("TblNhanViens/{id}")
    Call<NhanVien> find(@Path("id") int maNv);

    @POST("TblHoaDons/create")
    Call<Void> create(@Body HoaDon hoaDon);

    @POST("TblHoaDons")
    Call<HoaDon> save(@Body HoaDon hoaDon);

    @PUT("update")
    Call<Void> update(@Body HoaDon hoaDon);

    @DELETE("TblHoaDons/{id}")
    Call<Void> delete(@Query("id") int maHd);

    @DELETE("TblHoaDons/{id}")
    Call<Void> deleteDoUong(@Path("id") int maHd);
}
