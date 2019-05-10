package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

public class PV_main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_main_screen);
    }

    //Launched by selecting Main Menu Option 1a
    public void LaunchPVInput(View view) {
        Intent intentPVInput = new Intent(this, PV_input_data.class);
        startActivity(intentPVInput);
    }

    //Launched by selecting Main Menu Option 1b
    public void LaunchInverterInput(View view) {
        Intent intentInverterInput = new Intent(this, Inverter_input_data.class);
        startActivity(intentInverterInput);
    }

}
