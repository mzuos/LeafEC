package com.EWB_Tonibung.mcbcalculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    //Launched by selecting Main Menu Option 2a
    public void LaunchCableforMCB(View view) {
        Intent intentCableforMCB = new Intent(this, SizeCable_input_data.class);
        startActivity(intentCableforMCB);
    }

    //Launched by selecting Main Menu Option 2b
    public void LaunchMCBforCable(View view) {
        Intent intentMCBforCable = new Intent (this, SizeMCB_input_data.class);
        startActivity(intentMCBforCable);
    }

    //Launched by selecting Main Menu Option 2c
    public void LaunchDesignForLoad(View view) {
        Intent intent_Load = new Intent (this, SizeforLoad_input_data.class);
        startActivity(intent_Load);
    }

    //Launched by selecting Main Menu Option 3b
    public void LaunchCableRatings(View view) {
        Intent intent_ratings = new Intent (this, CableRatings.class);
        startActivity(intent_ratings);
    }

    //Launched from Main Menu
    public void LaunchAWGSizeViewer(View view) {
        Intent intent_AWG = new Intent (this, AWG_sizelist.class);
        startActivity(intent_AWG);
    }




}





