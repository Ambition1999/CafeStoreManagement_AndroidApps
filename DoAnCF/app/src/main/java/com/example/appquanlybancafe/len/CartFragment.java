package com.example.appquanlybancafe.len;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.appquanlybancafe.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Context mContext;
    Utils utils;
    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        utils=new Utils();
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        if(utils.co==1){
            adapter.addFragment(new OrderingFragment(), "Dang Order");
            adapter.addFragment(new daOrderFragment(), "Da Order");
            Log.d("vaol","f");
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(1);
        }
        else{
            adapter.addFragment(new OrderingFragment(), "Dang Order");
            adapter.addFragment(new daOrderFragment(), "Da Order");
            viewPager.setAdapter(adapter);
        }
    }
}
