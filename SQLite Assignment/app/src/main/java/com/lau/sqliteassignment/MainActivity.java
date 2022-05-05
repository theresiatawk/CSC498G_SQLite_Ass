package com.lau.sqliteassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking the variable the the list view layout
        list = (ListView) findViewById(R.id.list_view);


        // Creating db
        try{
            SQLiteDatabase sql_db = this.openOrCreateDatabase("examsdb", MODE_PRIVATE, null);
            sql_db.execSQL("CREATE TABLE if not exists exams(name VARCHAR, study_link VARCHAR)");
            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Mobile Computing', 'https://ionicframework.com/')");
            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Parallel Programming', 'https://docs.nvidia.com/cuda/')");
            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Database Management', 'https://www.w3schools.com/sql/')");
            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Object Oriented Programming', 'https://www.w3schools.com/java/')");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}