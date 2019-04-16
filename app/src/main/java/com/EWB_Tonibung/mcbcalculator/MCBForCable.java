package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MCBForCable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcbfor_cable);

        //get the intent in the target activity
        Intent intentMCBforCable = getIntent();

        //get the attached bundle from the intent
        Bundle BundleMCBforCable = intentMCBforCable.getExtras();

        //Extracting the stored data from the bundle

        String MCBSize = BundleMCBforCable.getString("MCB_SIZE");
        String CableRating = BundleMCBforCable.getString("WIRE_RATING");

        //Capture the layout of the textviews and set the strings as their text
        TextView TView_MCBSize = findViewById (R.id.TV_MCBSize);
        TView_MCBSize.setText(MCBSize);

        TextView TView_Rating= findViewById (R.id.TV_CableRating);
        TView_Rating.setText(CableRating);
    }
}
