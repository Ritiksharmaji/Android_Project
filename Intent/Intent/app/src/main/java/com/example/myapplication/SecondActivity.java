package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // to fetch the data from other Actitvity we are creating a intent
        // here we are getting the data from first intent so
        // we create a intent by calling the getIntent() method not create a agian new object
        // Intent second_intent = new Intent();
        Intent second_intent = getIntent();
        String title =  second_intent.getStringExtra("title");
        String studentName =  second_intent.getStringExtra("student_name");
       int rollNo=  second_intent.getIntExtra("ROll_No",0);

       // create object fo Textview to set the above data into GUI by xml file

        TextView studentname = findViewById(R.id.studentName);

        studentname.setText( "Roll no:"+ rollNo + "  " +"Student Name:"+ studentName);
        // to set the title bar
//        getSupportActionBar().setTitle(title);


    }
}