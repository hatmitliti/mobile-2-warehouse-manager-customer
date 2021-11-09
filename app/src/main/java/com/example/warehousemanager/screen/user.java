package com.example.warehousemanager.screen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warehousemanager.MainActivity;
import com.example.warehousemanager.R;
import com.example.warehousemanager.object.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;

import de.hdodenhof.circleimageview.CircleImageView;

public class user extends  AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        CircleImageView profile_image = findViewById(R.id.profile_image);
        EditText txtNameUserEdit = findViewById(R.id.txtNameUserEdit);
       // EditText txtBirthUser = findViewById(R.id.txtBirthUser);
        TextView txtTotalMoneyUser = findViewById(R.id.txtTotalMoneyUser);
        TextView txtdiachi = findViewById(R.id.txtdiachi);
        TextView txtphoneUser_ = findViewById(R.id.txtphoneUser_);
        Button btnLuu = findViewById(R.id.btnLuu);

        //lấy dữ liệu:
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("user");
        data.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getKey().equals(MainActivity.UsernameApp)) {
                    user = snapshot.getValue(User.class);
                    Toast.makeText(getApplicationContext(), user.getName(), Toast.LENGTH_SHORT).show();
                    try {
                        Picasso.get().load(user.getImgUser()).into(profile_image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    txtNameUserEdit.setText(user.getName());
                    txtTotalMoneyUser.setText(NumberFormat.getInstance().format(user.getTotalMoney()));
                    txtphoneUser_.setText(user.getPhone());
                    txtdiachi.setText(user.getAddress());
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

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtNameUserEdit.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtdiachi.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtphoneUser_.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }


                user.setName(txtNameUserEdit.getText().toString());
                user.setPhone(txtphoneUser_.getText().toString());
                user.setAddress(txtdiachi.getText().toString());

                FirebaseDatabase.getInstance().getReference("users")
                        .child(MainActivity.UsernameApp).setValue(user);

            }
        });
    }
}