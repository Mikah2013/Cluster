package com.example.cluster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
        readList();
    }

    public List<String> readSMS(){
        List<String> sms = new ArrayList<String>();
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms"), null,null, null, null);
        while (cursor.moveToNext()) {
            String address = cursor.getString(cursor.getColumnIndex("address"));
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            sms.add("Number: " + address + " .Message: " + body);
        }
        return sms;
    }
    private void readList() {
        List smsList = readSMS();
        String smslist = smsList.toString();
        TextView mReadTextView = findViewById(R.id.readTextView);
        mReadTextView.setText(smslist);
    }
}