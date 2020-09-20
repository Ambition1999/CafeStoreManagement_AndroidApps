package com.example.appquanlybancafe.len;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appquanlybancafe.R;
import com.example.appquanlybancafe.toan.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main4Activity extends AppCompatActivity {
    TextView txtGioHang;
    ImageButton imgSee;
    Utils utils;
    TextView txtLuu;
    String maBan = "x";
    String trangThaiBan = "y";
    private Dialog dialog;
    datBan hd;
    static TextView txtThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        txtGioHang = findViewById(R.id.button2);
        txtLuu = findViewById(R.id.button1);
        imgSee = findViewById(R.id.btnXemGio);
        txtThanhToan = findViewById(R.id.button3);
        utils = new Utils();
        final Fragment Cartfragment = new CartFragment();
        final Fragment Categoriesfragment = new CategoriesFragment();
        try {
            Intent i = getIntent();
            if (i.getStringExtra("truyenMaBan") != null) {
                maBan = i.getStringExtra("truyenMaBan");
                trangThaiBan = i.getStringExtra("truyenTrangThai");
                loadFragment(Categoriesfragment);
            } else if (i.getParcelableExtra("truyenHD") != null) {
                Log.d("chackovo","phaiko");
                hd = i.getParcelableExtra("truyenHD");
                maBan = hd.getSoBan();
                trangThaiBan = "true";
                utils.naruto = 1;
                utils.co = 1;
                loadFragment(Cartfragment);
            }
        } catch (Exception e) {

        }
        imgSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (utils.naruto == 0) {
                    loadFragment(Cartfragment);
                    utils.naruto = 1;
                } else {
                    xoabang0(utils.getCart());
                    loadFragment(Categoriesfragment);
                    utils.naruto = 0;
                }
            }
        });
        txtGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trangThaiBan=="true") {
                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                    intent.putExtra("chonBan", hd);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Main4Activity.this,"Bàn này chưa có order",Toast.LENGTH_SHORT).show();

