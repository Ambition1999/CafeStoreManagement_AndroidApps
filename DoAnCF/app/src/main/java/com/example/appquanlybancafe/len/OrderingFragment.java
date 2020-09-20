package com.example.appquanlybancafe.len;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrderingFragment extends Fragment {
    Utils utils;
    ListView listView;
    OrderingAdapter chooseItemAdapter;
    ArrayList<ThucDon> arrayList2;
    public OrderingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ordering, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.list_choose_item);
        utils=new Utils();
        arrayList2=utils.getCart();
        chooseItemAdapter=new OrderingAdapter(getContext(),arrayList2);
        listView.setAdapter(chooseItemAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
            }
        });
    }
}
