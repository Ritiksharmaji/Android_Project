package com.example.onlion_quize_app;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class DashBoardActivity extends AppCompatActivity {

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    CardView cardView;
    private long timeLeftInMillis = 20000; // 1 minute = 60000 milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        timerTextView = findViewById(R.id.timer_text_view);

        startTimer();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
//                timerTextView.setText("Time's Up!");
                // You can add any other logic you want to execute when the timer finishes
                // adding a dialog
//                Dialog dialog = new Dialog(DashBoardActivity.this, R.style.Dialoge);
//                dialog.setContentView(R.layout.time_out_dialog);
//                dialog.show();
                Dialog dialog = new Dialog(DashBoardActivity.this);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.show();


            }
        }.start();
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }
}
