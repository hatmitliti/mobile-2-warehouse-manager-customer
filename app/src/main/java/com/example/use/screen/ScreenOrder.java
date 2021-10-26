package com.example.use.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.use.R;
import com.example.use.adapter.CustomAdapterOrder;
import com.example.use.adapter.CustomAdapterProduct;
import com.example.use.object.Product;

import java.util.ArrayList;

public class ScreenOrder extends AppCompatActivity {
    ListView lv;
    Toolbar toolbar;
    ArrayList<Product> list = new ArrayList<>();
    CustomAdapterOrder adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_don_dat_hang);
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
        list.add(new Product(R.drawable.tiger,"Tiger",230000));
        list.add(new Product(R.drawable.heniken,"Heineken",230000));
        adapter = new CustomAdapterOrder(this, R.layout.item_don_dat_hang, list);
        lv.setAdapter(adapter);
    }

    private void setControl() {
        lv = findViewById(R.id.lv);
        toolbar = findViewById(R.id.tb);
    }
}
