package com.example.onlion_quize_app;

import static com.example.onlion_quize_app.MainActivity.arrayList_modelclass;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 20000; // 20 seconds for each question

    // Declare the UI elements for question and options
    private TextView questionTextView;
    private CardView option1CardView, option2CardView, option3CardView, option4CardView;
    private TextView option1TextView, option2TextView, option3TextView, option4TextView;
    private TextView nextButton;

    // Declare an ArrayList by importing the MainActivityClass's ArrayList object.
    ArrayList<modelclass> allQuestions = arrayList_modelclass;

    // To keep track of the current question
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        // Initialize the timer view
        timerTextView = findViewById(R.id.timer_text_view);

        // Initialize the question and option views
        questionTextView = findViewById(R.id.question_1);
        option1CardView = findViewById(R.id.card_view_1);
        option2CardView = findViewById(R.id.card_view_2);
        option3CardView = findViewById(R.id.card_view_3);
        option4CardView = findViewById(R.id.card_view_4);
        option1TextView = findViewById(R.id.option_1_question_1);
        option2TextView = findViewById(R.id.option_2_question_1);
        option3TextView = findViewById(R.id.option_3_question_1);
        option4TextView = findViewById(R.id.option_4_question_1);
        nextButton = findViewById(R.id.submit_button_question_1);

        // Load the first question
        loadQuestion();

        // Start the timer
        startTimer();

        // Handle the Next button click
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex++;
                if (currentQuestionIndex < allQuestions.size()) {
                    loadQuestion();
                    resetTimer();
                } else {
                    // Handle the end of the quiz
                    showQuizCompletedDialog();
                }
            }
        });
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
                showTimeUpDialog();
            }
        }.start();
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timeLeftInMillis = 20000; // Reset to 20 seconds for the next question
        startTimer();
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    private void showTimeUpDialog() {
        Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setContentView(R.layout.time_out_dialog);

        Button tryOneMoreTextview = dialog.findViewById(R.id.try_one_more_time_id);
        tryOneMoreTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionIndex = 0;
                loadQuestion();
                resetTimer();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showQuizCompletedDialog() {
        Dialog dialog = new Dialog(DashBoardActivity.this);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setContentView(R.layout.quiz_completed_dialog);
        dialog.show();

    }

    private void loadQuestion() {
        modelclass currentQuestion = allQuestions.get(currentQuestionIndex);

        questionTextView.setText(currentQuestion.getQuestion());
        option1TextView.setText(currentQuestion.getOpt1());
        option2TextView.setText(currentQuestion.getOpt2());
        option3TextView.setText(currentQuestion.getOpt3());
        option4TextView.setText(currentQuestion.getOpt4());
    }
}
