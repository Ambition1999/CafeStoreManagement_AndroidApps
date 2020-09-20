package com.example.appquanlybancafe.len;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appquanlybancafe.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class daOrderFragment extends Fragment {
    ArrayList<CTDatBan> arrayList=new ArrayList<>();
    ListView listView;
    Utils utils;
    daOrderAdapter daOrderAdapter;
    datBan db;
    public daOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_da_order, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        utils=new Utils();

        try {
            Intent i=getActivity().getIntent();
            db=i.getParcelableExtra("truyenHD");
          //  Log.d("leno",utils.getCart().size()+"");
            if(utils.getCart().size()==0){
//                if(utils.giaMoi!=0) {
//                    Main4Activity.txtThanhToan.setText(utils.giaMoi + "");
//                    utils.tienGoc =utils.giaMoi;
//                }
//                else {
                    Log.d("lklk", "" + db.getTong());
                    Main4Activity.txtThanhToan.setText(db.getTong() + "");
                    utils.tienGoc = db.getTong();
             //   }
            }
            else {
                Main4Activity.txtThanhToan.setText(utils.tinhTien() + "");
                Log.d("opop",utils.tinhTien()+"::::");
            }
        }
        catch (Exception e)
        {

        }
        listView=view.findViewById(R.id.da_order_list);
        ProductService productService=APIClient.getClient().create(ProductService.class);
        Call<List<CTDatBan>> call=productService.getCT();
        call.enqueue(new Callback<List<CTDatBan>>() {
            @Override
            public void onResponse(Call<List<CTDatBan>> call, Response<List<CTDatBan>> response) {
                arrayList= (ArrayList<CTDatBan>) response.body();
                final ArrayList<CTDatBan> arrayList2=new ArrayList<>();
                try {
                    for (int j = 0; j < arrayList.size(); j++) {
                        if (arrayList.get(j).getMaHD() == db.getMaHD()) {
                            arrayList2.add(arrayList.get(j));
                        }
                    }
                    daOrderAdapter=new daOrderAdapter(getContext(),arrayList2);
                    listView.setAdapter(daOrderAdapter);
                }
                catch (Exception e){
                    daOrderAdapter=new daOrderAdapter(getContext(),arrayList2);
                    listView.setAdapter(daOrderAdapter);
                }
//                if(arrayList2.size()==0){
//                    final ProductService productService=APIClient.getClient().create(ProductService.class);
//                    Call call4=productService.xoaHD(db.getMaHD());
//                    call4.enqueue(new Callback() {
//                        @Override
//                        public void onResponse(Call call, Response response) {
//                            Intent intent=new Intent(getActivity(), MainActivity.class);
//                            startActivity(intent);
//                            getActivity().finish();
//                        }
//
//                        @Override
//                        public void onFailure(Call call, Throwable t) {
//
//                        }
//                    });
//                }
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        final ProductService productService=APIClient.getClient().create(ProductService.class);
                        Call call2=productService.xoaCT(db.getMaHD(),arrayList2.get(position));
                        call2.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, Response response) {
                                Toast.makeText(getActivity(),"Xoá Thành Công",Toast.LENGTH_SHORT).show();
                                utils.co=1;
                                double gia=arrayList2.get(position).getTong();
                                Log.d("lasaooo",gia+"/1");
                                double giaCu=Double.parseDouble(Main4Activity.txtThanhToan.getText().toString());
                                Log.d("lasaooo",giaCu+"/2");
                                double giaM=giaCu-gia;
                                Log.d("lasaooo",giaM+"/3");
                                utils.giaMoi=giaM;
                                Log.d("lasaooo",utils.giaMoi+"/4");
                                Main4Activity.txtThanhToan.setText(utils.giaMoi+"");
                                loadFragment(new CartFragment());
                            }

                            @Override
                            public void onFailure(Call call, Throwable t) {

                            }
                        });
                        return false;
                    }
                });
            }
            @Override
            public void onFailure(Call<List<CTDatBan>> call, Throwable t) {

            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}