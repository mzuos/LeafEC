package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GeneralCalcs_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_calcs_screen);
    }

    public void LaunchSizeMCB(View view) {
        Intent intent_sizeMCB = new Intent (this,SizeMCB_input_data.class);
        startActivity(intent_sizeMCB);
    }

    public void LaunchSizeCable(View view) {
        Intent intent_sizeCable = new Intent (this,SizeCable_input_data.class);
        startActivity(intent_sizeCable);
    }

    public void LaunchSizeforLoad(View view) {
        Intent intent_sizeforload = new Intent (this,SizeforLoad_input_data.class);
        startActivity(intent_sizeforload);
    }

}
