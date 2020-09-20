package com.example.appquanlybancafe.len;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appquanlybancafe.R;
import com.example.appquanlybancafe.toan.MainActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class thanhtoanActivity extends AppCompatActivity {
    TextView txtTamTinh, txtTongCong, txtTienTraLai;
    EditText etTienMat;
    Utils utils;
    Button btnThanhToan;
    String maBan = "x";
    String trangThaiBan = "y";
    datBan db = new datBan();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);

        txtTamTinh = findViewById(R.id.thanhtoan_txt_tamtinh);
        txtTongCong = findViewById(R.id.thanhtoan_txt_tongcong);
        txtTienTraLai = findViewById(R.id.thanhtoan_txt_tientralai);
        etTienMat = findViewById(R.id.thanhtoan_txt_tienmat);
        btnThanhToan = findViewById(R.id.thanhtoan_thanhtoan);
        utils = new Utils();
        txtTamTinh.setText(utils.tinhTien() + "");
        txtTongCong.setText(utils.tinhTien() + "");
        etTienMat.setText(utils.tinhTien() + "");

        double x = utils.tinhTien();
        double y = Double.parseDouble(etTienMat.getText() + "");
        double z = y - x;
        txtTienTraLai.setText(z + "");

        try {
            Intent i = getIntent();
            if (i.getStringExtra("maBan") != null) {
                maBan = i.getStringExtra("maBan");
                trangThaiBan = i.getStringExtra("TrangThaiBan");
                Log.d("vo ko", "vo" + maBan + "///" + trangThaiBan);
            } else if (i.getParcelableExtra("truyenHD2") != null) {
                db = i.getParcelableExtra("truyenHD2");
                Log.d("xek", "" + db.getTong());
                trangThaiBan = "true";
                txtTamTinh.setText(utils.tinhTien() + "");
                txtTongCong.setText(utils.tinhTien() + "");
                etTienMat.setText(utils.tinhTien() + "");
            }
        }catch (Exception e){

        }

        etTienMat.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    long x = Long.parseLong(txtTongCong.getText() + "");
                    long y = Long.parseLong(etTienMat.getText() + "");
                    long z = y - x;
                    txtTienTraLai.setText(z + "");
                    return false;
                }catch(Exception e)
                {
                    return false;
                }
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (trangThaiBan.equals("false")) {
                        Log.d("laivao", "ok");
                        //hoa don chua co data trong hoadon va chitiethoadon!!!
                        //thanh toan ngay lap tuc khi dat hang xong
                        try {
                            List<CTDatBan> lstCT = new ArrayList<>();
                            for (int i = 0; i < utils.getCart().size(); i++) {
                                if (utils.getCart().get(i).getSlDat() > 0) {
                                    double t = utils.getCart().get(i).getDonGia() * utils.getCart().get(i).getSlDat();
                                    CTDatBan ct = new CTDatBan(utils.getCart().get(i).getMaMon(), utils.getCart().get(i).getSlDat(), t);
                                    lstCT.add(ct);
                                }
                            }
                            String currentDateTime;
                            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            currentDateTime = sdf1.format(new Date());
                            ProductService productService = APIClient.getClient().create(ProductService.class);
                            datBan db = new datBan(maBan, "Roi", currentDateTime, currentDateTime, "2", Double.parseDouble((String) txtTongCong.getText()), "Cash");
                            mix m = new mix(db, lstCT);
                            Call call = productService.create(m);
                            call.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    Toast.makeText(thanhtoanActivity.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                                    utils.getCart().clear();
                                    utils.tienGoc = 0;
                                    maBan = "x";
                                    trangThaiBan = "y";
                                    Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toast.makeText(thanhtoanActivity.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                                    utils.getCart().clear();
                                    utils.tienGoc = 0;
                                    maBan = "x";
                                    trangThaiBan = "y";
                                    Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        } catch (Exception e) {
                            utils.getCart().clear();
                            utils.tienGoc = 0;
                            maBan = "x";
                            trangThaiBan = "y";
                            Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                    //bàn trạng thái true
                    //giỏ hàng >0
                    //bàn đã được or thêm và thanh toán luôn
                    else if (trangThaiBan.equals("true") && utils.getCart().size() > 0) {
                        final List<CTDatBan> lstCT = new ArrayList<>();
                        for (int i = 0; i < utils.getCart().size(); i++) {
                            if (utils.getCart().get(i).getSlDat() > 0) {
                                double t = utils.getCart().get(i).getDonGia() * utils.getCart().get(i).getSlDat();
                                CTDatBan ct = new CTDatBan(utils.getCart().get(i).getMaMon(), utils.getCart().get(i).getSlDat(), t);
                                lstCT.add(ct);
                            }
                        }
                        final ProductService productService = APIClient.getClient().create(ProductService.class);
                        Call<List<CTDatBan>> call = productService.getCTTheoMa(db.getMaHD());
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                ArrayList<CTDatBan> arrayList = (ArrayList<CTDatBan>) response.body();
                                for (int i = 0; i < arrayList.size(); i++) {
                                    for (int j = 0; j < lstCT.size(); j++) {
                                        if (arrayList.get(i).getMaMon().equals(lstCT.get(j).getMaMon())) {
                                            int slCu = arrayList.get(i).getSl();
                                            int slMoi = lstCT.get(j).getSl();
                                            double tong = arrayList.get(i).getTong() + lstCT.get(j).getTong();
                                            //update lai so luong nhung chi tiet trung voi gio hang
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
                                String currentDateTime;
                                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                currentDateTime = sdf1.format(new Date());
                                //update lai tong cong va trang thai
                                datBan d = new datBan(db.getMaHD(), db.getSoBan(), "Roi", db.getTgvao(), currentDateTime, "2", utils.tinhTien(), "Cash");
                                Call ca = productService.update(db.getMaHD(), d);
                                ca.enqueue(new Callback() {
                                    @Override
                                    public void onResponse(Call call, Response response) {

                                    }

                                    @Override
                                    public void onFailure(Call call, Throwable t) {

                                    }
                                });
                                //tao cac chi tiet moi ko trung chi tiet cu
                                for (int i = 0; i < lstCT.size(); i++) {
                                    CTDatBan ct = lstCT.get(i);
                                    ct.setMaHD(db.getMaHD());
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
                                Toast.makeText(thanhtoanActivity.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                                utils.getCart().clear();
                                utils.tienGoc = 0;
                                maBan = "x";
                                utils.co = 0;
                                trangThaiBan = "y";
                                Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                                intent.putExtra("key_Main_Main4", "kmm4");
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                    } else {
                        //hoa don da co trong bang hoadon va chitiethoadon
                        //khach ngoi lai tai quan
                        final ProductService productService = APIClient.getClient().create(ProductService.class);
                        String currentDateTime;
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        currentDateTime = sdf1.format(new Date());
                        Log.d("oppp", "vao" + db.getMaHD());
                        datBan d = new datBan(db.getMaHD(), db.getSoBan(), "Roi", db.getTgvao(), currentDateTime, "2", db.getTong(), "Cash");
                        Call call = productService.update(db.getMaHD(), d);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Toast.makeText(thanhtoanActivity.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                                utils.co = 0;
                                utils.tienGoc = 0;
                                maBan = "x";
                                trangThaiBan = "y";
                                Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {
                                Toast.makeText(thanhtoanActivity.this, "Thanh Toán thành công", Toast.LENGTH_SHORT).show();
                                utils.co = 0;
                                utils.tienGoc = 0;
                                maBan = "x";
                                trangThaiBan = "y";
                                Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                } catch (Exception e) {
                    utils.co = 0;
                    utils.tienGoc = 0;
                    maBan = "x";
                    trangThaiBan = "y";
                    Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
                    startActivity(intent);
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
        Intent intent = new Intent(thanhtoanActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        utils.co = 0;
        utils.getCart().clear();
        utils.tienGoc = 0;
        maBan = "x";
        trangThaiBan = "y";
        finish();
        return super.onKeyDown(keyCode, event);
    }
}
