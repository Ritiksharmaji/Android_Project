package com.example.recycler_view_example;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // create a object of Arraylist with coontactModel class type.
    ArrayList<ContactModel>  arrayList_model = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // creating a object of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycle_View_id);

        // setting a layoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // creating object for ContactModel and adding it to arraylist.
        ContactModel contactModel = new ContactModel(R.drawable.a , "Ankit sharma","8292240471");
        arrayList_model.add(contactModel);

        // adding other one dy direct
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


        // after create successfully customer-adapter we are apply it to Recycler view
        RecycleContactAdapter adapter = new RecycleContactAdapter(this, arrayList_model);
        recyclerView.setAdapter(adapter);
    }
}