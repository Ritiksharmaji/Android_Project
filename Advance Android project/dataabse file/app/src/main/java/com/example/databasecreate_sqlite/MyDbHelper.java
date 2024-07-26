package com.example.databasecreate_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {

    // declare the values of database and version
    private static final String DATABASE_NAME = "Contact";
    private static final int DATABASE_VERSION = 1;
    private static final String table_contact = "contact_table";
    private static final String key_id= "id";
    private static final String key_name= "name";
    private static final String key_number= "phone_num";

    public MyDbHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public MyDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // create table table_name ( id type_id , key_name type_key_name , key_phone type_key_phone);
        sqLiteDatabase.execSQL("create table "+ table_contact +
                "(" + key_id+  " integer primary key autoincrement," + key_name + " text," + key_number + " text" +")");

        // to open the database
        SQLiteDatabase db = this.getWritableDatabase();
        // to close the database
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + table_contact);
        onCreate(sqLiteDatabase);

    }


}
