package com.example.newsproject;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserDashboardActivity extends AppCompatActivity {

    private TextView tvUserName, tvUserEmail, tvUserPhone, tvUserCity, tvUserCategory;
    private Button btnPostNews, btnViewPosts;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_dashboard);

        tvUserName = findViewById(R.id.tvUserName);
        tvUserEmail = findViewById(R.id.tvUserEmail);
        tvUserPhone = findViewById(R.id.tvUserPhone);
        tvUserCity = findViewById(R.id.tvUserCity);
        tvUserCategory = findViewById(R.id.tvUserCategory);
        btnPostNews = findViewById(R.id.btnPostNews);
        btnViewPosts = findViewById(R.id.btnViewPosts);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String userId = mAuth.getCurrentUser().getUid();
        db.collection("users").document(userId).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    tvUserName.setText(document.getString("name"));
                    tvUserEmail.setText(document.getString("email"));
                    tvUserPhone.setText(document.getString("phone"));
                    tvUserCity.setText(document.getString("city"));
                    tvUserCategory.setText(document.getString("category"));
                }
            }
        });

        btnPostNews.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, PostNewsActivity.class)));
        btnViewPosts.setOnClickListener(v -> startActivity(new Intent(UserDashboardActivity.this, UserPostsActivity.class)));
    }
}