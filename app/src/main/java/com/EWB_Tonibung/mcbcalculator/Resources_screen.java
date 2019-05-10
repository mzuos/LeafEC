package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Resources_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_screen);
    }

    public void LaunchCableRatings(View view) {
        Intent intent_ratings = new Intent (this, CableRatings.class);
        startActivity(intent_ratings);
    }

    public void LaunchAWGSizeViewer(View view) {
        Intent intent_AWG = new Intent (this, AWG_mm2_converter.class);
        startActivity(intent_AWG);
    }

    public void LaunchImageViewer(View view) {
        //Intent intent_image = new Intent (this, Image_viewer.class);
        Intent intent_image = new Intent (this, Image_gallery.class);
        startActivity(intent_image);
    }
}
