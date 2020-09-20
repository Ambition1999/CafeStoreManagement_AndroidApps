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

public interface HoaDonServices {
    @GET("TblHoaDons")
    Call<ArrayList<HoaDon>> findAll();

    @GET("TblHoaDons/getHDChuaTT")
    Call<ArrayList<HoaDon>> findHDChuaTT();

    @GET("TblHoaDons/getHDDaTT")
    Call<ArrayList<HoaDon>> findHDDaTT();

    @GET("TblHoaDons/{id}")
    Call<HoaDon> find(@Path("id") int maHD);

    @GET("TblHoaDons/getHDByMaBan/{maBan}")
    Call<HoaDon> findHDByMaBan(@Path("maBan") int maBan);

    @GET("TblHoaDons/getTongTienTheoNgayInput/{ngay}-{thang}-{nam}")
    Call<String> getTongTienTheoTGInput(@Path("ngay") int ngay, @Path("thang") int thang, @Path("nam") int nam);

    @GET("TblHoaDons/getTongTienTheo5NgayGanNhat")
    Call<ArrayList<Item>> findHD5NgayGanNhat();

    @GET("find/{maLoai}")
    Call<HoaDon> findLoai(@Query("maLoai") int maLoai);

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
