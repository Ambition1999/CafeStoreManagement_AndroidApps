package com.example.appquanlybancafe.toan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appquanlybancafe.R;
import com.example.appquanlybancafe.len.CTDatBan;
import com.example.appquanlybancafe.len.Main4Activity;
import com.example.appquanlybancafe.len.datBan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderFragmentTemp extends Fragment {
    GridView gridView;
    OrderedAdapterGridTemp orderedAdapterGrid;
    ImageButton imageButton;
    TextView tvTongSoBan;
    Button btn_DangMo, btn_TangTret, btn_Tang1;
    ArrayList<HoaDon> arrayListHD;
    ArrayList<NhanVien> arrayListNhanVien;
    ArrayList<Ban> arrBan;
    datBan chonBan=null;
    ArrayList<datBan> db;
    datBan d;
    ArrayList<CTDatBan> arrayListCT1;
    public OrderFragmentTemp() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_temp, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa(view);

        //setDataByArrayList();
        setDataToGrid();

        Intent intent=getActivity().getIntent();
        if(intent.getParcelableExtra("chonBan")!=null){
            chonBan=intent.getParcelableExtra("chonBan");
        }

        btn_DangMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                setDataToGrid();
            }
        });

        btn_TangTret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                setDataToGrid("Tầng trệt");
            }
        });

        btn_Tang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDataByArrayList();
                setDataToGrid("Tầng 1");
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent=new Intent(getContext(), Main4Activity.class);
                db=new ArrayList<>();
                arrayListCT1=new ArrayList<>();
                d=new datBan();
                final String tt = arrBan.get(position).getTrangThai()+"";
                final int maB = arrBan.get(position).getMaBan();
                if(tt.equals("true")&&chonBan!=null){
                    Toast.makeText(getActivity(),"Bàn này không trống",Toast.LENGTH_SHORT).show();
//                    com.example.appquanlybancafe.len.ProductService productService= com.example.appquanlybancafe.len.APIClient.getClient().create(com.example.appquanlybancafe.len.ProductService.class);
//                    Call call=productService.getCTTheoMa(chonBan.getMaHD());
//                    call.enqueue(new Callback() {
//                        @Override
//                        public void onResponse(Call call, Response response) {
//                            arrayListCT1= (ArrayList<CTDatBan>) response.body();
//                        }
//
//                        @Override
//                        public void onFailure(Call call, Throwable t) {
//
//                        }
//                    });
//                    Call call2 = productService.getHD();
//                    call2.enqueue(new Callback() {
//                        @Override
//                        public void onResponse(Call call, Response response) {
//                            db = (ArrayList<datBan>) response.body();
//                            for (int i = 0; i < db.size(); i++) {
//                                if (db.get(i).getSoBan().equals(maB + "") && db.get(i).getTt().equals("Chua")) {
//                                    d = db.get(i);
//                                    break;
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call call, Throwable t) {
//
//                        }
//                    });
                }
                else if(tt.equals("false")&&chonBan!=null){
                    Log.d("check","c"+chonBan.getMaHD());
                    final com.example.appquanlybancafe.len.ProductService productService= com.example.appquanlybancafe.len.APIClient.getClient().create(com.example.appquanlybancafe.len.ProductService.class);
                    datBan dd=new datBan(chonBan.getMaHD(),maB+"","Chua",chonBan.getTgvao(),chonBan.getTgra(),"2",chonBan.getTong(),chonBan.getPhuongThuc());
                    Call cc=productService.update(chonBan.getMaHD(),dd);
                    cc.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                    Call cc1=productService.getBanTheoID(Integer.parseInt(chonBan.getSoBan()));
                    Log.d("setnull",chonBan.getSoBan());
                    cc1.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            com.example.appquanlybancafe.len.Ban b= (com.example.appquanlybancafe.len.Ban) response.body();
                            if(b!=null)
                                Log.d("setnull","lol");
                            b.setTt("False");
                            Call cc2=productService.updateBan(Integer.parseInt(chonBan.getSoBan()),b);
                            cc2.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Toast.makeText(getActivity(),"Chuyển bản thành công",Toast.LENGTH_SHORT).show();
                                    Intent intent1=new Intent(getActivity(), MainActivity.class);
                                    startActivity(intent1);
                                    getActivity().finish();
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Log.d("thanhcong","okok");
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
                else if(tt.equals("true")){
                    //ProductService productService= APIClient.getClient().create(ProductService.class);
                    com.example.appquanlybancafe.len.ProductService productService= com.example.appquanlybancafe.len.APIClient.getClient().create(com.example.appquanlybancafe.len.ProductService.class);
                    Call call = productService.getHD();
                    call.enqueue(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            db = (ArrayList<datBan>) response.body();
                            for (int i = 0; i < db.size(); i++) {
                                if (db.get(i).getSoBan().equals(maB + "") && db.get(i).getTt().equals("Chua")) {
                                    d = db.get(i);
                                    break;
                                }
                            }
                            intent.putExtra("truyenHD",d);
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(Call call, Throwable t) {

                        }
                    });
                }
                else{
                    intent.putExtra("truyenMaBan",arrBan.get(position).getMaBan()+"");
                    intent.putExtra("truyenTrangThai",arrBan.get(position).getTrangThai()+"");
                    startActivity(intent);
                }
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
                arrBan =  (ArrayList<Ban>) response.body();
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

    public void setDataByArrayList(){
        HoaDonServices hoaDonServices = APIClient.getClient().create(HoaDonServices.class);
        hoaDonServices.findAll().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrayListHD = (ArrayList<HoaDon>) response.body();
                if(arrayListHD.size() == 0){
                    Toast.makeText(getContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                orderedAdapterGrid = new OrderedAdapterGridTemp(getContext(),arrayListHD);
                orderedAdapterGrid.notifyDataSetChanged();
                gridView.setAdapter(orderedAdapterGrid);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //Toast.makeText(getContext(), "Lỗi kết nối123", Toast.LENGTH_LONG).show();
                t.printStackTrace();
                return;
            }
        });
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
                orderedAdapterGrid = new OrderedAdapterGridTemp(getContext(),arrayListHD);
                orderedAdapterGrid.notifyDataSetChanged();
                gridView.setAdapter(orderedAdapterGrid);
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
                orderedAdapterGrid = new OrderedAdapterGridTemp(getContext(),arrayListHD);
                orderedAdapterGrid.notifyDataSetChanged();
                gridView.setAdapter(orderedAdapterGrid);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }



//    public ArrayList<Ordered> insertOrder(){
//        ArrayList<Ordered> tmp = new ArrayList<>();
//        tmp.add(new Ordered("Bàn 1","22/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 2","22/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 3","23/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 4","23/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 5","24/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 6","24/02/2020","Nguyen Van A"));
//        return  tmp;
//    }
//
//    public ArrayList<Ordered> insertOrderTangTret(){
//        ArrayList<Ordered> tmp = new ArrayList<>();
//        tmp.add(new Ordered("Bàn 1","22/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 2","22/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 3","23/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 4","23/02/2020","Nguyen Van A"));
//        return  tmp;
//    }
//    public ArrayList<Ordered> insertOrderTang1(){
//        ArrayList<Ordered> tmp = new ArrayList<>();
//        tmp.add(new Ordered("Bàn 5","24/02/2020","Nguyen Van A"));
//        tmp.add(new Ordered("Bàn 6","24/02/2020","Nguyen Van A"));
//        return  tmp;
//    }
}
