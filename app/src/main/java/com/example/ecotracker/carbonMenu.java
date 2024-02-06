package com.example.ecotracker;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;



import java.time.Instant;

public class carbonMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_menu);
    }

    public void open_individual_activity(View view){
        Intent intent = new Intent(this, individualFootprint.class);
        startActivity(intent);
    }

    public void open_industry_activity(View view){
        Intent intent = new Intent(this, IndustryFootprint.class);
        startActivity(intent);
    }
}
