package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appquanlybancafe.R;
import com.example.appquanlybancafe.len.OrderingFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemBanActivity extends AppCompatActivity {

    ArrayList<Ban> arrBan;
    ImageButton imageButton_Them;
    GridView gridView;
    Button btn_Them;
    TabLayout tabLayout;
    ViewPager viewPager;
    EditText edtThemBan;
    String tabText;
    ThemBanMoiAdapterGrid adapterGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_ban);
        AnhXa();
        viewPager = findViewById(R.id.ThemBan_viewPager);
        getSupportActionBar().setTitle("Sơ đồ bàn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        gridView = findViewById(R.id.gridViewTableNew);

        tabLayout = findViewById(R.id.ThemBan_tabLayout);
        //tabLayout.getTabAt(0).select();

        //setupViewPager();
        //tabLayout.setupWithViewPager(viewPager);

        setDataToGrid(0);
        tabText = "Tầng trệt";
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                    {
                        setDataToGrid(0);
                        tabText = "Tầng trệt";
                        break;
                    }
                    case 1:{
                        setDataToGrid(1);
                        tabText = "Tầng 1";
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

//    private void setupViewPager() {
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//            adapter.addFragment(new ThemBanFragment("Tầng trệt"), "Tầng trệt");
//            adapter.addFragment(new ThemBanFragment("Tầng 1"), "Tầng 1");
//            viewPager.setAdapter(adapter);
//            //viewPager.setCurrentItem(1);
//    }

    public void setDataToGrid(int maKhuVuc) {
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call call = productService.findBanByKhuVuc(maKhuVuc);

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("rs", "kết nối thành công");
                arrBan = new ArrayList<>();
                arrBan = (ArrayList<Ban>) response.body();
                if (arrBan.size() == 0) {
                    Toast.makeText(getApplicationContext(), "Arr rỗng", Toast.LENGTH_SHORT).show();
                    return;
                }
                adapterGrid = new ThemBanMoiAdapterGrid(getApplicationContext(), arrBan);
                gridView.setAdapter(adapterGrid);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("errorBan", "kết nối thất bại");
            }
        });


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object obj = gridView.getItemAtPosition(position);
                Ban ban = (Ban) obj;
                showAlertDialog(ban);
            }
        });

        btn_Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edtThemBan.getText().toString().trim())) {
                    edtThemBan.setError("Vui lòng nhập tên bàn");
                    return;
                } else {
                    Ban ban = new Ban();
                    ban.setTenBan(edtThemBan.getText().toString());
                    ban.setTrangThai(false);
                    ban.setKhuVuc(tabText);

                    //Add bàn vào arraylist cũ.
                    arrBan.add(ban);

                    ProductService productService = APIClient.getClient().create(ProductService.class);
                    productService.save(ban).enqueue(new Callback<Ban>() {
                        @Override
                        public void onResponse(Call<Ban> call, Response<Ban> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
                                setDataToGrid_Reload(arrBan);
                            }
                        }

                        @Override
                        public void onFailure(Call<Ban> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });
    }

    public void setDataToGrid_Reload(ArrayList<Ban> arrayListBan) {
        adapterGrid = new ThemBanMoiAdapterGrid(getApplicationContext(), arrayListBan);
        adapterGrid.notifyDataSetChanged();
        gridView.setAdapter(adapterGrid);
        //tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).select();
    }

    public void showAlertDialog(final Ban ban){
        AlertDialog.Builder builder = new AlertDialog.Builder(ThemBanActivity.this);
        builder.setTitle("Xóa bàn");
        builder.setMessage("Bạn có muốn xóa bàn này không?");
        builder.setCancelable(false);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //deleteItemInData(ban);
                HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
                hoaDonServices.findHDByMaBan(ban.getMaBan()).enqueue(new Callback<HoaDon>() {
                    @Override
                    public void onResponse(Call<HoaDon> call, Response<HoaDon> response) {
                        //Không tìm thấy bàn trong hóa đơn
                        HoaDon hdTemp = (HoaDon) response.body();
                        if(hdTemp == null){
                            //Tiến hành xóa
                            ProductService productService = APIClient.getClient().create(ProductService.class);
                            productService.deleteBan(ban.getMaBan()).enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    if (response.isSuccessful()) {
                                        arrBan.remove(ban);
                                        Toast.makeText(getApplicationContext(), "Xóa " + ban.getTenBan() +  "thành công", Toast.LENGTH_LONG).show();
                                        setDataToGrid_Reload(arrBan);
                                    }
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
                                }
                            });
                            //adapterGrid.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Bàn " + ban.getTenBan() +  " đã được sử dụng, không thể xóa.", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<HoaDon> call, Throwable t) {

                    }
                });
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void deleteItemInData(final Ban ban){
        ProductService productService = APIClient.getClient().create(ProductService.class);
        productService.deleteBan(ban.getMaBan()).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Xóa " + ban.getTenBan() +  "thành công", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
            }
        });
        //setDataToGrid(tabText);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void AnhXa(){
        btn_Them = (Button) findViewById(R.id.ThemBan_btnThem);
        edtThemBan = findViewById(R.id.ThemBan_edtText);
    }

}
