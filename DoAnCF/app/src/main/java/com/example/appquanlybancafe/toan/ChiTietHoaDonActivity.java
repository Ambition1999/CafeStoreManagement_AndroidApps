package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.appquanlybancafe.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChiTietHoaDonActivity extends AppCompatActivity {

    TextView tvMaHD, tvTenBan, tvKhuVuc, tvTgVao, tvTgRa, tvTenNV, tvTongTien;
    HoaDon hoaDon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoa_don);

        AnhXa();

        Intent intent = getIntent();
        if(intent.getParcelableExtra("hoaDon") != null) {
            hoaDon = (HoaDon) intent.getParcelableExtra("hoaDon");
            getSupportActionBar().setTitle("Chi tiết hóa đơn " + hoaDon.getMaHd());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            tvMaHD.setText("Mã hóa đơn: " + hoaDon.getMaHd());
            ConvertFunction convertF = new ConvertFunction();
            try{

                String TgTemp = convertF.dateString(hoaDon.getTgvao());
                tvTgVao.setText("Thời gian vào: " + TgTemp);
            }catch (Exception ex){

            }
            try{
                String TgTemp = convertF.dateString(hoaDon.getTgra());
                tvTgRa.setText("Thời gian ra: " + TgTemp);
            }catch (Exception ex){

            }
            tvTongTien.setText("Tổng tiền thanh toán: " + hoaDon.getTongTienTt() + "VNĐ");
            ProductService productService = APIClient.getClient().create(ProductService.class);
            productService.find(hoaDon.getMaBan()).enqueue(new Callback<Ban>() {
                @Override
                public void onResponse(Call<Ban> call, Response<Ban> response) {
                    Ban ban = (Ban) response.body();
                    tvTenBan.setText("Bàn số: " + ban.getTenBan());
                    tvKhuVuc.setText("Khu vực: " + ban.getKhuVuc());
                    NhanVienServices nhanVienServices = APIClient.getClient().create(NhanVienServices.class);
                    nhanVienServices.find(hoaDon.getMaNv()).enqueue(new Callback<NhanVien>() {
                        @Override
                        public void onResponse(Call<NhanVien> call, Response<NhanVien> response) {
                            NhanVien nhanVien = (NhanVien) response.body();
                            tvTenNV.setText("Nhân viên : " + nhanVien.getTenNv());
                        }

                        @Override
                        public void onFailure(Call<NhanVien> call, Throwable t) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<Ban> call, Throwable t) {

                }
            });
        }

    }

    public void AnhXa(){
        tvMaHD = findViewById(R.id.cthd_tvMaHD);
        tvKhuVuc = findViewById(R.id.cthd_tvKhuVuc);
        tvTenBan = findViewById(R.id.cthd_tvBan);
        tvTenNV = findViewById(R.id.cthd_tvTenNV);
        tvTgVao = findViewById(R.id.cthd_tvTGVao);
        tvTgRa = findViewById(R.id.cthd_tvTGRa);
        tvTongTien = findViewById(R.id.cthd_tvTongTien);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
