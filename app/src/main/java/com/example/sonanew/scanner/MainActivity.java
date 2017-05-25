package com.example.sonanew.scanner;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button add, scan;
    private EditText barcode, count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcode = (EditText) findViewById(R.id.barcode);
        count = (EditText) findViewById(R.id.count);
        count.setFocusable(true);
        count.setFocusableInTouchMode(true);
        barcode.setFocusable(false);
        add = (Button) findViewById(R.id.add);
        scan = (Button) findViewById(R.id.scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("nlscan.action.SCANNER_RESULT");
                sendBroadcast(intent);
                intent = getIntent();
                barcode.setText(intent.getStringExtra("barcode"));
                count.requestFocus();
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
                Toast.makeText(MainActivity.this, "barv", Toast.LENGTH_SHORT).show();
                break;
            case KeyEvent.KEYCODE_F1:
                break;

        }
        return true;
    }
}
