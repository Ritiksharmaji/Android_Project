package com.example.explicit_intent_example_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        // to get the bundle from the first Intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String name = bundle.getString("student_name");
            int age = bundle.getInt("age", -1); // -1 is a default value in case "age" is not found

            // display the data in Toast
            Toast.makeText(getApplicationContext(),"Name of Student: "+ name+ " age of student: "+ age,
                    Toast.LENGTH_LONG).show();

        }
    }

    public void callFirstActivity(View view){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }
}