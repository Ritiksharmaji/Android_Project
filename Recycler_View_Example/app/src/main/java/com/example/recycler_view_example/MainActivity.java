package com.example.recycler_view_example;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // step-7: create a object of Arraylist with contactModel class type.
    ArrayList<ContactModel>  arrayList_model = new ArrayList<>();
    // finding the ids
    FloatingActionButton floatingActionButton = findViewById(R.id.btnOpenDialog);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        //step-3: creating a object of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycle_View_id);

        //step-4: setting a layoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // step-7: creating object for ContactModel and adding it to arraylist.
        ContactModel contactModel = new ContactModel(R.drawable.a , "Ankit sharma","8292240471");
        arrayList_model.add(contactModel);

        //step-7: adding other one dy direct
        arrayList_model.add(new ContactModel(R.drawable.b, "Ritik sharma", "825625828"));
        arrayList_model.add(new ContactModel(R.drawable.e, "Ritik kumar sharma","620944027"));
        arrayList_model.add(new ContactModel(R.drawable.d, "mahima kumari","9973187962"));
        arrayList_model.add(new ContactModel(R.drawable.b, "Ritik sharma", "825625828"));
        arrayList_model.add(new ContactModel(R.drawable.e, "Ritik kumar sharma","620944027"));
        arrayList_model.add(new ContactModel(R.drawable.b, "Ritik sharma", "825625828"));
        arrayList_model.add(new ContactModel(R.drawable.e, "Ritik kumar sharma","620944027"));
        arrayList_model.add(new ContactModel(R.drawable.d, "mahima kumari","9973187962"));
        arrayList_model.add(new ContactModel(R.drawable.b, "Ritik sharma", "825625828"));
        arrayList_model.add(new ContactModel(R.drawable.e, "Ritik kumar sharma","620944027"));
        arrayList_model.add(new ContactModel(R.drawable.b, "Ritik sharma", "825625828"));
        arrayList_model.add(new ContactModel(R.drawable.e, "Ritik kumar sharma","620944027"));


        // Step-10: creating a object of custome RecyclerviewAdaper in mainActivity.java
        // after create successfully customer-adapter we are apply it to Recycler view
        RecycleContactAdapter adapter = new RecycleContactAdapter(this, arrayList_model);
        //Step-11: set the adapter to custome recyclerviewAdaper
        recyclerView.setAdapter(adapter);
    }
}