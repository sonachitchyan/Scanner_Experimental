package com.example.sonanew.scanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.list);
        Gson gson = new Gson();
        arrayList = gson.fromJson(getIntent().getStringExtra("info"),(Type) new Data());
        arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        listView.setAdapter(arrayAdapter);
    }
}
