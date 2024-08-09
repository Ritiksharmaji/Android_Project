package com.example.bookmarkapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class BookmarkProvider extends ContentProvider {
    public static final String AUTHORITY = "com.example.bookmarkapp.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/bookmarks");

    private static final int BOOKMARKS = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, "bookmarks", BOOKMARKS);
    }

    private SQLiteDatabase database;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "bookmarks.db";
        private static final int DATABASE_VERSION = 1;

        private static final String TABLE_BOOKMARKS = "bookmarks";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_URL = "url";
        private static final String COLUMN_ROLE = "role";

        private static final String CREATE_TABLE_BOOKMARKS = "CREATE TABLE " + TABLE_BOOKMARKS + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_URL + " TEXT NOT NULL, "
                + COLUMN_ROLE + " TEXT NOT NULL);";

        public DatabaseHelper(android.content.Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_BOOKMARKS);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKMARKS);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        database = dbHelper.getWritableDatabase();
        return (database != null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.query("bookmarks", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case BOOKMARKS:
                return "vnd.android.cursor.dir/vnd.com.example.bookmarkapp.bookmarks";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = database.insert("bookmarks", "", values);
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return database.delete("bookmarks", selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return database.update("bookmarks", values, selection, selectionArgs);
    }
}
