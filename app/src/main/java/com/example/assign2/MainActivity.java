package com.example.assign2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtName;
    EditText edtID;
    EditText edtAge;
    EditText edtCity;
    Button btnSave;
    Button btnShow;
    TextView ViewData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtID = findViewById(R.id.std_id);
        edtName = findViewById(R.id.std_name);
        edtAge = findViewById(R.id.std_age);
        edtCity = findViewById(R.id.std_city);
        btnSave = findViewById(R.id.btn_save);
        btnShow = findViewById(R.id.btn_view);
        ViewData = findViewById(R.id.ViewData);
        DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myDB.insert_studentData(edtID.getText().toString(),
                        edtName.getText().toString(),
                        edtCity.getText().toString(),
                        edtAge.getText().toString());

                edtID.setText("");
                edtName.setText("");
                edtAge.setText("");
                edtCity.setText("");

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewDataOnTextView();

                Intent intent = new Intent(MainActivity.this, RecyclerShowData.class);
                startActivity(intent);
            }
        });
    }

    public void viewDataOnTextView() {
        String allRecords = "";
        DatabaseHelper myDB = new DatabaseHelper(MainActivity.this);
        Cursor eachRecord_cursor = myDB.get_studentData();

        if (eachRecord_cursor.getCount() == 0) {
            Toast.makeText(this, "No Record found", Toast.LENGTH_SHORT).show();
        }
        while (eachRecord_cursor.moveToNext()) {
            String eachRecord;
            eachRecord = "ID: " + eachRecord_cursor.getString(0) + "\n";
            eachRecord = eachRecord + "NAME:" + eachRecord_cursor.getString(1) + "\n";
            eachRecord = eachRecord + "CITY:" + eachRecord_cursor.getString(2) + "\n";
            eachRecord = eachRecord + "AGE:" + eachRecord_cursor.getString(3) + "\n";
            eachRecord = eachRecord + "---------------------------------------\n";
            allRecords = allRecords + eachRecord;
        }
        ViewData.setText(allRecords);
    }
}