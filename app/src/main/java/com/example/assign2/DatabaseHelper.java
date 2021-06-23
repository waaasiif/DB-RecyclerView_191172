package com.example.assign2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TableLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context mainContext;
    private static String DATABASE = "studentdetails.db";
    private static String TABLE_NAME = "studentinfo";
    private static int DATABASE_VERSION = 1;


    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        mainContext = context;
        Toast.makeText(context, "Database created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query_create = "create table " + TABLE_NAME + "(std_id TEXT primary key,"+
                "std_name TEXT ,std_city TEXT, std_age TEXT)";

        db.execSQL(query_create);
        Toast.makeText(mainContext, "Table created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public void insert_studentData(String s_id, String s_name, String s_city, String s_age) {

        SQLiteDatabase sql_db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("std_id", s_id);
        contentValues.put("std_name", s_name);
        contentValues.put("std_age", s_age);
        contentValues.put("std_city", s_city);

        long result = sql_db.insert(TABLE_NAME, null, contentValues);
        if (result==-1){
            Toast.makeText(mainContext, "Failed!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(mainContext, "Data inserted successfully" , Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor get_studentData(){
        String query = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!=null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }
}

