package com.example.assign2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class RecyclerShowData extends AppCompatActivity {

    Button button_insert;
    RecyclerView recyclerView;

    DatabaseHelper myDB;
    ArrayList<String> std_id,std_name,std_age,std_city;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_show_data);

        button_insert = findViewById(R.id.btn_insertAgain);
        recyclerView = findViewById(R.id.recyclerView1);

        button_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerShowData.this,MainActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseHelper(RecyclerShowData.this);
        std_id = new ArrayList<>();
        std_name = new ArrayList<>();
        std_age = new ArrayList<>();
        std_city = new ArrayList<>();
        displayDataInArray();
        customAdapter = new CustomAdapter(RecyclerShowData.this,std_id,std_name,std_city,std_age);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerShowData.this));
    }

    void displayDataInArray() {

        Cursor cursor = myDB.get_studentData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                std_id.add(cursor.getString(0));
                std_name.add(cursor.getString(1));
                std_city.add(cursor.getString(2));
                std_age.add(cursor.getString(3));
            }
        }
    }


}