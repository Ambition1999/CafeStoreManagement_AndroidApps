package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LichSuThanhToanActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HoaDon> arrayListHoaDon;
    LichSuThanhToanAdapter lichSuThanhToanAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_thanh_toan);

        getSupportActionBar().setTitle("Lịch sử thanh toán");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listView = findViewById(R.id.lstt_listView);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        Call call = hoaDonServices.findHDDaTT();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.wtf("lstt","Kết nối thành công");
                arrayListHoaDon = new ArrayList<>();
                arrayListHoaDon = (ArrayList<HoaDon>) response.body();
                Log.wtf("lstt",arrayListHoaDon.size() + "");
                Collections.reverse(arrayListHoaDon);
                listView.setAdapter(new LichSuThanhToanAdapter(getApplicationContext(),arrayListHoaDon));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("lstt","Lỗi kết nối");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LichSuThanhToanActivity.this, ChiTietHoaDonActivity.class);
                intent.putExtra("hoaDon", (Parcelable) arrayListHoaDon.get(position));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_thong_ke, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_ThongKe){
            Intent intent = new Intent(getApplication(),ThongKeActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