//                final ProductService productService=APIClient.getClient().create(ProductService.class);
//                Call cc=productService.getCTTheoMa(hd.getMaHD());
//                cc.enqueue(new Callback() {
//                    @Override
//                    public void onResponse(Call call, Response response) {
//                        ArrayList<CTDatBan> arrayList= (ArrayList<CTDatBan>) response.body();
//                        for(int i=0;i<arrayList.size();i++){
//                            Call call2=productService.xoaCT(arrayList.get(i).getMaHD(),arrayList.get(i));
//                            call2.enqueue(new Callback() {
//                                @Override
//                                public void onResponse(Call call, Response response) {
//                                    Log.d("cao","k1");
//                                }
//
//                                @Override
//                                public void onFailure(Call call, Throwable t) {
//                                    Log.d("cao","k2");
//                                }
//                            });
//                        }
//                        ////------------------
//                        Call call4=productService.xoaHD(hd.getMaHD());
//                        call4.enqueue(new Callback() {
//                            @Override
//                            public void onResponse(Call call, Response response) {
//
//                            }
//
//                            @Override
//                            public void onFailure(Call call, Throwable t) {
//
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void onFailure(Call call, Throwable t) {
//
//                    }
//                });
            }
        });
        txtThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (trangThaiBan.equals("false")) {
                        if (utils.getCart().size() > 0) {
                            Intent intent = new Intent(Main4Activity.this, thanhtoanActivity.class);
                            intent.putExtra("maBan", maBan);
                            intent.putExtra("TrangThaiBan", trangThaiBan);
                            startActivity(intent);
                        } else
                            Toast.makeText(Main4Activity.this, "Khong co san pham nao", Toast.LENGTH_SHORT).show();
                    } else if (trangThaiBan.equals("true")) {
                        Intent intent = new Intent(Main4Activity.this, thanhtoanActivity.class);
                        intent.putExtra("truyenHD2", hd);
                        Log.d("exxx", hd.getMaHD() + "");
                        startActivity(intent);
                    }
                }
                catch (Exception e){

                }
            }
        });
        txtLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //đưa hết danh sách hàng đã chọn từ giỏ vào 1 lít 'lstCT' gồm các chi tiết hóa đơn
                    final List<CTDatBan> lstCT = new ArrayList<>();
                    for (int i = 0; i < utils.getCart().size(); i++) {
                        if (utils.getCart().get(i).getSlDat() > 0) {
                            double t = utils.getCart().get(i).getDonGia() * utils.getCart().get(i).getSlDat();
                            CTDatBan ct = new CTDatBan(utils.getCart().get(i).getMaMon(), utils.getCart().get(i).getSlDat(), t);
                            lstCT.add(ct);
                        }
                    }
                    //nếu giỏ có sản phẩm thì mới thực hiện lưu
                    if (utils.getCart().size() > 0) {
                        //bàn trạng thái false
                        //bàn lần đầu được đạt
                        if (trangThaiBan.equals("false")) {
                            String currentDateTime;
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            currentDateTime = sdf1.format(new Date());
                            datBan db = new datBan(maBan, "Chua", currentDateTime, "2", Double.parseDouble((String) txtThanhToan.getText()));
                            //put order into database 'hoadon'---->
                            //put chi tiết order into database 'chitiethd'

                            ProductService productService = APIClient.getClient().create(ProductService.class);
                            mix m = new mix(db, lstCT);
                            Call call = productService.create(m);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                }
                            });
                            Toast.makeText(Main4Activity.this, "Lưu thông tin hóa đơn thành công", Toast.LENGTH_SHORT).show();
                            utils.getCart().clear();
                            utils.tienGoc = 0;
                            maBan = "x";
                            trangThaiBan = "y";
                            Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                            intent.putExtra("key_Main_Main4", "kmm4");
                            startActivity(intent);
                            finish();
                        } else if (trangThaiBan.equals("true")) {
                            //bàn trạng thái true
                            //bàn được đặt thêm lần nữa
                            //đầu tiên lấy hết chi tiêt hd theo mã hd ra
                            final ProductService productService = APIClient.getClient().create(ProductService.class);
                            Call<List<CTDatBan>> call = productService.getCTTheoMa(hd.getMaHD());
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Log.d("tryon","1");
                                    ArrayList<CTDatBan> arrayList = (ArrayList<CTDatBan>) response.body();
                                    //thực hiện cộng dồn số lượng nếu trùng sản phẩm
                                    for (int i = 0; i < arrayList.size(); i++) {
                                        for (int j = 0; j < lstCT.size(); j++) {
                                            if (arrayList.get(i).getMaMon().equals(lstCT.get(j).getMaMon())) {
                                                int slCu = arrayList.get(i).getSl();
                                                int slMoi = lstCT.get(j).getSl();
                                                double tong = arrayList.get(i).getTong() + lstCT.get(j).getTong();
                                                CTDatBan ct = new CTDatBan(arrayList.get(i).getMaHD(), arrayList.get(i).getMaMon(), slCu + slMoi, tong);
                                                Log.d("tryon","2");
                                                Call c = productService.updateCT(arrayList.get(i).getMaHD(), ct);
                                                Log.d("tryon","3");
                                                c.enqueue(new Callback() {
                                                    @Override
                                                    public void onResponse(Call call, Response response) {
                                                        Log.d("tryon","41");
                                                    }

                                                    @Override
                                                    public void onFailure(Call call, Throwable t) {
                                                        Log.d("tryon","42");
                                                    }
                                                });
                                                lstCT.remove(lstCT.get(j));
                                                j--;
                                            }
                                        }
                                    }
                                    //cập nhật lại thành tiền cho bàn
                                    datBan d = new datBan(hd.getMaHD(), hd.getSoBan(), "Chua", hd.getTgvao(), null, "2", utils.tinhTien(), "Cash");
                                    Call ca = productService.update(hd.getMaHD(), d);
                                    ca.enqueue(new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {

                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {

                                        }
                                    });
                                    //thêm những sản phẩm mới vào hóa đơn
                                    for (int i = 0; i < lstCT.size(); i++) {
                                        CTDatBan ct = lstCT.get(i);
                                        ct.setMaHD(hd.getMaHD());
                                        Call cc = productService.createChiTiet(ct);
                                        cc.enqueue(new Callback() {
                                            @Override
                                            public void onResponse(Call call, Response response) {

                                            }

                                            @Override
                                            public void onFailure(Call call, Throwable t) {

                                            }
                                        });
                                    }
                                    Log.d("tryon","7");
                                    Toast.makeText(Main4Activity.this, "Lưu thông tin hóa đơn thành công", Toast.LENGTH_SHORT).show();
                                    utils.co = 0;
                                    utils.getCart().clear();
                                    utils.tienGoc = 0;
                                    maBan = "x";
                                    trangThaiBan = "y";
                                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                                    intent.putExtra("key_Main_Main4", "kmm4");
                                    startActivity(intent);
                                    finish();
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                }
                            });
                        }
                    } else
                        Toast.makeText(Main4Activity.this, "Mời chọn hàng trước khi lưu", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    utils.co = 0;
                    utils.getCart().clear();
                    utils.tienGoc = 0;
                    maBan = "x";
                    trangThaiBan = "y";
                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                    intent.putExtra("key_Main_Main4", "kmm4");
                    startActivity(intent);
                    finish();
                }
            }
        });
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        utils.co = 0;
        utils.getCart().clear();
        utils.tienGoc = 0;
        maBan = "x";
        trangThaiBan = "y";
        Intent intent = new Intent(Main4Activity.this, MainActivity.class);
        intent.putExtra("key_Main_Main4", "kmm4");
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void xoabang0(ArrayList<ThucDon> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getSlDat() == 0) {
                arrayList.remove(i);
                i--;
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        utils.co = 0;
        utils.tienGoc=0;
        if (utils.getCart().size() > 0)
            showAlertDialog();
        else
            finish();
        return super.onKeyDown(keyCode, event);
    }

    //    public void showDialog(){
