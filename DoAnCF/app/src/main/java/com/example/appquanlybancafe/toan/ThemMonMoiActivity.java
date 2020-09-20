package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.POST;

public class ThemMonMoiActivity extends AppCompatActivity {


    TextView nhom, loai;
    ImageButton imageButton_Nhom, imageButton_Loai;
    EditText edtTenMon, edtDVT, edtGiaBan;
    String[] arr = new String[]{"Cafe", "Nước ngọt", "Nước ép"};
    String title;
    DoUong doUong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon_moi);

        AnhXa();


        title = "Thêm thức uống mới";
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent.getParcelableExtra("ChiTietMon") != null){
            doUong = new DoUong();
            doUong = intent.getParcelableExtra("ChiTietMon");
            edtTenMon.setText(doUong.getTenMon());
            edtGiaBan.setText(String.valueOf(doUong.getDonGia()));
            edtDVT.setText(doUong.getDonViTinh());
            loai.setText(getTenLoaiByMaLoai(doUong.getMaLoai()));
            getSupportActionBar().setTitle("Chỉnh sửa thức uống");
        }


        imageButton_Loai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = "Chọn loại";
                showAlertDialog_Loai(title,arr);
            }
        });

//        imageButton_Nhom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = "Chọn nhóm";
//                showAlertDialog_Loai(title,arr);
//            }
//        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_them_mon_moi, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mnu_Them_btnSave) {
            if (checkFormNotNull()) {
                Log.d("title", getSupportActionBar().getTitle().toString());
                Toast.makeText(getApplicationContext(), getSupportActionBar().getTitle().toString(), Toast.LENGTH_SHORT).show();
                if (getSupportActionBar().getTitle().toString().equals(title)) {
                    int maLoai = 1;
                    if (loai.getText().toString().trim().equals("Nước ngọt"))
                        maLoai = 2;
                    else if (loai.getText().toString().trim().equals("Nước ép"))
                        maLoai = 5;
                    DoUong doUong = new DoUong();
                    try {
                        doUong.setTenMon(edtTenMon.getText().toString().trim());
                        doUong.setDonViTinh(edtDVT.getText().toString().trim());
                        doUong.setDonGia(Double.parseDouble(edtGiaBan.getText().toString().trim()));
                        doUong.setMaLoai(maLoai);
                        doUong.setHinhAnh("Espresso.jpg");
                        doUong.setPhoBien(false);
                        doUong.setTuyChonThem("abc");

                        ThucDonServices thucDonServices = APIClient.getClient().create(ThucDonServices.class);
                        thucDonServices.save(doUong).enqueue(new Callback<DoUong>() {
                            @Override
                            public void onResponse(Call<DoUong> call, Response<DoUong> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<DoUong> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                                return;
                            }
                        });
                        Intent intent = new Intent(ThemMonMoiActivity.this, ThemMonActivity.class);
                        startActivity(intent);
                        finish();

                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    int maLoai = 1;
                    if (loai.getText().toString().trim().equals("Nước ngọt"))
                        maLoai = 2;
                    else if (loai.getText().toString().trim().equals("Nước ép"))
                        maLoai = 5;
                    DoUong doUong_New = new DoUong();
                    try {
                        doUong_New.setMaMon(doUong.getMaMon());
                        doUong_New.setTenMon(edtTenMon.getText().toString().trim());
                        doUong_New.setDonViTinh(edtDVT.getText().toString().trim());
                        doUong_New.setDonGia(Double.parseDouble(edtGiaBan.getText().toString().trim()));
                        doUong_New.setMaLoai(maLoai);
                        doUong_New.setHinhAnh("Espresso.jpg");
                        doUong_New.setPhoBien(false);
                        doUong_New.setTuyChonThem("abc");

                        ThucDonServices thucDonServices = APIClient.getClient().create(ThucDonServices.class);
                        Call<DoUong> call = thucDonServices.update(doUong.getMaMon(), doUong_New);
                        call.enqueue(new Callback<DoUong>() {
                            @Override
                            public void onResponse(Call<DoUong> call, Response<DoUong> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(getApplicationContext(), "Vô nhưng không update đc", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<DoUong> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                                return;
                            }
                        });
                        Intent intent = new Intent(ThemMonMoiActivity.this, ThemMonActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                return true;
            }
            else {
                Toast.makeText(getApplicationContext(), "Vui lòng điền đủ các trường dữ liệu", Toast.LENGTH_LONG).show();
            }
        }

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog_Loai(String title, final String[] arr){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title).setSingleChoiceItems(arr,-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loai.setText(arr[which]);
            }
        });
        builder.setCancelable(false);
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getContext(), "Xử lý sự kiện xóa", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void AnhXa(){
        edtTenMon = findViewById(R.id.them_mon_moi_edtTenMon);
        edtDVT = findViewById(R.id.them_mon_moi_edtDVT);
        edtGiaBan = findViewById(R.id.them_mon_moi_edtGiaBan);
        //nhom = findViewById(R.id.them_mon_moi_tvChonNhom);
        loai = findViewById(R.id.them_mon_moi_tvChonLoai);
        imageButton_Loai = findViewById(R.id.them_mon_moi_btnChonLoai);
        //imageButton_Nhom = findViewById(R.id.them_mon_moi_btnChonNhom);
    }

    public String getTenLoaiByMaLoai(int maLoai){
        switch (maLoai){
            case 1:
                return "Cafe";
            case 2:
                return "Nước ngọt";
            case 5:
                return "Nước ép";
            default:
                return "Cafe";
        }
    }

    public boolean checkFormNotNull(){
        String tenMon = edtTenMon.getText().toString().trim();
        String dvt = edtDVT.getText().toString().trim();
        String giaBan = edtGiaBan.getText().toString().trim();
        String loaiMon = loai.getText().toString().trim();
        if(tenMon.isEmpty() || dvt.isEmpty() || giaBan.isEmpty() || loaiMon.isEmpty()){
            return false;
        }
        return true;
    }
}
