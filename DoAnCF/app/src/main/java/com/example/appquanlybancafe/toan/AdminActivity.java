package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    Button btnThemMon, btnThemBan, btnThoat, btnLSTT;
    ArrayList<DoUong> arrayList;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getSupportActionBar().setTitle("Trang quản lý");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        AnhXa();
        btnThemMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ThemMonActivity.class);
                startActivity(intent);
            }
        });

        btnThemBan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),ThemBanActivity.class);
                startActivity(intent);
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClearAccount();
                Intent intent = new Intent(getApplication(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btnLSTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(),LichSuThanhToanActivity.class);
                startActivity(intent);
            }
        });
    }

    public void AnhXa(){
        btnThemMon = findViewById(R.id.btn_themMonAn);
        btnThemBan = findViewById(R.id.main_btn_themBan);
        btnThoat = findViewById(R.id.btn_DangXuat);
        btnLSTT = findViewById(R.id.btn_LSThanhToan);
    }

    public void ClearAccount(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
