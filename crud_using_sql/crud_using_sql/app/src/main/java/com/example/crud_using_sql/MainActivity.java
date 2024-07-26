package com.example.crud_using_sql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskClickListener {

    private List<Task> taskList = new ArrayList<>();
    private EditText titleEditText, descriptionEditText;
    private Button addButton, read, write, cancel;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    private LinearLayout l1, l2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleEditText = findViewById(R.id.titleEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        addButton = findViewById(R.id.addButton);
        recyclerView = findViewById(R.id.recyclerView);
        read = findViewById(R.id.read);
        write = findViewById(R.id.write);
        l1 = findViewById(R.id.linear);
        l2 = findViewById(R.id.linear2);
        cancel = findViewById(R.id.cancel);

        l1.setVisibility(View.GONE);

        taskAdapter = new TaskAdapter(taskList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readTasks();
            }
        });

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                readTasks();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.VISIBLE);
                readTasks();
            }
        });



    }
    @Override
    public void onRestart() {
        super.onRestart();
        readTasks();
    }
    private void addTask() {
        // Add task code, similar to the previous examples
        String title = titleEditText.getText().toString().trim();
        String description = descriptionEditText.getText().toString().trim();

        if (!title.isEmpty() && !description.isEmpty()) {

            String url = "http://14.139.85.169/jspapi/crud/create.jsp?title="+title+"&description="+description;

            RequestQueue rq = Volley.newRequestQueue(getApplicationContext());

            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        if(response.trim().equalsIgnoreCase("success"))
                            Toast.makeText(MainActivity.this, "Task added successfully", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Failed to add task", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            rq.add(request);

        } else {
            // Handle the case where title or description is empty
            Toast.makeText(this, "Title and description cannot be empty", Toast.LENGTH_SHORT).show();
        }

        // After adding a task, you might want to refresh the task list
        readTasks();
    }

    private void readTasks() {

        String url = "http://14.139.85.169/jspapi/crud/read.jsp";
        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("details");
                    taskList.clear();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject hit=jsonArray.getJSONObject(i);
                        String task = hit.getString("title");
                        String description = hit.getString("description");
                        String id = hit.getString("id");
                        taskList.add(new Task(task,description,id));
                    }

                    updateUIWithTasks();
                } catch (JSONException e) {
                    Log.d("SQL", "Error reading tasks"+e.toString());
                    Toast.makeText(MainActivity.this, "Error reading tasks", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(request);

    }

    private void updateUIWithTasks() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                taskAdapter.setTaskList(taskList);
                taskAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTaskClick(int position) {
        Task task = taskList.get(position);
        showEditTaskActivity(task);
    }

    private void showEditTaskActivity(Task task) {
        Intent intent = new Intent(this, EditTaskActivity.class);
        intent.putExtra("taskId", task.getTaskId());
        intent.putExtra("currentTitle", task.getTitle());
        intent.putExtra("currentDescription", task.getDescription());
        startActivity(intent);
    }

}