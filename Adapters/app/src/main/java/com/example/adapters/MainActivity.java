package com.example.adapters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    //data source
    String[] worldcup = {
            "germany",
            "spain",
            "japan",
            "argentina"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);

        //adapter
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.adapterlist, R.id.textView, worldcup);
        listView.setAdapter(adapter);

    }
}