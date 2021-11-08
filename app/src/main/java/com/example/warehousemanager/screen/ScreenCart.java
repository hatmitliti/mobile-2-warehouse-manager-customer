package com.example.warehousemanager.screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.adapter.CustomAdapterCart;
import com.example.warehousemanager.object.Cart;
import com.example.warehousemanager.object.ProductCart;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ScreenCart extends Fragment {

    ListView lv;
    Button btnDatHang;
    TextView txtTongTienCart;
    CustomAdapterCart adapter;
    ArrayList<ProductCart> list = new ArrayList<>();
    ArrayList<String> mkey = new ArrayList<>();


    @Override
    public void onResume() {
        super.onResume();
        setTotal(list);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_cart, container, false);
        lv = view.findViewById(R.id.lv);
        btnDatHang = view.findViewById(R.id.btnDatHang);
        txtTongTienCart = view.findViewById(R.id.txtTongTienCart);
        adapter = new CustomAdapterCart(getActivity(), R.layout.item_cart, list);
        lv.setAdapter(adapter);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("carts");
        mDatabase.child(MainActivity.UsernameApp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                ProductCart productCart = snapshot.getValue(ProductCart.class);
                list.add(productCart);
                adapter.notifyDataSetChanged();
                mkey.add(snapshot.getKey());
                txtTongTienCart.setText("");
                setTotal(list);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                int index = mkey.indexOf(snapshot.getKey());
                ProductCart productCart = snapshot.getValue(ProductCart.class);
                list.set(index, productCart);
                adapter.notifyDataSetChanged();
                txtTongTienCart.setText("");
                setTotal(list);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                list.remove(snapshot.getValue(ProductCart.class));
                adapter.notifyDataSetChanged();
                txtTongTienCart.setText("");
                setTotal(list);
                mkey.remove(snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // sự kiện cho button đặt hàng
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(txtTongTienCart.getTag().toString()) == 0) {
                    Toast.makeText(getContext(), "Chưa có sản phẩm", Toast.LENGTH_SHORT).show();
                } else {
                    int tongTien = Integer.parseInt(txtTongTienCart.getTag().toString());
                    Intent intent = new Intent(getActivity(), ScreenOrder.class);
                    intent.putExtra("list", list);
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    // tính tổng tiền trong giỏ
    public void setTotal(ArrayList<ProductCart> list) {
        int tongTien = 0;
        for (int j = 0; j < list.size(); j++) {
            tongTien += (list.get(j).getPrice() * list.get(j).getQuality());
        }
        txtTongTienCart.setText(NumberFormat.getInstance().format(tongTien) + "VNĐ");
        txtTongTienCart.setTag(tongTien);
    }
}
