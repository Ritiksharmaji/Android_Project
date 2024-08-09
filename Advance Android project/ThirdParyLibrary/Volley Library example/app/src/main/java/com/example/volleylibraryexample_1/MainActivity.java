package com.example.volleylibraryexample_1;
import android.os.Bundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Private string declared in the latter section of the program
        String jsonStr = getListData();
        try {
            // Create a userList string hashmap ArrayList
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();

            // Declaring the ListView from the layout file
            ListView lv = findViewById(R.id.user_list);

            // Initializing the JSON object and extracting the information
            JSONObject jObj = new JSONObject(jsonStr);
            JSONArray jsonArry = jObj.getJSONArray("users");
            for (int i = 0; i < jsonArry.length(); i++) {
                HashMap<String, String> user = new HashMap<>();
                JSONObject obj = jsonArry.getJSONObject(i);
                user.put("name", obj.getString("name"));
                user.put("designation", obj.getString("designation"));
                user.put("location", obj.getString("location"));
                userList.add(user);
            }

            // ListAdapter to broadcast the information to the list elements
            ListAdapter adapter = new SimpleAdapter(
                    this, userList, R.layout.list_row,
                    new String[]{"name", "designation", "location"},
                    new int[]{R.id.name, R.id.designation, R.id.location}
            );
            lv.setAdapter(adapter);
        } catch (JSONException ex) {
            Log.e("JsonParser Example", "unexpected JSON exception", ex);
        }
    }

    // JSON object in the form of input stream
    private String getListData() {
        return "{ \"users\" :" +
                "[{\"name\":\"Ace\",\"designation\":\"Engineer\",\"location\":\"New York\"}" +
                ",{\"name\":\"Tom\",\"designation\":\"Director\",\"location\":\"Chicago\"}" +
                ",{\"name\":\"Tim\",\"designation\":\"Chartered Accountant\",\"location\":\"Sunnyvale\"}] }";
    }
}
