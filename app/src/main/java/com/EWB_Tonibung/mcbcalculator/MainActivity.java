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
    public void LaunchSolarPVScreen(View view) {
        Intent intentSolarPVScreen = new Intent(this, PV_main_screen.class);
        startActivity(intentSolarPVScreen);
    }

    //Launched by selecting Main Menu Option 2
    public void LaunchGeneralCalcScreen(View view) {
        Intent intentMainCalcScreen = new Intent(this, GeneralCalcs_screen.class);
        startActivity(intentMainCalcScreen);
    }

    //Launched by selecting Main Menu Option 3
    public void LaunchResourcesScreen(View view) {
        Intent intentResourcesScreen = new Intent(this, Resources_screen.class);
        startActivity(intentResourcesScreen);
    }









}





