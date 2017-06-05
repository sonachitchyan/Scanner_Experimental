package com.example.sonanew.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.util.TimeUnit;
import android.os.AsyncTask;
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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    private EditText barcode, count;
    private Button clear;
   // private List<Data> list = new ArrayList<>();
    private BroadcastReceiver scanner = null;
    private TextView name_text, count_text, value_text, context_db;
    private String [] hints = {"Barcode"};
    private RadioGroup radioGroup;
    private RadioButton radio_barcode, radio_code, radio_article;

    Realm realm = Realm.getInstance(new RealmConfiguration.Builder(
            getApplicationContext()).name("myrealm.realm").build());


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
        radioGroup = (RadioGroup) findViewById(R.id.group);
        radio_article = (RadioButton) findViewById(R.id.radio_article);
        radio_barcode = (RadioButton) findViewById(R.id.radio_barcode);
        radio_code = (RadioButton) findViewById(R.id.radio_code);
        radio_barcode.setChecked(true);
        clear = (Button) findViewById(R.id.clear);






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
                    JsonReader jsonReader = new JsonReader();
                    jsonReader.execute();
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
                    hints[0] = "Article";
                    barcode.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
                case R.id.radio_code:
                    barcode.setHint("Code");
                    hints[0] = "Code";
                    barcode.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
                case R.id.radio_barcode:
                    barcode.setHint("Barcode");
                    hints[0] = "Barcode";
                    barcode.setInputType(InputType.TYPE_CLASS_NUMBER);
                    break;
            }
        }
    }

    class JsonReader extends AsyncTask<Void, Void, Void>{
    @Override
    protected Void doInBackground(Void... params) {
        try {
            java.util.concurrent.TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        String bar = barcode.getText().toString();
        String hint = hints[0];
        switch(hint){
            case "Article":

                break;
            case "Code":
                break;
            case "Barcode":
                if(!bar.equals("") && count.getText().toString().equals("")){
                    int c = Integer.parseInt(count.getText().toString());
                    realm.beginTransaction();
                    Data data = realm.createObject(Data.class);
                    data.setBarcode(bar);
                    data.setCount(c);
                    data.setArticle(0);
                    realm.commitTransaction();
               // Data data = new Data(bar,Integer.parseInt(count.getText().toString()) );
                    Gson gson = new Gson();
                    try {
                        FileWriter fileWriter = new FileWriter("myjson.json");
                       // gson.toJson(data, fileWriter);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                break;
        }
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}
}
