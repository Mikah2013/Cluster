package com.example.cluster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MessageDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MSG_ID = "messageId";
    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView mAddressText = findViewById(R.id.textDetailViewAddress);
        TextView mMessageText = findViewById(R.id.textDetailViewMessage);
        TextView mDateText = findViewById(R.id.textDetailViewDate);

        Intent intent = getIntent();
        String messageId = intent.getStringExtra(EXTRA_MSG_ID);
        if (messageId != null) {
            message = MessageLab.get(getApplicationContext()).getMessage(messageId);
        }
        mAddressText.setText(message.getAddress());
        mMessageText.setText(message.getBody());
        mDateText.setText(message.getDate());
    }
}