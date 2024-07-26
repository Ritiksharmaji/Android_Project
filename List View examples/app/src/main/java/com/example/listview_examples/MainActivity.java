package com.example.listview_examples;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    step-1:
    // creating instance of listView and ArrayList.
    ListView listView;
    Spinner spinner;
    TextView textView;
    AutoCompleteTextView autoCompleteTextView;

    // step-2:
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList_spinner = new ArrayList<>();
    ArrayList<String> arrayList_autoComplete = new ArrayList<>();

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

        // step-3:
        // finding the id of ListView from xml.
        listView = findViewById(R.id.list_view_id);
        spinner = findViewById(R.id.spinner_id);
        textView = findViewById(R.id.spinner_Textview_id);
        autoCompleteTextView = findViewById(R.id.autoComplete_id);

        // step-4:
        // adding the string variables into the ArrayList
        arrayList.add("Ankit Sharma");
        arrayList.add("Ritik sharma");
        arrayList.add("Priyanshu sharma");
        arrayList.add("Mahima kumari");

            // for spinner listview
        arrayList_spinner.add("211FJ01050");
        arrayList_spinner.add("211FJ01051");
        arrayList_spinner.add("211FJ01052");
        arrayList_spinner.add("211FJ01053");
        arrayList_spinner.add("211FJ01054");
        arrayList_spinner.add("211FJ01055");
        arrayList_spinner.add("211FJ01056");
        arrayList_spinner.add("211FJ01057");

            // for autocomplete-textview
        arrayList_autoComplete.add("Hindi");
        arrayList_autoComplete.add("english");
        arrayList_autoComplete.add("telgu");
        arrayList_autoComplete.add("gangling");
        arrayList_autoComplete.add("bhojpuri");


// step-5:
        // create a ArrayAdapter object
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList);

        // this is for spinner views
        ArrayAdapter<String> arrayAdapter_spinner = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_dropdown_item_1line,
                arrayList_spinner);

        // this is for autocomplete-text views
        ArrayAdapter<String> arrayAdapter_autoCompleted = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,
                arrayList_autoComplete);

// step-6:
        //setting the adapter object to listview object so that list will be display.
        listView.setAdapter(arrayAdapter);

            // this is for spinner views
        spinner.setAdapter(arrayAdapter_spinner);

        // this is for spinner views
        autoCompleteTextView.setAdapter(arrayAdapter_autoCompleted);

        
        // to apply the onClick listener on the items of listview we are calling the ListView_object.setOnItemClickListener()
        // by using this method we call click on particular list item of listview.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            if(position == 0){
                Toast.makeText(MainActivity.this, "first field selected",Toast.LENGTH_LONG).show();


            }

            }
        });


    }
}