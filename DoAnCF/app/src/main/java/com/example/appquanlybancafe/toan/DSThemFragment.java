package com.example.appquanlybancafe.toan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appquanlybancafe.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class DSThemFragment extends Fragment {

    ArrayList<DoUong> arrayList;
    ListView listView;
    DoUongAdapter doUongAdapter;
    ImageButton imageButton;
    public DSThemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dsthem, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView_DSThem);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        ThucDonServices thucDonServices = APIClient.getClient().create(ThucDonServices.class);
        Call callDSThem = thucDonServices.findDoUongPhoBien();
        callDSThem.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("rs", "kết nối thành công");
                arrayList =  (ArrayList<DoUong>) response.body();
                listView.setAdapter(new DoUongAdapter(getContext(),arrayList));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("error", "kết nối thất bại");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"click item " + position,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(),ThemMonMoiActivity.class);
                intent.putExtra("ChiTietMon", (Parcelable) arrayList.get(position));
                startActivity(intent);
            }
        });


    }


}
