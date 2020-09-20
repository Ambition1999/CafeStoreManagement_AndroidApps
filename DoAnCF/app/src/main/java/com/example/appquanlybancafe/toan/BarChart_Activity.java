package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.appquanlybancafe.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarChart_Activity extends AppCompatActivity {

    ArrayList<Item> arr;
    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart_);

        getSupportActionBar().setTitle("Doanh thu trong vòng 5 ngày");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        barChart = findViewById(R.id.thongke_barchart);

        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        Call call = hoaDonServices.findHD5NgayGanNhat();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.wtf("lstt", "Kết nối thành công");
                arr = (ArrayList<Item>) response.body();
                loadChart(arr);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.wtf("lstt", "Lỗi kết nối");
            }
        });



    }

    public void loadChart(ArrayList<Item> arr){
        ArrayList thongKeNgay = new ArrayList();
        ArrayList<String> arrDay = new ArrayList<>();
        int i = 0;
        Log.wtf("phantu", arr.size() + "");
        Log.wtf("parse", Integer.parseInt(arr.get(0).getValue()) + "");
        for (Item item: arr) {
            thongKeNgay.add(new BarEntry(i,Integer.parseInt(item.getValue())));
            arrDay.add(item.getId());
            i++;
        }
//        thongKeNgay.add(new BarEntry(21, 50000));
//        thongKeNgay.add(new BarEntry(22, 20000));
//        thongKeNgay.add(new BarEntry(23, 90000));
//        thongKeNgay.add(new BarEntry(24, 60000));
//        thongKeNgay.add(new BarEntry(25, 80000));

        BarDataSet barDataSet = new BarDataSet(thongKeNgay,"Ngày");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Thống kê doanh thu 5 ngày gần nhất");


        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(arrDay));
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setDrawAxisLine(false);
        //xAxis.setGranularity(lf);

        barChart.animateY(2000);
        barChart.invalidate();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
