package com.example.bookmarkapp;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BookmarkAdapter adapter;
    private List<Bookmark> bookmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookmarks = new ArrayList<>();
        adapter = new BookmarkAdapter(this, bookmarks);

        ListView listView = findViewById(R.id.bookmark_list);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            Uri uri = intent.getData();
            if (uri != null) {
                String websiteUrl = uri.getQueryParameter("url");
                String roleName = uri.getQueryParameter("role");

                // Save the bookmark in your app
                saveBookmark(websiteUrl, roleName);
            }
        }

        loadBookmarks();
    }

    private void saveBookmark(String url, String role) {
        ContentValues values = new ContentValues();
        values.put("url", url);
        values.put("role", role);

        getContentResolver().insert(BookmarkProvider.CONTENT_URI, values);
        loadBookmarks();
    }

    private void loadBookmarks() {
        bookmarks.clear();
        Cursor cursor = getContentResolver().query(BookmarkProvider.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") String url = cursor.getString(cursor.getColumnIndex("url"));
                @SuppressLint("Range") String role = cursor.getString(cursor.getColumnIndex("role"));
                bookmarks.add(new Bookmark(url, role));
            }
            cursor.close();
        }
        adapter.notifyDataSetChanged();
    }
}