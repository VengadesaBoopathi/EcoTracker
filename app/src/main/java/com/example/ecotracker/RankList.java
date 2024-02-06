package com.example.ecotracker;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RankList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_list);

        Toast.makeText(this, DataPublic.userData+"", Toast.LENGTH_SHORT).show();
        Map<String, Integer> sortedUsageMap = sortByValues(DataPublic.userData);
        Toast.makeText(this, sortedUsageMap+"", Toast.LENGTH_SHORT).show();
        updateTable(sortedUsageMap);
    }


    private Map<String, Integer> sortByValues(Map<String, Integer> map) {
        Map<String, Integer> sortedMap = new TreeMap<>((o1, o2) -> {
            Integer value1 = map.get(o1);
            Integer value2 = map.get(o2);

            if (value1 == null) {
                value1 = 0;
            }

            if (value2 == null) {
                value2 = 0;
            }

            // Compare values first
            int result = value1.compareTo(value2);

            // If values are equal, compare keys (names)
            if (result == 0) {
                return o1.compareTo(o2);
            }

            return result;
        });

        sortedMap.putAll(map);
        return sortedMap;
    }

    private void updateTable(Map<String, Integer> sortedUsageMap) {
        TableLayout tableLayout = findViewById(R.id.tableLayout);
        int rank = 1;

        for (String entry : sortedUsageMap.keySet()) {
            String name = entry;
            Integer usage = sortedUsageMap.get(name);

            TableRow row = new TableRow(this);

            TextView rankTextView = new TextView(this);
            rankTextView.setText(String.valueOf(rank));
            rankTextView.setTextSize(16);
            rankTextView.setTextColor(getResources().getColor(R.color.lightGreen));
            rankTextView.setPadding(8, 8, 8, 8);
            row.addView(rankTextView);
            rank++;

            if (name != null) {
                TextView nameTextView = new TextView(this);
                nameTextView.setText(name);
                nameTextView.setTextSize(16);
                nameTextView.setTextColor(getResources().getColor(R.color.white));
                nameTextView.setPadding(8, 8, 8, 8);
                row.addView(nameTextView);
            }

            if (usage != null) {
                TextView usageTextView = new TextView(this);
                usageTextView.setText(String.valueOf(usage));
                usageTextView.setTextSize(16);
                usageTextView.setTextColor(getResources().getColor(R.color.white));
                usageTextView.setPadding(8, 8, 8, 8);
                row.addView(usageTextView);
            }

            // Adding the row to the table
            tableLayout.addView(row);
        }
    }

}