//        dialog = new Dialog(Main4Activity.this);
//        dialog.setTitle("");
//        dialog.setContentView(R.layout.dialog);
//        dialog.show();
//    }
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Warning!!!");
        builder.setMessage("Bạn có muốn lưu lại thông tin giỏ hàng hay không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                utils.getCart().clear();
                utils.tienGoc = 0;
                maBan = "x";
                trangThaiBan = "y";
                Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                intent.putExtra("key_Main_Main4", "kmm4");
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int j) {
                dialogInterface.dismiss();
                try {
                    //đưa hết danh sách hàng đã chọn từ giỏ vào 1 lít 'lstCT' gồm các chi tiết hóa đơn
                    final List<CTDatBan> lstCT = new ArrayList<>();
                    for (int i = 0; i < utils.getCart().size(); i++) {
                        if (utils.getCart().get(i).getSlDat() > 0) {
                            double t = utils.getCart().get(i).getDonGia() * utils.getCart().get(i).getSlDat();
                            CTDatBan ct = new CTDatBan(utils.getCart().get(i).getMaMon(), utils.getCart().get(i).getSlDat(), t);
                            lstCT.add(ct);
                        }
                    }
                    //nếu giỏ có sản phẩm thì mới thực hiện lưu
                    if (utils.getCart().size() > 0) {
                        //bàn trạng thái false
                        //bàn lần đầu được đạt
                        if (trangThaiBan.equals("false")) {
                            String currentDateTime;
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            currentDateTime = sdf1.format(new Date());
                            datBan db = new datBan(maBan, "Chua", currentDateTime, "2", Double.parseDouble((String) txtThanhToan.getText()));
                            //put order into database 'hoadon'---->
                            //put chi tiết order into database 'chitiethd'

                            ProductService productService = APIClient.getClient().create(ProductService.class);
                            mix m = new mix(db, lstCT);
                            Call call = productService.create(m);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                }
                            });
                            Toast.makeText(Main4Activity.this, "Lưu thông tin hóa đơn thành công", Toast.LENGTH_SHORT).show();
                            utils.getCart().clear();
                            utils.tienGoc = 0;
                            maBan = "x";
                            trangThaiBan = "y";
                            Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                            intent.putExtra("key_Main_Main4", "kmm4");
                            startActivity(intent);
                            finish();
                        } else if (trangThaiBan.equals("true")) {
                            //bàn trạng thái true
                            //bàn được đặt thêm lần nữa
                            //đầu tiên lấy hết chi tiêt hd theo mã hd ra
                            final ProductService productService = APIClient.getClient().create(ProductService.class);
                            Call<List<CTDatBan>> call = productService.getCTTheoMa(hd.getMaHD());
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    ArrayList<CTDatBan> arrayList = (ArrayList<CTDatBan>) response.body();
                                    //thực hiện cộng dồn số lượng nếu trùng sản phẩm
                                    for (int i = 0; i < arrayList.size(); i++) {
                                        for (int j = 0; j < lstCT.size(); j++) {
                                            if (arrayList.get(i).getMaMon().equals(lstCT.get(j).getMaMon())) {
                                                int slCu = arrayList.get(i).getSl();
                                                int slMoi = lstCT.get(j).getSl();
                                                double tong = arrayList.get(i).getTong() + lstCT.get(j).getTong();
                                                CTDatBan ct = new CTDatBan(arrayList.get(i).getMaHD(), arrayList.get(i).getMaMon(), slCu + slMoi, tong);
                                                Call c = productService.updateCT(arrayList.get(i).getMaHD(), ct);
                                                c.enqueue(new Callback() {
                                                    @Override
                                                    public void onResponse(Call call, Response response) {
                                                    }

                                                    @Override
                                                    public void onFailure(Call call, Throwable t) {
                                                    }
                                                });
                                                lstCT.remove(lstCT.get(j));
                                                j--;
                                            }
                                        }
                                    }
                                    //cập nhật lại thành tiền cho bàn
                                    datBan d = new datBan(hd.getMaHD(), hd.getSoBan(), "Chua", hd.getTgvao(), null, "2", utils.tinhTien(), "Cash");
                                    Call ca = productService.update(hd.getMaHD(), d);
                                    ca.enqueue(new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {

                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {

                                        }
                                    });
                                    //thêm những sản phẩm mới vào hóa đơn
                                    for (int i = 0; i < lstCT.size(); i++) {
                                        CTDatBan ct = lstCT.get(i);
                                        ct.setMaHD(hd.getMaHD());
                                        Call cc = productService.createChiTiet(ct);
                                        cc.enqueue(new Callback() {
                                            @Override
                                            public void onResponse(Call call, Response response) {

                                            }

                                            @Override
                                            public void onFailure(Call call, Throwable t) {

                                            }
                                        });
                                    }
                                    Toast.makeText(Main4Activity.this, "Lưu thông tin hóa đơn thành công", Toast.LENGTH_SHORT).show();
                                    utils.co = 0;
                                    utils.getCart().clear();
                                    utils.tienGoc = 0;
                                    maBan = "x";
                                    trangThaiBan = "y";
                                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                                    intent.putExtra("key_Main_Main4", "kmm4");
                                    startActivity(intent);
                                    finish();
                                }
                                @Override
                                public void onFailure(Call call, Throwable t) {
                                }
                            });
                        }
                    } else
                        Toast.makeText(Main4Activity.this, "Mời chọn hàng trước khi lưu", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    utils.getCart().clear();
                    utils.tienGoc = 0;
                    maBan = "x";
                    trangThaiBan = "y";
                    Intent intent = new Intent(Main4Activity.this, MainActivity.class);
                    intent.putExtra("key_Main_Main4", "kmm4");
                    startActivity(intent);
                    finish();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
