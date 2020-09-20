package com.example.appquanlybancafe.toan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TableDiagramFragment extends Fragment {

    GridView gridView;
    TableAdapterGrid tableAdapterGrid;
    ArrayList<Ban> arrBan;

    public TableDiagramFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table_diagram, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridView = view.findViewById(R.id.gridViewTable);


        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call call = productService.findAll();

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.d("rs", "kết nối thành công");
                arrBan= (ArrayList<Ban>) response.body();
                gridView.setAdapter(new TableAdapterGrid(getContext(),arrBan));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                Log.d("errorBan", "kết nối thất bại");
            }
        });
    }

//    public ArrayList<Table> getMockDataTable(){
//        ArrayList<Table> tmp = new ArrayList<>();
//        tmp.add(new Table("1","Tên bàn 1", "cafetable.png"));
//        tmp.add(new Table("2","Tên bàn 2", "cafetable.png"));
//        tmp.add(new Table("3","Tên bàn 3", "cafetable.png"));
//        tmp.add(new Table("4","Tên bàn 4", "cafetable.png"));
//        return tmp;
//    }
}
