package com.example.appquanlybancafe.toan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.appquanlybancafe.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Trang bán hàng");

        sharedPreferences = getSharedPreferences("loginData", MODE_PRIVATE);
        tabLayout = findViewById(R.id.tabMain);
        tabLayout.getTabAt(0).select();

        loadFragment(new MainFragment());
        Intent intent=getIntent();
        //getSupportActionBar().hide();
	getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        if(intent.getParcelableExtra("chonBan")!=null){
            Log.d("12345","123");
            //getSupportActionBar().show();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            Fragment fragment = new OrderFragmentTemp();
            loadFragment(fragment);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition())
                {
                    case 0:
                    {
                        Fragment fragment = new MainFragment();
                        loadFragment(fragment);
                        break;
                    }
                    case 1:{
                        Fragment fragment = new OrderFragment();
                        loadFragment(fragment);
                        break;
                    }
                    case 2:{
                        Fragment fragment = new OrderFragmentTemp();
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menurestdata, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_logout){
            ClearAccount();
            Intent intent = new Intent(getApplication(),LoginActivity.class);
            startActivity(intent);
        }
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void ClearAccount(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }







}
