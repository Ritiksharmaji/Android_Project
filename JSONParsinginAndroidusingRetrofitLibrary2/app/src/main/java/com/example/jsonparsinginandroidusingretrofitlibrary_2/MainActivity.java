package com.example.jsonparsinginandroidusingretrofitlibrary_2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView courseNameTV, courseTracksTV, courseBatchTV;
    private ImageView courseIV;
    private ProgressBar loadingPB;
    private CardView courseCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize variables
        loadingPB = findViewById(R.id.idLoadingPB);
        courseCV = findViewById(R.id.idCVCourse);
        courseNameTV = findViewById(R.id.idTVCourseName);
        courseTracksTV = findViewById(R.id.idTVTracks);
        courseBatchTV = findViewById(R.id.idTVBatch);
        courseIV = findViewById(R.id.idIVCourse);

        // Fetch course data
        getCourse();
    }

    private void getCourse() {
        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        https://jsonkeeper.com/b/

        // Create API instance
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // Call the API
        Call<RecyclerData> call = retrofitAPI.getCourse();

        // Enqueue the API call
        call.enqueue(new Callback<RecyclerData>() {
            @Override
            public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
                if (response.isSuccessful()) {
                    loadingPB.setVisibility(View.GONE);
                    courseCV.setVisibility(View.VISIBLE);
                    RecyclerData modal = response.body();

                    // Set the data to views
                    courseNameTV.setText(modal.getCourseName());
                    courseTracksTV.setText(modal.getCourseTracks());
                    courseBatchTV.setText(modal.getCourseMode());
                    Picasso.get().load(modal.getCourseimg()).into(courseIV);
                }
            }

            @Override
            public void onFailure(Call<RecyclerData> call, Throwable t) {
                // Handle error
                Toast.makeText(MainActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
