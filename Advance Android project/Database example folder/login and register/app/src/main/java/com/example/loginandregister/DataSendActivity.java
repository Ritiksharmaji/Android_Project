package com.example.loginandregister;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataSendActivity extends AppCompatActivity {

    EditText userName,userage;
    Button btn,bt2;
    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data_send);

        userName = findViewById(R.id.editTextName);
        userage = findViewById(R.id.editTextAge);
        btn = findViewById(R.id.submitButton);
        bt2 = findViewById(R.id.viewData);
        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitData();
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DataSendActivity.this,ViewDataActivity.class);
                startActivity(intent);
            }
        });

    }

    public void submitData() {
        String name = userName.getText().toString();
        String age = userage.getText().toString();
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid(); // Get the current user's ID

        Students students = new Students(name, age, userId);

        db.collection("Students")
                .document(name)
                .set(students)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(DataSendActivity.this, "Your data has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DataSendActivity.this, "Failed to add data\n" + e, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
