package com.example.newsproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UserPostsActivity extends AppCompatActivity {

    private ListView lvPosts;
    private Button btnCreatePost;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private PostsAdapter postsAdapter;
    private List<Map<String, Object>> postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_posts);

        lvPosts = findViewById(R.id.lvPosts);
        btnCreatePost = findViewById(R.id.btnCreatePost);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        postsList = new ArrayList<>();
        postsAdapter = new PostsAdapter(this, postsList);
        lvPosts.setAdapter(postsAdapter);

        btnCreatePost.setOnClickListener(v -> startActivity(new Intent(UserPostsActivity.this, PostNewsActivity.class)));

        fetchUserPosts();
    }

    private void fetchUserPosts() {
        String userId = mAuth.getCurrentUser().getUid();
        db.collection("posts").whereEqualTo("userId", userId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            postsList.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> post = new HashMap<>();
                                post.put("title", document.getString("title"));
                                post.put("description", document.getString("description"));
                                postsList.add(post);
                            }
                            postsAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(UserPostsActivity.this, "Error fetching posts", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}