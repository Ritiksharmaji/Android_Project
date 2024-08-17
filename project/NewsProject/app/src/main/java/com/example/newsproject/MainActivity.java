package com.example.newsproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnUserLogin, btnUserRegister, btnAdminLogin, btnAdminRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUserLogin = findViewById(R.id.btnUserLogin);
        btnUserRegister = findViewById(R.id.btnUserRegister);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        btnAdminRegister = findViewById(R.id.btnAdminRegister);

        btnUserLogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));
        btnUserRegister.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RegistrationActivity.class)));
        btnAdminLogin.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdminLoginActivity.class)));
        btnAdminRegister.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdminRegistrationActivity.class)));
    }
}
