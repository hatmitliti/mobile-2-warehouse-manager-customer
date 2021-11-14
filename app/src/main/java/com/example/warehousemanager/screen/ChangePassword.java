package com.example.warehousemanager.screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warehousemanager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    private EditText edtNhapMatKhau, edtNhapMatKhauMoi, edtNhapLaiMatKhauMoi;
    private Button btnDoiMK;
    FirebaseUser user;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Button btnDoiMK = findViewById(R.id.btnDoiMK);

        mapping();

        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reAuthenticationPassword();
            }
        });
        Toolbar toolbar = findViewById(R.id.tbChangePassword);
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

    private void reAuthenticationPassword() {
        user = auth.getCurrentUser();
        String email = user.getEmail();
        String password = edtNhapMatKhau.getText().toString().trim();

        if (password.isEmpty()) {
            edtNhapMatKhau.setError("Trường mật khẩu trống!");
        } else {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(email, password);
            user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        changePassword();
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu hiện tại không đúng!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void changePassword() {
        user = auth.getCurrentUser();
        String password = edtNhapMatKhauMoi.getText().toString().trim();
        String repassword = edtNhapLaiMatKhauMoi.getText().toString().trim();
        if (password.isEmpty()) {
            edtNhapMatKhauMoi.setError("Trường mật khẩu trống!");
        } else if (repassword.isEmpty()) {
            edtNhapLaiMatKhauMoi.setError("Trường nhập lại mật khẩu trống!");
        } else if (!repassword.equalsIgnoreCase(password)) {
            edtNhapLaiMatKhauMoi.setError("Nhập lại mật khẩu không khớp!");
        } else {
            user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    } else {
                        Toast.makeText(getApplicationContext(), "Đổi mật khẩu không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void mapping() {
        auth = FirebaseAuth.getInstance();
        edtNhapMatKhau = findViewById(R.id.txtNhapMatKhau);
        edtNhapMatKhauMoi = findViewById(R.id.txtNhapMatKhauMoi);
        edtNhapLaiMatKhauMoi = findViewById(R.id.txtNhapLaiMatKhauMoi);
        btnDoiMK = findViewById(R.id.btnDoiMK);
    }
}