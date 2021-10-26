package com.example.use.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.use.R;
import com.example.use.adapter.CustomAdapterCart;
import com.example.use.object.Cart;

import java.util.ArrayList;

public class ScreenCart extends Fragment {
    ListView lv;
    Button btnDatHang;
    CustomAdapterCart adapter;
    ArrayList<Cart> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_cart,container,false);
        lv = view.findViewById(R.id.lv);
        btnDatHang = view.findViewById(R.id.btnDatHang);
        list.add(new Cart(R.drawable.tiger,"Tiger",230000,"x3"));
        list.add(new Cart(R.drawable.heniken,"Heineken",230000,"x3"));
        adapter = new CustomAdapterCart(getActivity(), R.layout.item_cart, list);
        lv.setAdapter(adapter);
        btnDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),ScreenOrder.class));
            }
        });

        return view;
    }

    public void setAdapter(){

    }
}
