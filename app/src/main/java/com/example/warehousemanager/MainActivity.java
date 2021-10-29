package com.example.warehousemanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.warehousemanager.adapter.ViewPaperAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ViewPager2 viewPager2;
    public static String UsernameApp;
    public static String NameApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private void setEvent() {
        ViewPaperAdapter adapter = new ViewPaperAdapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_cart).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                }
            }
        });
        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.action_cart:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.action_profile:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

    private void setControl() {
        navigationView = findViewById(R.id.bottom_nav);
        viewPager2 = findViewById(R.id.view_paper2);
    }

}