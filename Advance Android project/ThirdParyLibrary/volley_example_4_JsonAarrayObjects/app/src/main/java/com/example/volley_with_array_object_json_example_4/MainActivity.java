package com.example.volley_with_array_object_json_example_4;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Student> studentList;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        studentList = new ArrayList<>();
        adapter = new StudentAdapter(this, studentList);
        listView.setAdapter(adapter);

        fetchData();
    }

    private void fetchData() {
        String url = "https://www.myjsons.com/v/jsonArrayObject";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject student = response.getJSONObject(i);

                                String name = student.getString("name");
                                int age = student.getInt("age");
                                String sex = student.getString("sex");
                                String id = student.getString("id");
                                String course = student.getString("course");
                                String year = student.getString("year");

                                // Create Student object and add to list
                                studentList.add(new Student(name, age, sex, id, course, year));
                            }

                            // Notify adapter about data changes
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", error.toString());
                    }
                });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }
}
