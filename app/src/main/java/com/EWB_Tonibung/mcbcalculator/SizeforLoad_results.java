package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SizeforLoad_results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizeforload_results);

        //get the intent in the target activity
        Intent Intent_Load = getIntent();

        //get the attached bundle from the intent
        Bundle Bundle_Load = Intent_Load.getExtras();

        //Extracting the stored data from the bundle //String user_name = extras.getString("USER_NAME");

        String MCBSize = Bundle_Load.getString("MCB_SIZE");
        String CableSize = Bundle_Load.getString("WIRE_SIZE");
        String LoadType = Bundle_Load.getString("LOAD_TYPE");
        String Watts = Bundle_Load.getString("POWER");
        String Amps = Bundle_Load.getString("AMPS");
        String OvLoad = Bundle_Load.getString("OVERLOAD");

        String LoadDescription = "Load: " + Watts + " Watt - " + LoadType + ". "+OvLoad+" overload factor";
        String DesignCurrent =  "Design current: " + Amps + "A";
        if (MCBSize.equals ("n/a")){ // this is the correct way to compare strings
            MCBSize = "no MCB available";
            CableSize = "n/a";
        }
        else{
            MCBSize = MCBSize + " Amps";
            CableSize = CableSize + " sqmm";
        }
        //Capture the layout of the textviews and set the strings as their text
        TextView TView_LoadDescription = findViewById(R.id.TV_LoadDescription);
        TView_LoadDescription.setText (LoadDescription);
        TextView TView_DesignI = findViewById(R.id.TV_DesignAmp);
        TView_DesignI.setText (DesignCurrent);
        TextView TView_MCBSize = findViewById (R.id.TVLoad_MCBSize);
        TView_MCBSize.setText(MCBSize);
        TextView TView_CableSize = findViewById (R.id.TVLoad_CableSize);
        TView_CableSize.setText(CableSize);
    }
}
