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

    //Launched by selecting Main Menu Option 1
    public void LaunchPVInput(View view) {
        Intent intentPVInput = new Intent(this, PV_input_data.class);
        startActivity(intentPVInput);
    }

    //Launched by selecting Main Menu Option 2
    public void LaunchInverterInput(View view) {
        Intent intentInverterInput = new Intent(this, Inverter_input_data.class);
        startActivity(intentInverterInput);
    }

    //Launched by selecting Main Menu Option 3
    public void LaunchCableforMCB(View view) {
        Intent intentCableforMCB = new Intent(this, SizeCable_input_data.class);
        startActivity(intentCableforMCB);
    }

    //Launched by selecting Main Menu Option 4
    public void LaunchMCBforCable(View view) {
        Intent intentMCBforCable = new Intent (this, SizeMCB_input_data.class);
        startActivity(intentMCBforCable);
    }

    //Launched by selecting Main Menu Option 5
    public void LaunchDesignForLoad(View view) {
        Intent intent_Load = new Intent (this, SizeforLoad_input_data.class);
        startActivity(intent_Load);
    }


}





