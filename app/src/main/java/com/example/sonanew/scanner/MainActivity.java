package com.example.sonanew.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText barcode, count;
    private List<Data> list = new ArrayList<>();
    private BroadcastReceiver scanner = null;
    private TextView name_text, count_text, value_text, context_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcode = (EditText) findViewById(R.id.barcode);
        count = (EditText) findViewById(R.id.count);
        name_text = (TextView) findViewById(R.id.name_text);
        count_text = (TextView) findViewById(R.id.count_text);
        value_text = (TextView) findViewById(R.id.value_text);
        context_db = (TextView) findViewById(R.id.count_db_text);
        scanner = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String bar = intent.getStringExtra("SCAN_BARCODE1");
                String scanStatus = intent.getStringExtra("SCAN_STATE");
                if ("ok".equals(scanStatus)) {
                    barcode.setText(bar);
                } else {
                    Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("nlscan.action.SCANNER_RESULT");
        this.registerReceiver(scanner, intentFilter);
        count.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //read from db and show in text
                }
                return true;
            }
        });


    }
    public void onRadioButtonChecked(View view){
        boolean c = ((RadioButton)view).isChecked();
        if (c){
            switch (view.getId()){
                case R.id.radio_article:
                    barcode.setHint("Article");
                    barcode.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case R.id.radio_code:
                    barcode.setHint("Code");
                    barcode.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case R.id.radio_barcode:
                    barcode.setHint("Barcode");
                    barcode.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
            }
        }
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        switch (keyCode) {
//            case KeyEvent.KEYCODE_ENTER:
//                if (!barcode.getText().toString().equals("") && !count.getText().toString().equals("")) {
//                    Data data = new Data(barcode.getText().toString(), Integer.parseInt(count.getText().toString()));
//                    list.add(data);
//                    Gson gson = new Gson();
//                    String info = gson.toJson(list);
//                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                    intent.putExtra("info", info);
//                    startActivity(intent);
//                }
//                break;
//        }
//        return true;
//    }
}
