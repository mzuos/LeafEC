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


        String MCBRating = BundleCableForMCB.getString("MCB_SIZE");
        MCBRating = "Minimum suggested cable size for " + MCBRating + "A MCB:";

        String CableSize = BundleCableForMCB.getString("WIRE_SIZE");
        String CableRating = BundleCableForMCB.getString("WIRE_RATING") + " A";
        CableRating = "Current Rating of the cable for continous use: " + CableRating;


        if (CableSize.equals ("-1.0")){

            CableSize = "n/a";
            CableRating  = "No cable of this type available for MCB rating";
        }


        //Capture the layout of the textviews and set the strings as their text
        TextView TView_MCBdescription = findViewById(R.id.TV_cable_descrip);
        TView_MCBdescription.setText (MCBRating);

        TextView TView_CableSize = findViewById (R.id.TV_CableSize);
        TView_CableSize.setText(CableSize);

        TextView TView_CableRating = findViewById (R.id.TV_CableRating);
        TView_CableRating.setText(CableRating);
    }
}
