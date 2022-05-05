package com.lau.sqliteassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hiding the action bar
        getSupportActionBar().hide();

        // Linking the variable the the list view layout
        list = (ListView) findViewById(R.id.list_view);

        // Creating the array lists needed to store the info fetched from the database
        ArrayList<String> exam_names = new ArrayList<>();
        ArrayList<String> study_links = new ArrayList<>();

        // Creating db
        try{
            SQLiteDatabase sql_db = this.openOrCreateDatabase("examsdb", MODE_PRIVATE, null);
            sql_db.execSQL("CREATE TABLE if not exists exams(name VARCHAR, study_link VARCHAR)");
//            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Mobile Computing', 'https://ionicframework.com/')");
//            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Parallel Programming', 'https://docs.nvidia.com/cuda/')");
//            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Database Management', 'https://www.w3schools.com/sql/')");
//            sql_db.execSQL("INSERT INTO exams(name, study_link) VALUES ('Object Oriented Programming', 'https://www.w3schools.com/java/')");

            // Fetching from the db
            Cursor c = sql_db.rawQuery("SELECT * FROM exams", null);
            int exam_name_position = c.getColumnIndex("name");
            int study_link_position = c.getColumnIndex("study_link");
            c.moveToFirst();
            int i = 0;
            while (i < c.getCount()) {
                exam_names.add(c.getString(exam_name_position));
                study_links.add(c.getString(study_link_position));
                c.moveToNext();
                i++;
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, exam_names);
            list.setAdapter(adapter);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // On clicking on a certain course go to the study page along with the link to study
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), StudyActivity.class);
                intent.putExtra("study_link", study_links.get(i));
                startActivity(intent);
            }
        });

    }
}