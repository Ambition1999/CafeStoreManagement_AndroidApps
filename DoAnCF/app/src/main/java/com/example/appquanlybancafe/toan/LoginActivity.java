package com.example.appquanlybancafe.toan;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin;
    ImageButton btnShowPassword;
    TextView txtUserName, txtPassword;
    SharedPreferences sharedPreferences, sharedPreferences2;
    ArrayList<TaiKhoan> arrayListTaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        sharedPreferences2 = getSharedPreferences("dataInforUser", MODE_PRIVATE);

        edtUsername = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText1);

        btnLogin = findViewById(R.id.btnLogin);

        btnShowPassword = findViewById(R.id.imgbtnShow);

        getDataTBlTaiKhoan();

        autoLogin();

        btnShowPassword.setOnClickListener(new View.OnClickListener() {

            private int passwordNotVisible = 1;
            @Override
            public void onClick(View v) {
                if (passwordNotVisible == 1) {
                    edtPassword.setTransformationMethod(null);
                    passwordNotVisible = 0;
                } else {
                    edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                    passwordNotVisible = 1;
                }
                edtPassword.setSelection(edtPassword.length());
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUsername.getText().toString().isEmpty()){
                    edtUsername.setError("Tài khoản không được để trống");
                }
                else if (edtPassword.getText().toString().isEmpty()) {
                    edtPassword.setError("Mật khẩu không được để trống");

                } else if (edtPassword.getText().toString().length() < 6) {
                    edtPassword.setError("Minimum 6 number");
                } else {
                   checkAccount(edtUsername.getText().toString(), edtPassword.getText().toString());
                }
            }
        });
    }

    public void clearShared(){
        SharedPreferences sharedPreferencesFragment = getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesFragment.edit();
        editor.clear();
        editor.commit();
    }

    public void getDataTBlTaiKhoan(){
        TaiKhoanServices taiKhoanServices = APIClient.getClient().create(TaiKhoanServices.class);
        taiKhoanServices.findAll().enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                arrayListTaiKhoan = (ArrayList<TaiKhoan>) response.body();
                if(arrayListTaiKhoan.size() == 0){
                    Toast.makeText(getApplicationContext(),"Arr rỗng",Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.wtf("dsHD", arrayListTaiKhoan.size()+"");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Lỗi kết nối", Toast.LENGTH_LONG).show();
                return;
            }
        });
    }


    protected  void loadMainLayoutNV(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivityForResult(intent, 100);
        finish();
    }

    protected  void loadMainLayoutAdmin(){
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        startActivityForResult(intent, 100);
        finish();
    }

    protected void autoLogin(){
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        String quyen = sharedPreferences.getString("quyen","");
        if(!username.equals("") && !password.equals(""))
            if(quyen.equals("admin"))
                loadMainLayoutAdmin();
            else
                loadMainLayoutNV();
        else
            return;
    }

    protected void saveAccountIntoAppData(String username, String password, String quyen){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.putString("quyen",quyen);
        editor.commit();
    }

    protected void checkAccount(String userName, String passWord){
        if(arrayListTaiKhoan != null) {
            for (TaiKhoan items : arrayListTaiKhoan) {
                if (items.getTenDn().equals(userName) && items.getMatKhau().equals(passWord)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    saveAccountIntoAppData(items.getTenDn(), items.getMatKhau(), items.getQuyen());
                    Log.wtf("quyen",items.getQuyen());
                    if (items.getQuyen().equals("admin")) {
                        loadMainLayoutAdmin();
                        return;
                    } else {
                        loadMainLayoutNV();
                        return;
                    }
                }
            }
            Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Lỗi kết nối", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 101) {
            edtUsername.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));

        }
        if (requestCode == 102 && resultCode == 101) {
            edtUsername.setText(data.getStringExtra("username"));
            edtPassword.setText(data.getStringExtra("password"));
        }
    }

}
