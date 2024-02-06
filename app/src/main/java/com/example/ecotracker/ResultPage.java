package com.example.ecotracker;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultPage extends AppCompatActivity {

    double[] emissionFactors = { 0.52 , 0.179 , 0.3 , 2.31 + 2.69 ,0.11 , 0.08 ,0.5 };

    private CarbonCalculatorService carbonCalculatorService;
    private boolean isBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CarbonCalculatorService.CarbonCalculatorBinder binder = (CarbonCalculatorService.CarbonCalculatorBinder) service;
            carbonCalculatorService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        Intent intentService = new Intent(this, CarbonCalculatorService.class);
        bindService(intentService, serviceConnection, Context.BIND_AUTO_CREATE);

        Intent intent = getIntent();
        TextView textView = findViewById(R.id.Name);
        TextView totaltext = findViewById(R.id.total);

        String name = null;
        for (String it : DataPublic.userData.keySet()) {
            name = it;
        }
        textView.setText("Hello " + name);

        String[] topics = intent.getStringArrayExtra("topics");
        int[] allottedvalue = (int[]) intent.getSerializableExtra("allottedvalue");
        String[] topics2 = intent.getStringArrayExtra("topic2");
        double[] data = (double[]) intent.getSerializableExtra("data");


        int total = 0;
        if (topics != null) {

            total = 0;
            for (int i = 0; i < allottedvalue.length; i++) {
                total += allottedvalue[i];
            }
            totaltext.setText("Your Total Emission per year : " +total +"  Co2 Equivalent");
            DataPublic.userData.put(name, total);
            populateTable(topics, allottedvalue);
            Toast.makeText(this, total + "", Toast.LENGTH_SHORT).show();
        } else {
            double total1 = 0;
            for (int i = 0; i < data.length; i++) {
                total1 += (data[i] * emissionFactors[i]);
            }
            totaltext.setText("Your Total Emission per year : " +total1 +"  Co2 Equivalent" );
            DataPublic.userData.put(name, (int) total1);
            populateTable2(topics2, data, emissionFactors);
        }

        int totalValue = 0;
        if (isBound) {
            totalValue = carbonCalculatorService.calculateTotalValue(allottedvalue);
        }
        Toast.makeText(this, "Total Value: " + total, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Unbind from the service
        if (isBound) {
            unbindService(serviceConnection);
            isBound = false;
        }
    }
    private void populateTable(String[] topics, int[] allottedvalue) {
        TableLayout tableLayout = findViewById(R.id.result);

        for (int i = 0; i < topics.length; i++) {
            TableRow row = new TableRow(this);

            TextView topicText = new TextView(this);
            topicText.setText(topics[i]);
            topicText.setTextSize(16);
            topicText.setTextColor(getResources().getColor(R.color.black));
            topicText.setPadding(8, 8, 8, 8);
            row.addView(topicText);

            TextView footprintText = new TextView(this);
            footprintText.setText(String.valueOf(allottedvalue[i]));
            footprintText.setTextSize(18);
            footprintText.setTextColor(getResources().getColor(R.color.lighterGreen));
            footprintText.setPadding(8, 8, 8, 8);
            row.addView(footprintText);

            tableLayout.addView(row);
        }
    }
    private void populateTable2(String[] topics, double[] data, double[] co2equivalent) {
        TableLayout tableLayout = findViewById(R.id.result);

        for (int i = 0; i < topics.length; i++) {
            // Create a new TableRow
            TableRow row = new TableRow(this);

            // Topic column
            TextView topicText = new TextView(this);
            topicText.setText(topics[i]);
            topicText.setTextSize(16);
            topicText.setTextColor(getResources().getColor(R.color.black));
            topicText.setPadding(8, 8, 8, 8);
            row.addView(topicText);

            TextView dataText = new TextView(this);
            dataText.setText(String.valueOf( data[i]));
            dataText.setTextSize(16);
            dataText.setTextColor(getResources().getColor(R.color.green));
            dataText.setPadding(8, 8, 8, 8);
            row.addView(dataText);

            // Footprint values column
            TextView footprintText = new TextView(this);
            footprintText.setText(String.valueOf(co2equivalent[i]*data[i]));
            footprintText.setTextSize(18);
            footprintText.setTextColor(getResources().getColor(R.color.lighterGreen));
            footprintText.setPadding(8, 8, 8, 8);
            row.addView(footprintText);

            tableLayout.addView(row);
        }
    }
    public void openRankList(View view){
       Intent intent = new Intent(this, RankList.class);
       startActivity(intent);
    }
}
