package com.example.volley_example_6_2_dynamic_single_object;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText registation,userName,userbranch,userPassword;
    String URL = "https://66b9efabfa763ff550fa2f7f.mockapi.io/postsimpleJson/postSingleJson";
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        registation = findViewById(R.id.editUserId);
        userName = findViewById(R.id.editUserName);
        userbranch = findViewById(R.id.editUserBranch);
        userPassword = findViewById(R.id.editTextUserPassword);
        submit = findViewById(R.id.SubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create json object
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("registation", registation.getText().toString());
                    jsonObject.put("userName", userName.getText().toString());
                    jsonObject.put("userbranch", userbranch.getText().toString());
                    jsonObject.put("userPassword", userPassword.getText().toString());

                    String registationText = registation.getText().toString();
                    String userNameText = userName.getText().toString();
                    String userBranchText = userbranch.getText().toString();
                    String userPasswordText = userPassword.getText().toString();
                    // Validate input
                    if (registationText.isEmpty() || userNameText.isEmpty() || userBranchText.isEmpty()
                            || userPasswordText.isEmpty()) {
                        Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_LONG).show();
                        return;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // Send the data to the server
                sendPostRequest(jsonObject);
            }
        });

    }

    private void sendPostRequest(JSONObject postData) {
        // Create a JsonObjectRequest
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                URL,
                postData, // The JSON object you want to send
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response from the server
                        Log.d("Response", "Success: " + response.toString());
                        Toast.makeText(MainActivity.this, "data successfully send to APIs",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle any errors
                        Log.e("Error", "Error: " + error.toString());
                    }
                }
        ) {

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        // Add the request to the RequestQueue
        requestQueue.add(jsonObjectRequest);
    }
}