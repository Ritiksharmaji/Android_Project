package com.example.bookmarkapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class BookmarkAdapter extends ArrayAdapter<Bookmark> {

    public BookmarkAdapter(Context context, List<Bookmark> bookmarks) {
        super(context, 0, bookmarks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bookmark bookmark = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bookmark_item, parent, false);
        }

        TextView urlTextView = convertView.findViewById(R.id.bookmark_url);
        TextView roleTextView = convertView.findViewById(R.id.bookmark_role);

        urlTextView.setText(bookmark.getUrl());
        roleTextView.setText(bookmark.getRole());

        return convertView;
    }
}
