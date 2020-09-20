package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.appquanlybancafe.R;
import com.github.mikephil.charting.charts.LineChart;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongKeActivity extends AppCompatActivity {

    ArrayList<HoaDon> arrayListHoaDon;

    TextView tien, tiencalendar, doanhthuCalendar;
    Button btnBarChart;
    CalendarView calendarView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);

        getSupportActionBar().setTitle("Trang thống kê");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tien = findViewById(R.id.tvTien);
        tiencalendar = findViewById(R.id.tvTienCalendar);
        calendarView = findViewById(R.id.calendar);
        doanhthuCalendar = findViewById(R.id.tvDoanhThuCalendar);

        btnBarChart = findViewById(R.id.thongke_btnBarChart);

        btnBarChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThongKeActivity.this, BarChart_Activity.class));
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
                HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
                Call call = hoaDonServices.getTongTienTheoTGInput(dayOfMonth,month+1,year);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        Log.wtf("lstt", "Kết nối thành công");
                        String tongTienNgay = "";
                        String result;
                        result = (String) response.body();
                        doanhthuCalendar.setText("D.thu ngày " + dayOfMonth + "/" + (month + 1) +"/" + year + " là: " );
                        if(result.isEmpty() || result == null){
                            tiencalendar.setText("0 VNĐ");
                        }
                        else{
                            Log.wtf("tongtien", result);
                            tongTienNgay = formatTienViet(Double.valueOf(result.trim()));
                            tiencalendar.setText(tongTienNgay + " VNĐ");
                        }

                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Log.wtf("lstt", "Lỗi kết nối");
                    }
                });
            }
        });

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        Call call = hoaDonServices.getTongTienTheoTGInput(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH) + 1,calendar.get(Calendar.YEAR));
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.wtf("lstt", "Kết nối thành công");
                String tongTienNgay = "";
                String result;
                result = (String) response.body();

                if(result.isEmpty() || result == null){
                    tien.setText("0 VNĐ");
                }
                else{
                    Log.wtf("tongtien", result);
                    tongTienNgay = formatTienViet(Double.valueOf(result.trim()));
                    tien.setText(tongTienNgay + " VNĐ");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("lstt", "Lỗi kết nối");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public String formatTienViet(double input){
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
        String str1 = currencyVN.format(input);
        return  str1;
    }


}
