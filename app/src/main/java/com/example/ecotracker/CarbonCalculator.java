package com.example.ecotracker;


import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class CarbonCalculator extends AppCompatActivity {

    BroadCast broadCast = new BroadCast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carbon_calculator);

        String content ="There are many benefits to tracking your carbon footprint. Perhaps the most obvious benefit is that it can help you to identify areas where you can make changes to reduce your impact on the environment.\n" +
                "\n" +
                "For example, if you discover that a significant portion of your carbon footprint comes from transportation, you can make changes such as carpooling or taking public transit more often.\n" +
                "\n" +
                "In addition, tracking your carbon footprint can also help you to set goals for reducing your impact. For instance, you may decide that you want to reduce your carbon footprint by 10% over a year. By setting and tracking progress towards specific goals, you ensure that you are making a real difference in the fight against climate change.\n" +
                "\n" +
                "Keeping tabs on your carbon footprint helps you make informed decisions about reducing your emissions by choosing energy-efficient products or supporting renewable energy sources.\n" +
                "\n" +
                "It also helps you learn ways to offset your emissions, such as planting trees or investing in carbon credits.\n" +
                "\n" +
                "By sharing your carbon footprint information with others, you can help to raise awareness about the importance of reducing our collective impact on the planet.";
        TextView text2=findViewById(R.id.text2);
        text2.setText(content);

        String name = null;
        Toast.makeText(this, DataPublic.userData+"", Toast.LENGTH_SHORT).show();
        for(String it:DataPublic.userData.keySet()){
            name =it;
        }
        name.toUpperCase();
        TextView text=findViewById(R.id.name);
        text.setText("Hey There Eco Warrior !!! ..."+name);

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadCast, filter);
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadCast);
    }


    public void openCarbonMenu(View view){
        Intent intent =new Intent(this,carbonMenu.class);
        startActivity(intent);
    }

}  