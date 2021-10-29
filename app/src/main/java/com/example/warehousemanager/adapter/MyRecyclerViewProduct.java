package com.example.warehousemanager.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.warehousemanager.R;
import com.example.warehousemanager.object.Cart;
import com.example.warehousemanager.object.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecyclerViewProduct extends RecyclerView.Adapter<MyRecyclerViewProduct.MyHolderProduct> {
    private Activity context;
    private int layoutID;
    private ArrayList<Product> list;

    public MyRecyclerViewProduct(Activity context, int layoutID, ArrayList<Product> list) {
        this.context = context;
        this.layoutID = layoutID;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =context.getLayoutInflater();
        CardView view = (CardView) inflater.inflate(R.layout.item_home,parent,false);
        return new MyHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderProduct holder, int position) {
        Product product =list.get(position);

        Picasso.get().load(product.getHinhAnh()).into( holder.imgHinh);
        holder.tvName.setText(product.getTenSanPham());
        holder.tvPrice.setText(product.getGiaTien()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return layoutID;
    }

    public class MyHolderProduct extends RecyclerView.ViewHolder {
        ImageView imgHinh;
        TextView tvName,tvPrice;
        public MyHolderProduct(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgHinh);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
