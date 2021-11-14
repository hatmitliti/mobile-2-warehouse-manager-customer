package com.example.warehousemanager.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.adapter.CustomAdapterOrder;
import com.example.warehousemanager.object.Bill;
import com.example.warehousemanager.object.Product;
import com.example.warehousemanager.object.ProductCart;
import com.example.warehousemanager.object.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.UUID;

public class ScreenOrder extends AppCompatActivity {
    ListView lv;
    // Toolbar toolbar;
    ArrayList<ProductCart> list = new ArrayList<>();
    CustomAdapterOrder adapter;
    Button btnDatHangXacNhan;
    TextView txtTongTien;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_don_dat_hang);
        setControl();
        setEvent();
        // lấy dữ liệu truyền qua
        Intent intent = getIntent();
        list = (ArrayList<ProductCart>) intent.getSerializableExtra("list");

        int tong = 0;
        for (int j = 0; j < list.size(); j++) {
            tong += (list.get(j).getPrice() * list.get(j).getQuality());
        }
        txtTongTien.setText(NumberFormat.getInstance().format(tong) + " VNĐ");

        adapter = new CustomAdapterOrder(this, R.layout.item_don_dat_hang, list);
        lv.setAdapter(adapter);

        EditText sonhatenduong = findViewById(R.id.sonhatenduong);
        EditText txtphuong = findViewById(R.id.txtphuong);
        EditText txtquan = findViewById(R.id.txtquan);
        EditText txtsdt = findViewById(R.id.txtsdt);
        // Bấm nút đặt hàng:
        btnDatHangXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonhatenduong.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                } else if (txtphuong.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                } else if (txtquan.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                } else if (txtsdt.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    // đặt hàng:
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("bills");

                    String ad1 = sonhatenduong.getText().toString();
                    String ad2 = txtphuong.getText().toString();
                    String ad3 = txtquan.getText().toString();
                    String ad4 = txtsdt.getText().toString();

                    String address = ad1 + " - " + ad2 + " - " + ad3 + " - " + ad4;
                    String id = UUID.randomUUID().toString();
                    String id_user = MainActivity.UsernameApp;
                    String name_user = MainActivity.NameApp;
                    String sdt = txtsdt.getText().toString();

                    int tong = 0;
                    for (int j = 0; j < list.size(); j++) {
                        tong += (list.get(j).getPrice() * list.get(j).getQuality());
                    }


                    Bill bill = new Bill(address, id, id_user, name_user, 0, tong, sdt);
                    mDatabase.child(bill.getId()).setValue(bill);
                    Toast.makeText(getApplicationContext(), "Đơn đã đặt", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                    DatabaseReference mDatabaseCart = FirebaseDatabase.getInstance().getReference("carts");
                    // xóa sản phẩm trong giỏ hiện tại
                    mDatabaseCart.child(MainActivity.UsernameApp).removeValue();
                    // tạo ra bill detail
                    DatabaseReference mDatabaseBilldetai = FirebaseDatabase.getInstance().getReference("bill_detail");
                    for (int j = 0; j < list.size(); j++) {
                        mDatabaseBilldetai.child(bill.getId()).child(list.get(j).getId()).setValue(list.get(j));
                    }


                }
            }
        });


        // lấy thông tin user hiển thị sẵn lên:
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(MainActivity.UsernameApp)){
                    txtsdt.setText(user.getPhone());
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
    }

    private void setControl() {
        lv = findViewById(R.id.lv);
        btnDatHangXacNhan = findViewById(R.id.btnDatHangXacNhan);
        txtTongTien = findViewById(R.id.txtTongTien);
    }
}
