package com.example.appquanlybancafe.len;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriesFragment extends Fragment implements ListViewAdapter.OnItemClickListener{
    private RecyclerView listView;
    private RecyclerView gridView;
    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;
    static List<LoaiMon> corporations;
    private  ArrayList<ThucDon> gridArrayRoot;
    Utils utils;
    ArrayList<ThucDon> gridArrayChild;
    String maBan="x";
    int ml;
    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        Intent i=getActivity().getIntent();
//        if(i.getStringExtra("truyenMaBan")!=null)
//        {
//            maBan=i.getStringExtra("truyenMaBan");
//        }

        utils=new Utils();
        listView=(RecyclerView) view.findViewById(R.id.list);
        gridView=(RecyclerView) view.findViewById(R.id.grid);

        setDummyData();

        listView.setHasFixedSize(true);
        gridView.setHasFixedSize(true);
//        set layout manager and adapter for "ListView"
        LinearLayoutManager horizontalManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(horizontalManager);

//        set layout manager and adapter for "GridView"
        final GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        gridView.setLayoutManager(layoutManager);
//        gridViewAdapter=new GridViewAdapter(getActivity(),operatingSystems);
//        gridView.setAdapter(gridViewAdapter);
    }
    private void setDummyData() {
        corporations=new ArrayList<>();
//        Intent intent=new Intent(getActivity(), Service2.class);
//        getActivity().startService(intent);

//        try {
//            ProductService productService=APIClient.getClient().create(ProductService.class);
//            Call<List<LoaiMon>> call=productService.findLoai();
//            Response<List<LoaiMon>> response=call.execute();
//            corporations=response.body();
//            Log.d("ck",corporations.size()+"");
//        }
//        catch (IOException e){
//            Log.d("ck",corporations.size()+"fail");
//            e.printStackTrace();
//        }

        ProductService productService=APIClient.getClient().create(ProductService.class);
        Call<List<LoaiMon>> call=productService.findLoai();
        call.enqueue(new Callback<List<LoaiMon>>() {
            @Override
            public void onResponse(Call<List<LoaiMon>> call, Response<List<LoaiMon>> response) {
                corporations=response.body();

                listViewAdapter = new ListViewAdapter(corporations);
                listView.setAdapter(listViewAdapter);
                listViewAdapter.setOnItemClickListener(CategoriesFragment.this);
            }

            @Override
            public void onFailure(Call<List<LoaiMon>> call, Throwable t) {
                Log.d("ck",corporations.size()+"fail");
            }
        });
        gridArrayRoot=new ArrayList<>();
        Call<List<ThucDon>> call2=productService.findTD();
        call2.enqueue(new Callback<List<ThucDon>>() {
            @Override
            public void onResponse(Call<List<ThucDon>> call, Response<List<ThucDon>> response) {
                gridArrayRoot= (ArrayList<ThucDon>) response.body();
            }
            @Override
            public void onFailure(Call<List<ThucDon>> call, Throwable t) {

            }
        });
    }
    public ArrayList<ThucDon> getDishFromCategories(int pos)
    {
        ArrayList<ThucDon> tmp=new ArrayList<>();
        for(int i=0;i<gridArrayRoot.size();i++){
            if(gridArrayRoot.get(i).getMaLoai()==pos){
                tmp.add(gridArrayRoot.get(i));
                //Log.d("len",arr.get(i).getTenMon());
            }
        }
        return tmp;
    }
    public void setLaiSL(ArrayList<ThucDon> lst){
        for(int i=0;i<utils.getCart().size();i++){
            for(int j=0;j<lst.size();j++){
                if(utils.getCart().get(i).getMaMon().equals(lst.get(j).getMaMon())){
                    lst.get(j).setSlDat(utils.getCart().get(i).getSlDat());
                    break;
                }
            }
        }
    }
    @Override
    public void onItemClick(View itemView, int position) {
        ml = corporations.get(position).getMaLoai();
        gridArrayChild=getDishFromCategories(ml);
        setLaiSL(gridArrayChild);
        gridViewAdapter=new GridViewAdapter(getActivity(),gridArrayChild);
        gridView.setAdapter(gridViewAdapter);
        gridViewAdapter.setOnItemClickListener(new GridViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, final int position) {
                //get ID of this choosed item--------->
                final String maMon=gridArrayChild.get(position).getMaMon();
                //Toast.makeText(getContext(), name + " was clicked!", Toast.LENGTH_SHORT).show();

                //add this item into your cart----->
                //
                // Check it out your cart
                // if not contain this item then plus one into cart...
                //<-------
                int flag=0;
                for(int i=0;i<utils.cart.size();i++)
                {
                    if(utils.cart.get(i).getMaMon().equals(maMon)){
                        utils.cart.get(i).slDat++;
                        flag=1;
                    }
                }
                if(flag==0){
                    ThucDon i=new ThucDon(gridArrayChild.get(position).getMaMon(),gridArrayChild.get(position).getTenMon(),gridArrayChild.get(position).getDonGia());
                    utils.cart.add(i);
                }
                flag=0;

                //get quality order of item by ID----->
                int sl=0;
                for(int i=0;i<utils.cart.size();i++)
                {
                    if(utils.cart.get(i).getMaMon().equals(maMon)){
                        sl=utils.cart.get(i).getSlDat();
                        break;
                    }
                }
                //set quality of item on gridview (again)---->
                if(sl>0) {
                    ((TextView) gridView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.slDat)).setVisibility(View.VISIBLE);
                    ((TextView) gridView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.slDat)).setText("x" + sl);
                }
                Main4Activity.txtThanhToan.setText(utils.tinhTien()+"");
                Log.d("opop2",utils.tinhTien()+"");
            }
        });
    }

}
