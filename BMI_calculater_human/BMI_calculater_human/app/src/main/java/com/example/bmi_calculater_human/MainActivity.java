package com.example.bmi_calculater_human;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private AdView adView;

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

        EditText weight_text = findViewById(R.id.weight_id);
        EditText height_ft = findViewById(R.id.height_feet_id);
        EditText height_inch = findViewById(R.id.height_inch_id);
        Button calculate_btn = findViewById(R.id.button_BMI);
        TextView result = findViewById(R.id.result_BMI_id);
        LinearLayout layout = findViewById(R.id.main);

        calculate_btn.setOnClickListener(view -> {
            int weight = Integer.parseInt(weight_text.getText().toString());
            int h_ft = Integer.parseInt(height_ft.getText().toString());
            int h_inch = Integer.parseInt(height_inch.getText().toString());
            int totalInch = h_inch + h_ft * 12;
            double totalCentimeter = totalInch * 2.54;
            double totalMeter = totalCentimeter / 100;
            double totalBMI = weight / (totalMeter * totalMeter);

            if (totalBMI > 25) {
                result.setText("You are overweight");
                layout.setBackgroundColor(getResources().getColor(R.color.color_over_weight));
            } else if (totalBMI < 18) {
                result.setText("You are underweight");
                layout.setBackgroundColor(getResources().getColor(R.color.color_under_weight));
            } else {
                result.setText("You are healthy");
                layout.setBackgroundColor(getResources().getColor(R.color.color_perfect_weight));
            }
        });

        // Initialize Mobile Ads SDK
        // Initialize the Google Mobile Ads SDK on a background thread.
        MobileAds.initialize(this); {}

//        // Load an ads
        adView = findViewById(R.id.ads_id);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
}
