package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SizeMCB_results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizemcb_results);

        //get the intent in the target activity
        Intent intentMCBforCable = getIntent();

        //get the attached bundle from the intent
        Bundle BundleMCBforCable = intentMCBforCable.getExtras();

        //Extracting the stored data from the bundle

        String MCBSize = BundleMCBforCable.getString("MCB_SIZE");
        String CableRating = BundleMCBforCable.getString("WIRE_RATING");
        CableRating = "Current rating of the cable: " + CableRating + " A";

        Boolean LargestMCB = BundleMCBforCable.getBoolean("FINAL_MCB");

        String Str_LargestMCB = "";

        if (LargestMCB == true){
            Str_LargestMCB = "- Largest MCB available in catalogue -";

        }

        //Capture the layout of the textviews and set the strings as their text
        TextView TView_MCBSize = findViewById (R.id.TV_MCBSize);
        TView_MCBSize.setText(MCBSize);

        TextView TView_Rating= findViewById (R.id.TV_SizeMCB_CableRating);
        TView_Rating.setText(CableRating);

        TextView TView_largest = (TextView) findViewById (R.id.TV_largestMCB);
        TView_largest.setText(Str_LargestMCB);
    }
}
