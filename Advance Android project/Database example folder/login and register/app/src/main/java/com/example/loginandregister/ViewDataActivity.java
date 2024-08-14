package com.example.loginandregister;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ViewDataActivity extends AppCompatActivity {

    private TextView dataTextView,nameview,ageview;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        dataTextView = findViewById(R.id.dataTextView);
        db = FirebaseFirestore.getInstance();
        nameview = findViewById(R.id.nameTextview);
        ageview = findViewById(R.id.ageTextview);

        loadUserSpecificData();
    }

    private void loadUserSpecificData() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        CollectionReference studentsRef = db.collection("Students");

        // Query Firestore to get documents where userId matches the current user
        studentsRef.whereEqualTo("userId", userId)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot querySnapshot = task.getResult();
                        StringBuilder dataBuilder = new StringBuilder();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            Log.d("datas are:", String.valueOf(document));
                            String name = document.getString("name");
                            String age = document.getString("age");
//                            dataBuilder.append("Name: ").append(name)
//                                    .append("\nAge: ").append(age)
//                                    .append("\n\n");
                            nameview.setText(name);
                            ageview.setText(age);
                        }

                    } else {
                        nameview.setText("Error getting documents: " + task.getException());
                    }
                });
    }
}