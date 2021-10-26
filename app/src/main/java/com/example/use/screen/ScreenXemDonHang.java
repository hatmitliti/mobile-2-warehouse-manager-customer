package com.example.use.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.use.R;
import com.example.use.adapter.CustomAdapterOrder;
import com.example.use.adapter.CustomAdapterXemDonHang;
import com.example.use.object.Product;
import com.example.use.object.XemDonHang;

import java.util.ArrayList;

public class ScreenXemDonHang extends AppCompatActivity {
    ListView lv;
    Toolbar toolbar;
    ArrayList<XemDonHang> list = new ArrayList<>();
    CustomAdapterXemDonHang adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xem_don_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        list.add(new XemDonHang("1/1/1999","Tiger","x3",230000));
        list.add(new XemDonHang("1/1/1999","Tiger","x3",230000));
        list.add(new XemDonHang("1/1/1999","Tiger","x3",230000));
        adapter = new CustomAdapterXemDonHang(this, R.layout.item_xem_don_hang, list);
        lv.setAdapter(adapter);
    }

    private void setControl() {
        lv = findViewById(R.id.lv);
        toolbar = findViewById(R.id.tb);
    }
}
