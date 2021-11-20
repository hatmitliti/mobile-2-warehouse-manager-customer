package com.example.warehousemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.warehousemanager.R;
import com.example.warehousemanager.object.Cart;
import com.example.warehousemanager.object.Product;
import com.example.warehousemanager.object.ProductCart;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomAdapterOrder extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<ProductCart> data;

    public CustomAdapterOrder(@NonNull Context context, int resource, ArrayList<ProductCart> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //
        NumberFormat currentLocale = NumberFormat.getInstance();
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        //
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.giaTien = convertView.findViewById(R.id.tvPrice);
            viewHolder.ten = convertView.findViewById(R.id.tvName);
            viewHolder.imgHinhAnh = convertView.findViewById(R.id.imgHinh);
            viewHolder.txtSoLuong = convertView.findViewById(R.id.txtSoLuong);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProductCart pc = data.get(position);
        viewHolder.giaTien.setText(en.format(pc.getPrice()) + " VNĐ");
        viewHolder.ten.setText(pc.getName() + "");
        viewHolder.txtSoLuong.setText("x " + pc.getQuality() + "");
        Picasso.get().load(pc.getImage()).into(viewHolder.imgHinhAnh);
        return convertView;
    }

    private static class ViewHolder {
        TextView giaTien;
        TextView ten;
        TextView txtSoLuong;
        ImageView imgHinhAnh;
    }
}
