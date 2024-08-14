package com.example.news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;


public class AdminDashboardActivity extends AppCompatActivity {


    private ListView lvPosts;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);


        lvPosts = findViewById(R.id.lvPosts);
        db = FirebaseFirestore.getInstance();

        fetchPosts();
    }

    private void fetchPosts() {
        db.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<String> posts = new ArrayList<>();
                    for (DocumentSnapshot document : task.getResult()) {
                        String title = document.getString("title");
                        String content = document.getString("content");
                        posts.add("Title: " + title + "\nContent: " + content);
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(AdminDashboardActivity.this, android.R.layout.simple_list_item_1, posts);
                    lvPosts.setAdapter(adapter);
                } else {
                    // Handle error
                    Toast.makeText(AdminDashboardActivity.this, "Error fetching posts: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}