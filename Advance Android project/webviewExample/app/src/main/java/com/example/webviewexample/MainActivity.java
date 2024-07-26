package com.example.webviewexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
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

        webView = findViewById(R.id.webview_id);
        progressBar = findViewById(R.id.progress_bar_id);

        // Enable JavaScript for better functionality , because within it, you can't search data in that search bar.
        webView.getSettings().setJavaScriptEnabled(true);

        // Load the URL in WebView
        webView.loadUrl("https://www.google.com");

        // Set the WebViewClient on WebView object to handle URL loading within the WebView to control the flow of web.
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // when the page will load something then loader will display.
                progressBar.setVisibility(View.VISIBLE);
                //to start the new pages either next or previous.
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // after loading the pages it will not display.
                progressBar.setVisibility(View.GONE);
                //when there is nothing to load or not any previous or next pages also not available then it will stop then app.
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onBackPressed() {

//        by-default this super.onBackPressed(); will be implements to pop the
//        activity but if you have back activity for that we have to make some changes
//        super.onBackPressed();
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}