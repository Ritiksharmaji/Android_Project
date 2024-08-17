package com.example.newsproject;

import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdminDashboardActivity extends AppCompatActivity {

    private TextView tvPosts;
    private Button btnFilterByDepartment, btnFilterByUser;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);


        tvPosts = findViewById(R.id.tvPosts);
        btnFilterByDepartment = findViewById(R.id.btnFilterByDepartment);
        btnFilterByUser = findViewById(R.id.btnFilterByUser);
        db = FirebaseFirestore.getInstance();

        btnFilterByDepartment.setOnClickListener(v -> filterPosts("department"));
        btnFilterByUser.setOnClickListener(v -> filterPosts("user"));
        fetchAllPosts();
    }

    private void fetchAllPosts() {
        db.collection("posts").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<DocumentSnapshot> documents = task.getResult().getDocuments();
                StringBuilder posts = new StringBuilder();
                for (DocumentSnapshot document : documents) {
                    posts.append(document.getString("title")).append("\n");
                    posts.append(document.getString("description")).append("\n\n");
                }
                tvPosts.setText(posts.toString());
            }
        });
    }

    private void filterPosts(String filterType) {
        // Implement filtering logic here
    }
}