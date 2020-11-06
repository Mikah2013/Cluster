package com.example.cluster;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Pie;

import java.util.ArrayList;
import java.util.List;

public class SettingsActivity extends AppCompatActivity {

    private List<Message> messageList;
    private Message mmMessage;
    private int paidSum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        AnyChartView mAnyChartView = findViewById(R.id.pie_chart);
        messageList = MessageLab.get(getApplicationContext()).getMessages();
        for (Message message : messageList) {
            mmMessage = message;
            Log.v("This is the sent list ", String.valueOf(message.getSentList()));
            Log.v("This withdrawal list ", String.valueOf(message.getWithdrawalList()));
            Log.v("This is the paid list ", String.valueOf(message.getPaidList()));
        }

        setUpPieChart();
    }

    private void setUpPieChart() {
        Pie mPie = AnyChart.pie();
        List<DataEntry> dataEntries = new ArrayList<>();


    }
}