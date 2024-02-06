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
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IndustryFootprint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_industry_footprint);
        for(int i=0;i<fragmentTitle.length;i++){
            data[i]=0;
        }
    }

    String[] fragmentTitle =new String[7];
    String[] button1 =new String[7];
    String[] button2 =new String[7];
    int[] image =new int[7];
    static double Usage;

    int rotate=0;
    int MAX_SIZE=fragmentTitle.length;
    double[] data=new double[MAX_SIZE];
    String[] topic =new String[]{
            "Electricity use",
            "Natural gas use",
            "Water supply",
            " Water treatment",
            "Fuel used",
            "Employee passenger travel",
            "Waste disposal/recycling"};

    public void SaveandNext(View view){
        EditText userinput= findViewById(R.id.input);
        Usage = Integer.parseInt(userinput.getText().toString());
        if(rotate<MAX_SIZE){
            data[rotate]=Usage;
            rotate++;
            updateActivity(rotate);
        }
        if(rotate==MAX_SIZE-1 ) {
            Button btn1 =findViewById(R.id.next);
            btn1.setVisibility(View.GONE);
            Button btn =findViewById(R.id.resultPage);
            btn.setVisibility(View.VISIBLE);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    1f
            );
            btn.setLayoutParams(params);
        }

    }
    public void resultPage(View view){
        EditText userinput= findViewById(R.id.input);
        Usage = Integer.parseInt(userinput.getText().toString());
        data[rotate]=Usage;

        Intent intent =new Intent(this,ResultPage.class);
        intent.putExtra("topic2",topic);
        intent.putExtra("data",data);

        startActivity(intent);
    }


    public void Previous(View view){
        if(rotate>0)rotate--;
        updateActivity(rotate);
        if(rotate!=MAX_SIZE-1 ) {
            Button btn1 =findViewById(R.id.next);
            btn1.setVisibility(View.VISIBLE);
            Button btn =findViewById(R.id.resultPage);
            btn.setVisibility(View.GONE);
        }
    }
    void updateActivity(int rotate){
        fragmentTitle[0]="Enter Electricity use: Total kilowatt-hours used from electricity bills.";
        fragmentTitle[1]="Enter Natural gas use: Total kilowatt-hours used from gas bills.";
        fragmentTitle[2]="Enter Water supply: Total water supplied in cubic metres from water bill.";
        fragmentTitle[3]="Enter Water used in Water treatment: Total water treated in cubic metres from water bill.";
        fragmentTitle[4]="Enter Fuel used in company-owned vehicles: Litres of fuel purchased from invoices and receipts (more accurate) or vehicle mileage from vehicle logbooks/odometers (less accurate).";
        fragmentTitle[5]="Enter Employee passenger travel distance: Employee receipts for details of travel and distance calculation websites to obtain flight, rail, and road distances.";
        fragmentTitle[6]="Enter Amount of Waste disposal/recycling: Tonnes of waste-to-landfill and recycled from waste collection provider.";


        image[0] = R.drawable.electricity;
        image[1] = R.drawable.naturalgas;
        image[2] = R.drawable.waterusage;
        image[3] = R.drawable.watertreatment;
        image[4] = R.drawable.vehicles;
        image[5] = R.drawable.employeetransport;
        image[6] = R.drawable.wastedisposal;

        button1[0]="Kwhs";
        button1[1]="Kwhs";
        button1[2]="Cubic-meter";
        button1[3]="Cubic-meter";
        button1[4]="Petrol";
        button1[5]="Petrol";
        button1[6]="Kg";

        button2[0]="Watt-hrs";
        button2[1]="tonne";
        button2[2]="Litre";
        button2[3]="Litre";
        button2[4]="Diesel";
        button2[5]="Diesel";
        button2[6]="Tonne";

        TextView fragmentTitle1 = findViewById(R.id.fragmentTitle);
        fragmentTitle1.setText(fragmentTitle[rotate]);

        ImageView image1= findViewById(R.id.image);
        image1.setImageResource(image[rotate]);

        RadioButton button_temp = findViewById(R.id.radiobtn1);
        button_temp.setText(button1[rotate]);

        RadioButton button_temp2 = findViewById(R.id.radiobtn2);
        button_temp2.setText(button2[rotate]);
    }

}