package com.example.recycler_view_example;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    // Step-10: creating a object of custome RecyclerviewAdaper in mainActivity.java
    // after create successfully customer-adapter we are apply it to Recycler view
    RecycleContactAdapter adapter = new RecycleContactAdapter(this, arrayList_model);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //step-3: creating a object of RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycle_View_id);

        //step-4: setting a layoutManager to RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // finding the ids of FloatingActionButton
        FloatingActionButton floatingActionButton = findViewById(R.id.btnOpenDialog);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // creating a custome dailog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_lay);
                //fetch the data from the dialog box
                EditText editText_name = dialog.findViewById(R.id.editTextName);
                EditText editText_age = dialog.findViewById(R.id.editTextNumber);
                Button button_add = dialog.findViewById(R.id.button_Action);
                button_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // getting the values from both the edittext
                        String edit1 = editText_name.getText().toString();
                        String edit2 = editText_age.getText().toString();

                        //adding the data to arraylist.
                        arrayList_model.add(new ContactModel(edit1,edit2));
                        //to insert the data into adapter and scroll the screen to dsplay that data.
                        adapter.notifyItemInserted(arrayList_model.size()-1);
                        recyclerView.scrollToPosition(arrayList_model.size()-1);

                        // to dismiss the dialog after adding the data
                        dialog.dismiss();
                    }
                });



                dialog.show();

            }
        });



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



        //Step-11: set the adapter to custome recyclerviewAdaper
        recyclerView.setAdapter(adapter);
    }
}