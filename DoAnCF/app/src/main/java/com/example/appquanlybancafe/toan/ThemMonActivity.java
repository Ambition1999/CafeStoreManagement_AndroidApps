package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.appquanlybancafe.R;
import com.google.android.material.tabs.TabLayout;

public class ThemMonActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);

        getSupportActionBar().setTitle("Danh mục món");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButton = (ImageButton) findViewById(R.id.btnThemMon);
        tabLayout = (TabLayout) findViewById(R.id.ThemMon_tabLayout);

        loadFragment(new DSThemFragment());

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                    {
                        Fragment fragment = new DSThemFragment();
                        loadFragment(fragment);
                        break;
                    }
                    case 1:{
                        Fragment fragment = new CafeFragment();
                        loadFragment(fragment);
                        break;
                    }
                    case 2:{
                        Fragment fragment = new NuocNgotFragment();
                        loadFragment(fragment);
                        break;
                    }

                    case 3:{
                        Fragment fragment = new NuocEpFragment();
                        loadFragment(fragment);
                        break;
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        //Button thêm món mới
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemMonActivity.this,ThemMonMoiActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment_ThemMon, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
