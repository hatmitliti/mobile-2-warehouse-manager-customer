package com.example.warehousemanager.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.warehousemanager.R;
import com.example.warehousemanager.adapter.CustomAdapterProduct;
import com.example.warehousemanager.object.Product;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends Fragment {
    GridView gv;
    CustomAdapterProduct adapter;
    ArrayList<Product> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_home, container, false);
        gv = view.findViewById(R.id.gvProduct);
        adapter = new CustomAdapterProduct(getActivity(), R.layout.item_home, list);
        gv.setAdapter(adapter);

        //  lay du lieu
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("products");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Product product = snapshot.getValue(Product.class);
                list.add(product);
                adapter.notifyDataSetChanged();
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


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), DetailProduct.class);
                intent.putExtra("id_product", list.get(i).getId());
                intent.putExtra("name_product", list.get(i).getTenSanPham());
                intent.putExtra("price_product", list.get(i).getGiaTien() + "");
                intent.putExtra("image_product", list.get(i).getHinhAnh());

                startActivity(intent);
            }
        });
        return view;
    }
}