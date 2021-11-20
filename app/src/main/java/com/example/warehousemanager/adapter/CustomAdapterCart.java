package com.example.warehousemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.object.Cart;
import com.example.warehousemanager.object.ProductCart;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CustomAdapterCart extends ArrayAdapter {

    Context context;
    int resource;
    ArrayList<ProductCart> list;

    public CustomAdapterCart(Context context, int resource, ArrayList<ProductCart> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.txtTenSP = convertView.findViewById(R.id.txtTenProductInCart);
            viewHolder.soLuong = convertView.findViewById(R.id.txtSoLuongProductInCart);
            viewHolder.hinhAnh = convertView.findViewById(R.id.imgHinhAnhProductInCart);
            viewHolder.giaTien = convertView.findViewById(R.id.txtGiaTienInCart);
            viewHolder.btnXoa = convertView.findViewById(R.id.btnXoaProductInCart);
            viewHolder.btnGiam = convertView.findViewById(R.id.btnGiamProductInCart);
            viewHolder.btnTang = convertView.findViewById(R.id.btnTangProductInCart);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProductCart productInCart = list.get(position);

        viewHolder.txtTenSP.setText(productInCart.getName());
        viewHolder.soLuong.setText("x" + productInCart.getQuality());
        Picasso.get().load(productInCart.getImage().toString()).into(viewHolder.hinhAnh);
        viewHolder.giaTien.setText(NumberFormat.getInstance().format(productInCart.getPrice()));

        // giảm số lượng:
        viewHolder.btnGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(productInCart.getQuality() == 0)) {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("carts");
                    mDatabase.child(MainActivity.UsernameApp).child(productInCart.
                            getId()).child("quality").setValue(productInCart.getQuality() - 1);
                }
            }
        });
        // tăng số lượng:
        viewHolder.btnTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("carts");
                mDatabase.child(MainActivity.UsernameApp).child(productInCart.
                        getId()).child("quality").setValue(productInCart.getQuality() + 1);
            }
        });

        // xóa:
        viewHolder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("carts");
                mDatabase.child(MainActivity.UsernameApp).child(productInCart.
                        getId()).removeValue();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    private static class ViewHolder {
        CheckBox chkbox;
        TextView txtTenSP;
        TextView soLuong;
        ImageView hinhAnh;
        TextView giaTien;
        Button btnTang;
        Button btnGiam;
        Button btnXoa;
    }
}
