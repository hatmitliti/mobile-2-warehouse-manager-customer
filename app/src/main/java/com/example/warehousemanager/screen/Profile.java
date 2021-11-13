package com.example.warehousemanager.screen;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.object.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Profile extends Fragment {
    Button btnXemDonHang;
    Button btnThongTinUser;
    TextView txtSDTUser;
    TextView txtHang;
    TextView txtTenUser;
    ImageView imgUser;
    ArrayList<String> mKey;


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
        TextView txtTotalMoneyUser = view.findViewById(R.id.txtTotalMoneyUser);


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
                    txtTotalMoneyUser.setText(NumberFormat.getInstance().format(user.getTotalMoney()));
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user.getId().equals(MainActivity.UsernameApp)) {
                    txtHang.setText(user.getRank());
                    txtSDTUser.setText(user.getPhone());
                    txtTenUser.setText(user.getName());
                    Picasso.get().load(user.getImgUser()).into(imgUser);
                    txtTotalMoneyUser.setText(NumberFormat.getInstance().format(user.getTotalMoney()));
                }
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
