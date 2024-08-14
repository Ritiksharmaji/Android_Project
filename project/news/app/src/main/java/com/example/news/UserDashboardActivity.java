package com.example.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserDashboardActivity extends AppCompatActivity {

    private TextView tvWelcome, tvUserDetails;
    private Button btnCreatePost, btnLogout;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_dashboard);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvUserDetails = findViewById(R.id.tvUserDetails);
        btnCreatePost = findViewById(R.id.btnCreatePost);
        btnLogout = findViewById(R.id.btnLogout);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Display user information
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    String name = document.getString("name");
                    String email = document.getString("email");
                    String phone = document.getString("phone");
                    String branch = document.getString("branch");

                    tvWelcome.setText("Welcome, " + name + "!");
                    tvUserDetails.setText("Email: " + email + "\nPhone: " + phone + "\nBranch: " + branch);
                }
            }
        });

        // Handle Create and View Posts button click
        btnCreatePost.setOnClickListener(v -> {
            startActivity(new Intent(UserDashboardActivity.this, PostNewsActivity.class));
        });

        // Handle Logout button click
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(UserDashboardActivity.this, MainActivity.class));
            finish();
        });
    }
}