package com.example.warehousemanager.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.warehousemanager.R;
import com.example.warehousemanager.object.Bill;
import com.example.warehousemanager.object.Cart;
import com.example.warehousemanager.object.XemDonHang;

import java.text.NumberFormat;
import java.util.ArrayList;

public class CustomAdapterXemDonHang extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Bill> data;

    public CustomAdapterXemDonHang(@NonNull Context context, int resource, ArrayList<Bill> data) {
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
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, null);

            viewHolder = new ViewHolder();
            viewHolder.txtIDBill = convertView.findViewById(R.id.txtIDBill);
            viewHolder.txtDiaChiBill = convertView.findViewById(R.id.txtDiaChiBill);
            viewHolder.TenNNBill = convertView.findViewById(R.id.TenNNBill);
            viewHolder.txtTongTienBill = convertView.findViewById(R.id.txtTongTienBill);
            viewHolder.lnItemBill = convertView.findViewById(R.id.lnItemBill);

            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Bill xemDonHang = data.get(position);
        viewHolder.txtIDBill.setText(xemDonHang.getId());
        viewHolder.txtDiaChiBill.setText(xemDonHang.getAddress());
        viewHolder.TenNNBill.setText(xemDonHang.getName());
        viewHolder.txtTongTienBill.setText( NumberFormat.getInstance().format(xemDonHang.getTotalMoney())   + " VND");

        if (xemDonHang.getStatus() == 0) {
            Drawable d = context.getResources().getDrawable(R.drawable.bg_circle_solid_blue);
            viewHolder.lnItemBill.setBackgroundDrawable(d);
        } else if (xemDonHang.getStatus() == 1) {
            Drawable d = context.getResources().getDrawable(R.drawable.bg_circle_solid_green);
            viewHolder.lnItemBill.setBackgroundDrawable(d);
        } else if (xemDonHang.getStatus() == 2) {
            Drawable d = context.getResources().getDrawable(R.drawable.bg_circle_solid_grey);
            viewHolder.lnItemBill.setBackgroundDrawable(d);
        } else if (xemDonHang.getStatus() == 3) {
            Drawable d = context.getResources().getDrawable(R.drawable.bg_circle_solid_yellow);
            viewHolder.lnItemBill.setBackgroundDrawable(d);
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView txtIDBill;
        TextView txtDiaChiBill;
        TextView TenNNBill;
        TextView txtTongTienBill;
        LinearLayout lnItemBill;
    }
}
