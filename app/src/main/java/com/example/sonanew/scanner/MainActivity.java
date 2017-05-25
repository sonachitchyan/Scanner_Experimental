package com.example.sonanew.scanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button add, scan;
    private EditText barcode, count;
    private List<Data> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcode = (EditText) findViewById(R.id.barcode);
        count = (EditText) findViewById(R.id.count);
        count.setFocusable(true);
        count.setFocusableInTouchMode(true);
        barcode.setFocusable(false);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.line);
        linearLayout.requestFocus();
        add = (Button) findViewById(R.id.add);
        scan = (Button) findViewById(R.id.scan);
        read_barcode();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                if (!barcode.getText().toString().equals("") && !count.getText().toString().equals("")) {
                    Data data = new Data(barcode.getText().toString(), Integer.parseInt(count.getText().toString()));
                    list.add(data);
                    Gson gson = new Gson();
                    String info = gson.toJson(list);
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("info", info);
                    startActivity(intent);
                }
                break;
        }
        return true;
    }

    public void read_barcode() {
        Intent intent = new Intent("nlscan.action.SCANNER_RESULT");
        sendBroadcast(intent);
        intent = getIntent();
        barcode.setText(intent.getStringExtra("barcode"));
        if (!barcode.getText().toString().equals("")) {
            count.requestFocus();
        }
    }
}
