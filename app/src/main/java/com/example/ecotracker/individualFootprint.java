package com.example.ecotracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class individualFootprint extends AppCompatActivity {

    int[] image =new int[7];

    int rotate=0;
    int MAX_SIZE=image.length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_footprint);
        image[0] = R.drawable.transport;
        image[1] = R.drawable.flying;
        image[2] = R.drawable.food;
        image[3] = R.drawable.shopping;
        image[4] = R.drawable.livingspace;
        image[5] = R.drawable.energyefficient;
        image[6] = R.drawable.heating;


    }
    String[] topic =new String[]{
                "Means of transport",
                "Flying",
                "Food",
                "Shopping, leisure, culture",
                "Living space",
                "Construction standard",
                "Heating system"};
    int[] allottedvalue = new int[topic.length];
    String[][] options = {
            {
                    " I walk or ride a bicycle, and I use public transport only occasionally.",
                    "I predominately use public transport (2/3) and sometimes walk or ride a bicycle (1/3).",
                    "I usually drive a car, but I also occasionally use public transport or walk.",
                    " I mainly drive a car."
            },

            {
                    " I never fly privately.",
                    "I fly privately once or twice a year within Europe.",
                    "I fly long haul privately about once a year (or about four times within Europe).",
                    "I fly long haul privately two or three times a year (or regularly)."
            },

            {
                    " I eat only vegan food.",
                    "I mostly eat vegetarian food.",
                    "I eat meat every other day on average.",
                    "I eat meat at almost every meal."
            },

            {
                    " I purchase new clothing, devices and furniture very rarely (these expenses are 60 euros a month). Expenses for leisure time, culture and health are below average.",
                    "I purchase half of my clothing, devices and furniture second-hand (these expenses are 170 euros a month). Expenses for leisure time, culture and health are slightly below average.",
                    "I occasionally purchase new clothing, devices and furniture (these expenses are 210 euros a month). Expenses for leisure time, culture and health are average (360 euros a month).",
                    "I frequently purchase new clothing, devices and furniture (these expenses are 420 euros a month). Expenses for leisure time, culture and health are slightly above average"
            },

            {
                    " I live in a small space (for example, a flat with several people).",
                    "I live in a large area (e.g. detached house)."
            },

            {
                    "I live in a renovated, energy-efficient house.",
                    "The house I inhabit is an older structure."
            },

            {
                    "Our home is heated using renewable energies (wood, heat pumps, etc.).",
                    "Our home is heated using fossil fuels (petroleum, natural gas, etc.)."
            }
    };
    public void SaveandNext(View view) {
        calculateCarbonFootprint();
        if (rotate < MAX_SIZE) {
            rotate++;
            updateActivity(rotate);
            updateOptions(rotate);
        }
        if (rotate == MAX_SIZE - 1) {
            Button btn1 = findViewById(R.id.next);
            btn1.setVisibility(View.GONE);

            Button btn = findViewById(R.id.resultPage);
            btn.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f

































            );
            btn.setLayoutParams(params);
        }
    }

    public void resultPage(View view) {
        calculateCarbonFootprint();

        Intent intent = new Intent(this, ResultPage.class);

        // Pass topic, options, and footprint values to ResultPage activity
        intent.putExtra("topics", topic);
        intent.putExtra("allottedvalue", allottedvalue);
        startActivity(intent);
    }


    public void Previous(View view){
        if(rotate>0)rotate--;
        updateActivity(rotate);
        updateOptions(rotate);
        if(rotate!=MAX_SIZE-1 ) {
            Button btn1 =findViewById(R.id.next);
            btn1.setVisibility(View.VISIBLE);
            Button btn =findViewById(R.id.resultPage);
            btn.setVisibility(View.GONE);
        }
    }

    void updateActivity(int rotate){
        ImageView image1= findViewById(R.id.image);
        image1.setImageResource(image[rotate]);
        TextView text =findViewById(R.id.topic);
        text.setText(topic[rotate]);
    }
    private void updateOptions(int questionNumber) {
        List<String> optionsList = Arrays.asList(options[questionNumber]);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.removeAllViews();
        for (int i = 0; i < optionsList.size(); i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);
            radioButton.setText(optionsList.get(i));
            radioButton.setTextSize(15 );
            radioGroup.addView(radioButton);
            radioButton.setPadding(10,10,10,10);
        }

//        calculateCarbonFootprint();
    }
    public int carbonFootprint = 0;
    int[][] footprintValues = {
            {0, 500, 1500, 3000},
            {0, 500, 1500, 3000},
            {0, 100, 500, 1000},
            {0, 100, 200, 300, 400},
            {0, 500},
            {0, 500},
            {0, 500}
    };



    int count=0;
    public void calculateCarbonFootprint() {
            RadioGroup radioGroup = findViewById(R.id.radioGroup);
            int selectedId = radioGroup.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selectedOption = findViewById(selectedId);
                if(selectedOption!=null) {
                    String optionText = selectedOption.getText().toString();
                    int[] optionIndex = find(optionText);

                    allottedvalue[count] = footprintValues[optionIndex[0]][optionIndex[1]];
                    carbonFootprint += footprintValues[optionIndex[0]][optionIndex[1]];
                }
                else{

                }
            }count++;
    }

    int[] find(String text){
        int[] result= new int[2];
        for(int j=0;j<options.length;j++){
            for(int k=0;k<options[j].length;k++) {
                if (options[j][k].equals(text)) {
                    result[0]=j;
                    result[1]=k;
                    return result;
                }
            }
        }
        return result;
    }

}
