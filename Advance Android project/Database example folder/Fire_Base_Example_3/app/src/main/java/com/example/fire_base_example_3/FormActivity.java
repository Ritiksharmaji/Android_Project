package com.example.fire_base_example_3;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;

public class FormActivity extends AppCompatActivity {

    private EditText editTextName, editTextPhone, editTextEmail, editTextPassword;
    private Spinner spinnerCity, spinnerDepartment;
    private Button buttonSubmit;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        spinnerCity = findViewById(R.id.spinnerCity);
        editTextEmail = findViewById(R.id.editTextEmail);
        spinnerDepartment = findViewById(R.id.spinnerDepartment);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        // Handle form submission
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    submitForm();
                }
            }
        });
    }

    // Method to validate form fields
    private boolean validateForm() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    // Method to submit form data to Firebase
    private void submitForm() {
        String name = editTextName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String city = spinnerCity.getSelectedItem().toString();
        String email = editTextEmail.getText().toString().trim();
        String department = spinnerDepartment.getSelectedItem().toString();
        String password = editTextPassword.getText().toString().trim();

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("phone", phone);
        userData.put("city", city);
        userData.put("email", email);
        userData.put("department", department);
        userData.put("password", password);

        // Store data in Firebase Realtime Database
        databaseReference.push().setValue(userData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(FormActivity.this, "Form submitted successfully", Toast.LENGTH_SHORT).show();
                    clearForm();
                })
                .addOnFailureListener(e -> Toast.makeText(FormActivity.this, "Form submission failed: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    // Method to clear form fields after submission
    private void clearForm() {
        editTextName.setText("");
        editTextPhone.setText("");
        editTextEmail.setText("");
        editTextPassword.setText("");
        spinnerCity.setSelection(0);
        spinnerDepartment.setSelection(0);
    }
}
