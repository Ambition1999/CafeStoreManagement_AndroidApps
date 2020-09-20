package com.example.appquanlybancafe.toan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appquanlybancafe.R;
import com.example.appquanlybancafe.len.Main4Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragment extends Fragment {
    GridView gridView;
    OrderedAdapterGrid orderedAdapterGrid;
    ImageButton imageButton;
    TextView tvTongSoBan;
    Button btn_DangMo, btn_TangTret, btn_Tang1;
    ArrayList<HoaDon> arrayListHD = new ArrayList<>();
    ArrayList<NhanVien> arrayListNhanVien;
    ArrayList<Ban> arrBan;
    ArrayList<Ordered> arrayListOrdered;
    NhanVien nhanVien;
    Ban ban;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        //setDataByArrayList();


        //setDataToGrid();

        getDataTBlHoaDonsDangMo();


        btn_DangMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                getDataTBlHoaDonsDangMo();
            }
        });

        btn_TangTret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                getDataTBlHoaDons("Tầng trệt");
            }
        });

        btn_Tang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                getDataTBlHoaDons("Tầng 1");
            }
        });

        tvTongSoBan.setText("Tổng số bàn: " );

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),"Thêm order",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), Main4Activity.class);
                startActivity(intent);
            }
        });
    }

    public void AnhXa(View view){
        imageButton = (ImageButton) view.findViewById(R.id.btnAddOrdered);
        tvTongSoBan = (TextView) view.findViewById(R.id.trangorder_tvTongSoBan);
        gridView = view.findViewById(R.id.gridViewOdred);
        btn_DangMo = view.findViewById(R.id.btn_DangMo);
        btn_TangTret = view.findViewById(R.id.btn_TangTret);
        btn_Tang1 = view.findViewById(R.id.btn_Tang1);
    }

    public void setDataToGrid(final String tenKhuVuc){
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call call = productService.findAll();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("rs", "kết nối thành công");
                arrBan = new ArrayList<>();
                List<Ban> lst = (List<Ban>) response.body();
                for (Ban items:lst) {
                    if(items.getKhuVuc().equals(tenKhuVuc))
                        arrBan.add(items);
                }
                if(arrBan.size() == 0){
                    Toast.makeText(getContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }

                gridView.setAdapter(new ThemBanMoiAdapterGrid(getContext(),arrBan));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("errorBan", "kết nối thất bại");
            }
        });
    }


    public void setDataToGrid(){

        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call call = productService.findAll();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("rs", "kết nối thành công");
                arrBan = (ArrayList<Ban>) response.body();
                if(arrBan.size() == 0){
                    Toast.makeText(getContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                gridView.setAdapter(new ThemBanMoiAdapterGrid(getContext(),arrBan));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("errorBan", "kết nối thất bại");
            }
        });
    }


    public NhanVien getDataTblNhanVien(int maNV) {
        nhanVien = new NhanVien();
        NhanVienServices nhanVienServices = APIClient.getClient().create(NhanVienServices.class);
        nhanVienServices.find(maNV).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                nhanVien = (NhanVien) response.body();
                Log.wtf("dsHD", nhanVien.getTenNv() +" NV");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
        return nhanVien;
    }


    public Ban getDataTblBan(int maBan) {
        ban = new Ban();
        ProductService productService = APIClient.getClient().create(ProductService.class);
        productService.find(maBan).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ban = (Ban) response.body();
                Log.wtf("dsHD", ban.getTenBan()+ " Ban");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
        return ban;
    }

    public void getDataTblBan2(){
        ProductService productService = APIClient.getClient().create(ProductService.class);
        try{
            arrBan = productService.findAll().execute().body();
        }catch (Exception ex){
            Toast.makeText(getContext(),"Lỗi getDataBan",Toast.LENGTH_SHORT);
        }


    }

    public void getDataTBlHoaDonsDangMo(){
        arrayListHD = new ArrayList<>();
        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        hoaDonServices.findHDChuaTT().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrayListHD = (ArrayList<HoaDon>) response.body();
                if (arrayListHD.size() == 0) {
                    Toast.makeText(getContext(), "Arr rỗng", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.wtf("dsHD", arrayListHD.size() + " HD");

                arrayListOrdered = new ArrayList<>();
                Log.wtf("dsHD", arrayListHD.size() + "Saize");
                if (arrayListHD.size() != 0) {
                    for (final HoaDon items : arrayListHD) {
                        Log.wtf("trangthaiHD", items.getTrangThai());
                        ProductService productService = APIClient.getClient().create(ProductService.class);
                        productService.find(items.getMaBan()).enqueue(new Callback<Ban>() {
                            @Override
                            public void onResponse(Call<Ban> call, Response<Ban> response) {
                                final Ban banHD = (Ban) response.body();
                                Log.wtf("banHD", banHD.getTenBan());
                                NhanVienServices nhanVienServices = APIClient.getClient().create(NhanVienServices.class);
                                nhanVienServices.find(items.getMaNv()).enqueue(new Callback<NhanVien>() {
                                    @Override
                                    public void onResponse(Call<NhanVien> call, Response<NhanVien> response) {
                                        NhanVien nvHD = (NhanVien) response.body();
                                        Log.wtf("banHD", banHD.getTenBan());
                                        Ordered ordered = new Ordered(items.maHd, banHD.maBan, nvHD.maNv, banHD.tenBan, items.tgvao, "2020-07-21T00:37:00", nvHD.tenNv, banHD.khuVuc, items.trangThai);
                                        arrayListOrdered.add(ordered);
                                        if (arrayListOrdered.size() == 0) {
                                            return;
                                        }
                                        orderedAdapterGrid = new OrderedAdapterGrid(getContext(), arrayListOrdered);
                                        gridView.setAdapter(orderedAdapterGrid);
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
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    public void getDataTBlHoaDons(final String tenKhuVuc){
        arrayListHD = new ArrayList<>();
        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        hoaDonServices.findHDChuaTT().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrayListHD = (ArrayList<HoaDon>) response.body();
                if (arrayListHD.size() == 0) {
                    Toast.makeText(getContext(), "Arr rỗng", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.wtf("dsHD", arrayListHD.size() + " HD");

                arrayListOrdered = new ArrayList<>();
                Log.wtf("dsHD", arrayListHD.size() + "Saize");
                if (arrayListHD.size() != 0) {
                    for (final HoaDon items : arrayListHD) {
                        Log.wtf("trangthaiHD", items.getTrangThai());
                        ProductService productService = APIClient.getClient().create(ProductService.class);
                        productService.find(items.getMaBan()).enqueue(new Callback<Ban>() {
                            @Override
                            public void onResponse(Call<Ban> call, Response<Ban> response) {
                                final Ban banHD = (Ban) response.body();
                                Log.wtf("banHD", banHD.getTenBan());
                                if (banHD.getKhuVuc().equals(tenKhuVuc)) {
                                    NhanVienServices nhanVienServices = APIClient.getClient().create(NhanVienServices.class);
                                    nhanVienServices.find(items.getMaNv()).enqueue(new Callback<NhanVien>() {
                                        @Override
                                        public void onResponse(Call<NhanVien> call, Response<NhanVien> response) {
                                            NhanVien nvHD = (NhanVien) response.body();
                                            Log.wtf("banHD", banHD.getTenBan());
                                            Ordered ordered = new Ordered(items.maHd, banHD.maBan, nvHD.maNv, banHD.tenBan, items.tgvao, "2020-07-21T00:37:00", nvHD.tenNv, banHD.khuVuc, items.trangThai);
                                            arrayListOrdered.add(ordered);
                                            if (arrayListOrdered.size() == 0) {
                                                return;
                                            }
                                            orderedAdapterGrid = new OrderedAdapterGrid(getContext(), arrayListOrdered);
                                            gridView.setAdapter(orderedAdapterGrid);
                                        }

                                        @Override
                                        public void onFailure(Call<NhanVien> call, Throwable t) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onFailure(Call<Ban> call, Throwable t) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    public void CombineClass() {
        arrayListOrdered = new ArrayList<>();
        Log.wtf("dsHD", arrayListHD.size() + "Saize");
        if(arrayListHD.size() != 0) {
            for (HoaDon items : arrayListHD) {
                Log.wtf("trangthaiHD", items.getTrangThai());
                Ban banHD = getDataTblBan(items.getMaBan());
                NhanVien nvHD = getDataTblNhanVien(items.getMaNv());
                Ordered ordered = new Ordered(items.maHd, banHD.maBan, nvHD.maNv, banHD.tenBan, items.tgvao, "2020-07-21T00:37:00", nvHD.tenNv, banHD.khuVuc, items.trangThai);
                arrayListOrdered.add(ordered);
            }
        }
        if(arrayListOrdered.size() == 0){
            return;
        }
        orderedAdapterGrid = new OrderedAdapterGrid(getContext(),arrayListOrdered);
        gridView.setAdapter(orderedAdapterGrid);
    }

    public Ban getBanByID(int maBan){
        for (Ban item: arrBan) {
            if(item.getMaBan() == maBan)
                return item;
        }
        Ban banNull = new Ban();
        return banNull;
    }

    public NhanVien getNhanVienByID(int maNV){
        for (NhanVien item: arrayListNhanVien) {
            if(item.getMaNv() == maNV)
                return item;
        }
        NhanVien nvNull = new NhanVien();
        return nvNull;
    }

    public void setDataByArrayList_TheoTang(){
        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        hoaDonServices.findAll().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                arrayListHD = (ArrayList<HoaDon>) response.body();
                if(arrayListHD.size() == 0){
                    Toast.makeText(getContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

    public void setDataToArrayListNhanVien(ArrayList<NhanVien> arr){
        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        hoaDonServices.findAll().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                arrayListHD = (ArrayList<HoaDon>) response.body();
                if(arrayListHD.size() == 0){
                    Toast.makeText(getContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }

}
