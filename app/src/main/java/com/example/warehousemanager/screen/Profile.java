package com.example.warehousemanager.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.object.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Profile extends Fragment {
    Button btnXemDonHang;
    Button btnThongTinUser;
    TextView txtSDTUser;
    TextView txtHang;
    TextView txtTenUser;
    ImageView imgUser;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_profile, container, false);
        btnXemDonHang = view.findViewById(R.id.btnXemDonHang);
        btnXemDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ScreenXemDonHang.class));
            }
        });


        txtHang = view.findViewById(R.id.txtHang);
        txtSDTUser = view.findViewById(R.id.txtSDTUser);
        txtTenUser = view.findViewById(R.id.txtTenUser);
        imgUser = view.findViewById(R.id.IMGAnhUser);
        btnThongTinUser = view.findViewById(R.id.btnThongTinUser);


        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("user");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(MainActivity.UsernameApp)) {
                    txtHang.setText(user.getRank());
                    txtSDTUser.setText(user.getPhone());
                    txtTenUser.setText(user.getName());
                    Picasso.get().load(user.getImgUser()).into(imgUser);
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


        btnThongTinUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), user.class));
            }
        });


        return view;
    }
}
