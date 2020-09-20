package com.example.appquanlybancafe.toan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.appquanlybancafe.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemBanFragment extends Fragment {

    ArrayList<Ban> arrBan;
    ImageButton imageButton_Them;
    GridView gridView;
    Button btn_Them;
    TabLayout tabLayout;
    ViewPager viewPager;
    String tenKhuVuc;

    public ThemBanFragment() {
        // Required empty public constructor
    }

    public ThemBanFragment(String tenKhuVuc) {
        this.tenKhuVuc = tenKhuVuc;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_them_ban, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridViewTableNew);
        setDataToGrid(tenKhuVuc);
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
}
