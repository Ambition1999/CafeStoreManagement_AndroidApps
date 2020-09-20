package com.example.appquanlybancafe.len;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    @GET("TblThucDons")
    Call<List<ThucDon>> findTD();

    @GET("TblHoaDons")
    Call<List<datBan>> getHD();

    @GET("TblLoaiMons")
    Call<List<LoaiMon>> findLoai();

//    @POST("TblHoaDons")
//    Call<Void> create(@Body Hashtable hashtable);

//    @FormUrlEncoded
    @POST("TblHoaDons")
    Call<Void> create(@Body mix m);
    @PUT("TblHoaDons/{id}")
    Call<Void> update(@Path("id") int ma, @Body datBan db);

//    @PUT("TblHoaDons")
//    Call<Void> update2(@Body datBan db);

    @POST("TblChiTietHds")
    Call<Void> createChiTiet(@Body CTDatBan ct);

    @GET("TblChiTietHds")
    Call<List<CTDatBan>> getCT();

    @GET("TblChiTietHds/events/{id}")
    Call<List<CTDatBan>>  getCTTheoMa(@Path("id") int ma);

    @PUT("TblChiTietHds/events/{id}")
    Call<Void>  updateCT(@Path("id") int ma, @Body CTDatBan ct);



    @GET("TblBans")
    Call<List<Ban>> getBan();

    //@DELETE("TblChiTietHds/events/{id}")
    @HTTP(method = "DELETE", path = "TblChiTietHds/events/{id}", hasBody = true)
    Call<Void> xoaCT(@Path("id") int ma, @Body CTDatBan ct);

    @DELETE("TblHoaDons/{id}")
    Call<Void> xoaHD(@Path("id") int ma);

    @PUT("TblBans/{id}")
    Call<Void> updateBan(@Path("id") int ma, @Body Ban ban);

    @GET("TblBans/events/{id}")
    Call<Ban> getBanTheoID(@Path("id") int ma);

    @GET("TblBans/{id}")
    Call<Ban> getBanTheoID1(@Path("id") int ma);

    @GET("TblThucDons/{id}")
    Call<ThucDon> getThucDonByMa(@Path("id") int ma);
}