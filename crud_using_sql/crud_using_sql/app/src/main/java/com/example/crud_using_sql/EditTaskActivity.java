package com.example.crud_using_sql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class EditTaskActivity extends AppCompatActivity {

    private EditText editTitleEditText, editDescriptionEditText;
    private Button updateButton, deleteButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTitleEditText = findViewById(R.id.editTitleEditText);
        editDescriptionEditText = findViewById(R.id.editDescriptionEditText);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        String taskId = getIntent().getStringExtra("taskId");
        String currentTitle = getIntent().getStringExtra("currentTitle");
        String currentDescription = getIntent().getStringExtra("currentDescription");

        editTitleEditText.setText(currentTitle);
        editDescriptionEditText.setText(currentDescription);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTask(taskId);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(taskId);
            }
        });
    }

    private void updateTask(String taskId) {
        String updatedTitle = editTitleEditText.getText().toString().trim();
        String updatedDescription = editDescriptionEditText.getText().toString().trim();


        String url = "http://14.139.85.169/jspapi/crud/update.jsp?title="+updatedTitle+"&description="+updatedDescription+"&id="+taskId;

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response.trim().equalsIgnoreCase("success"))
                        Toast.makeText(EditTaskActivity.this, "Task updated successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EditTaskActivity.this, "Failed to update task", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(EditTaskActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditTaskActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(request);

    }

    private void deleteTask(String taskId) {
        String url = "http://14.139.85.169/jspapi/crud/delete.jsp?id="+taskId;

        RequestQueue rq = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(response.trim().equalsIgnoreCase("success"))
                        Toast.makeText(EditTaskActivity.this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EditTaskActivity.this, "Failed to delete task", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(EditTaskActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                } finally {
                    finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditTaskActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        rq.add(request);

    }

}