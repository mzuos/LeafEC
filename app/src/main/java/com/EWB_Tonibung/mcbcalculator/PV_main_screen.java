package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class PV_main_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_main_screen);

        ImageView SolarSystem = (ImageView) findViewById(R.id.IV_SolarSystem) ;

        SolarSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(PV_main_screen.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.hybrid_solar);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
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
