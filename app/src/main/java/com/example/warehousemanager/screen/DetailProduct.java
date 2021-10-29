package com.example.warehousemanager.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.object.ProductCart;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailProduct extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imgHinh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detailproduct);
        setControl();
        setEvent();

        ImageView imageDetail = findViewById(R.id.imageDetail);
        TextView txtNameProduct = findViewById(R.id.txtNameProduct);
        TextView txtPrice = findViewById(R.id.txtPrice);


        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        String name_product = intent.getStringExtra("name_product");
        String price_product = intent.getStringExtra("price_product");
        String image_product = intent.getStringExtra("image_product");


        Picasso.get().load(image_product).into(imageDetail);
        txtNameProduct.setText(name_product);
        txtPrice.setText(price_product);


        // thêm giỏ hàng:

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference mDatabaseCart = FirebaseDatabase.getInstance().getReference("carts");
                //  mDatabase.child(usernameApp).child(product.getId()).setValue(productInCart);

                mDatabaseCart.child(MainActivity.UsernameApp).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ProductCart productCart = snapshot.child(id_product).getValue(ProductCart.class);
                        if (productCart != null) {
                            productCart.setQuality(productCart.getQuality() + 1);
                            mDatabaseCart.child(MainActivity.UsernameApp).child(productCart.getId()).setValue(productCart);
                        } else {
                            ProductCart productCart1 = new ProductCart(name_product, Integer.parseInt(price_product), 1, id_product, image_product);
                            mDatabaseCart.child(MainActivity.UsernameApp).child(id_product).setValue(productCart1);
                        }
                        Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }


    private void setEvent() {
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
        toolbar = findViewById(R.id.tb);
        imgHinh = findViewById(R.id.imgHinh);
    }
}
