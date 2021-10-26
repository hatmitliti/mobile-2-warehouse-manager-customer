package com.example.use.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.use.R;
import com.example.use.adapter.CustomAdapterProduct;
import com.example.use.adapter.MyRecyclerViewProduct;
import com.example.use.object.Product;

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
        list.add(new Product(R.drawable.tiger,"Tiger",230000));
        list.add(new Product(R.drawable.heniken,"Heineken",230000));
        adapter = new CustomAdapterProduct(getActivity(), R.layout.item_home, list);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(getActivity(),DetailProduct.class));
            }
        });
        return view;
    }
}