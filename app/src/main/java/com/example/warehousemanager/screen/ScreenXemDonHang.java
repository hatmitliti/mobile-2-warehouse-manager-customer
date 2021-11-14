package com.example.warehousemanager.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.adapter.CustomAdapterXemDonHang;
import com.example.warehousemanager.object.Bill;
import com.example.warehousemanager.object.XemDonHang;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ScreenXemDonHang extends AppCompatActivity {
    ListView lv;
    // Toolbar toolbar;
    ArrayList<Bill> list = new ArrayList<>();
    CustomAdapterXemDonHang adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xem_don_hang);
        setControl();
        setEvent();
    }

    private void setEvent() {
        Toolbar toolbar = findViewById(R.id.tbChangePassword);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        adapter = new CustomAdapterXemDonHang(this, R.layout.item_xem_don_hang, list);
        lv.setAdapter(adapter);

        DatabaseReference mDatabaseBill = FirebaseDatabase.getInstance().getReference("bills");

        mDatabaseBill.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Bill bill = snapshot.getValue(Bill.class);
                if (bill.getId_user().equals(MainActivity.UsernameApp)){
                    list.add(bill);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







    }

    private void setControl() {
        lv = findViewById(R.id.lv);
        //  toolbar = findViewById(R.id.tb);
    }
}
