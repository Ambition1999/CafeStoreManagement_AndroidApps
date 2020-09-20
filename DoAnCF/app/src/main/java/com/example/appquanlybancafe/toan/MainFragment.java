package com.example.appquanlybancafe.toan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    Button btnThemMon, btnThemBan, btnThoat;
    ArrayList<DoUong> arrayList;
    SharedPreferences sharedPreferencesFragment;
    ImageView imageView;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedPreferencesFragment = getActivity().getSharedPreferences("loginData", MODE_PRIVATE);
        AnhXa();
//        btnThemMon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),ThemMonActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnThemBan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),ThemBanActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        btnThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clearAccount();
//                Intent intent = new Intent(getContext(),LoginActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    public void AnhXa(){
//        btnThemMon = getView().findViewById(R.id.btn_themMonAn);
//        btnThemBan = getView().findViewById(R.id.main_btn_themBan);
//        btnThoat = getView().findViewById(R.id.btn_DangXuat);
    }

    public void clearAccount(){
        SharedPreferences.Editor editor = sharedPreferencesFragment.edit();
        editor.clear();
        editor.commit();
    }


}
