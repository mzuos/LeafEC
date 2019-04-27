package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SizeCable_results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizecable_results);

        //get the intent in the target activity
        Intent intentCableForMCB = getIntent();

        //get the attached bundle from the intent
        Bundle BundleCableForMCB = intentCableForMCB.getExtras();

        //Extracting the stored data from the bundle

        String CableSize = BundleCableForMCB.getString("WIRE_SIZE");
        String CableRating = BundleCableForMCB.getString("WIRE_RATING");

        //Capture the layout of the textviews and set the strings as their text
        TextView TView_CableSize = findViewById (R.id.TV_CableSize);
        TView_CableSize.setText(CableSize);

        TextView TView_CableRating = findViewById (R.id.TV_CableRating);
        TView_CableRating.setText(CableRating);
    }
}
