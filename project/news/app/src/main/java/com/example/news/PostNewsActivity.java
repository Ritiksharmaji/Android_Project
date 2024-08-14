package com.example.news;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class PostNewsActivity extends AppCompatActivity {

    private EditText etPostTitle, etPostContent;
    private Button btnSubmitPost;
    private LinearLayout llPosts;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_news);


        etPostTitle = findViewById(R.id.etPostTitle);
        etPostContent = findViewById(R.id.etPostContent);
        btnSubmitPost = findViewById(R.id.btnSubmitPost);
        llPosts = findViewById(R.id.llPosts);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Display user posts
        displayUserPosts();

        // Handle Submit Post button click
        btnSubmitPost.setOnClickListener(v -> {
            String title = etPostTitle.getText().toString().trim();
            String content = etPostContent.getText().toString().trim();

            if (title.isEmpty() || content.isEmpty()) {
                // Show error message
                return;
            }

            String userId = mAuth.getCurrentUser().getUid();
            NewsPost post = new NewsPost(title, content, userId);

            db.collection("posts").add(post).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    // Clear input fields and refresh posts
                    etPostTitle.setText("");
                    etPostContent.setText("");
                    displayUserPosts();
                }
            });
        });
    }

    private void displayUserPosts() {
        llPosts.removeAllViews();
        String userId = mAuth.getCurrentUser().getUid();

        db.collection("posts").whereEqualTo("userId", userId).get().addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                QuerySnapshot querySnapshot = task.getResult();
                if (querySnapshot != null) {
                    for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                        String title = document.getString("title");
                        String content = document.getString("content");

                        TextView postView = new TextView(PostNewsActivity.this);
                        postView.setText("Title: " + title + "\nContent: " + content);
                        postView.setPadding(16, 16, 16, 16);
                        llPosts.addView(postView);
                    }
                }
            }
        });
    }
}