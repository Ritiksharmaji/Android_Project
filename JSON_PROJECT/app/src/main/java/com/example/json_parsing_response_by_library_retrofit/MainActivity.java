package com.example.json_parsing_response_by_library_retrofit;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView in the layout
        textView = findViewById(R.id.textView);

        // Create an instance of the Retrofit service
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Make the network request
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> users = response.body();

                    // StringBuilder to accumulate the parsed data
                    StringBuilder parsedResult = new StringBuilder();

                    for (User user : users) {
                        // Append the extracted data to the StringBuilder
                        parsedResult.append("ID: ").append(user.getId())
                                .append("\nName: ").append(user.getName())
                                .append("\nUsername: ").append(user.getUsername())
                                .append("\nEmail: ").append(user.getEmail())
                                .append("\n\n");
                    }

                    // Update the TextView with the parsed data
                    textView.setText(parsedResult.toString());
                } else {
                    Toast.makeText(MainActivity.this, "Response unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
