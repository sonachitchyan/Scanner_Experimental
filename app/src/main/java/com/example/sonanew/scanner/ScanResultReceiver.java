package com.example.sonanew.scanner;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class ScanResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String bar = intent.getStringExtra("SCAN_BARCODE1");
        String scanStatus = intent.getStringExtra("SCAN_STATE");
        Intent intent1 = new Intent(context, MainActivity.class);
        if (scanStatus.equals("ok")) {
            intent1.putExtra("barcode", bar);
            context.startActivity(intent1);
        } else {
            Toast.makeText(context, "Try again", Toast.LENGTH_SHORT).show();
        }
    }
}
