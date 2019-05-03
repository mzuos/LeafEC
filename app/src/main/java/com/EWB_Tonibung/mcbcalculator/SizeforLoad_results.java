package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        String VDDisplay = Bundle_Load.getString("VDDISPLAY");
        String VDrop = Bundle_Load.getString("VOLTDROP");
        String VDPercent = Bundle_Load.getString("VDPERCENT");
        String distance = Bundle_Load.getString("DISTANCE");
        String VD_req = Bundle_Load.getString ("VD_REQ");
        String VD_satisf = Bundle_Load.getString ("VD_SATISFIED");


        String LoadDescription = "Load: " + Watts + " Watt - " + LoadType + ". "+OvLoad+" overload factor";
        String DesignCurrent =  "Design current for MCB sizing: " + Amps + " A";

        String CableSelect_Str = "";



        if (MCBSize.equals ("n/a")){ // this is the correct way to compare strings
            MCBSize = "no MCB available";
            CableSelect_Str = "Cable size calculated for design load:";
        }
        else{
            MCBSize = MCBSize + " Amps";
            CableSelect_Str = "Minimum suggested cable size for that MCB is:";

        }

        if (CableSize.equals ("n/a")){
            CableSize = "no cable available for load";
        }
        else{
            CableSize = CableSize + " sqmm";
        }

        //Capture the layout of the textviews and set the strings as their text
        TextView TView_LoadDescription = (TextView)findViewById(R.id.TV_LoadDescription);
        TView_LoadDescription.setText (LoadDescription);

        TextView TView_DesignI = (TextView)findViewById(R.id.TV_DesignAmp);
        TView_DesignI.setText (DesignCurrent);

        TextView TView_MCBSize = (TextView)findViewById (R.id.TVLoad_MCBSize);
        TView_MCBSize.setText(MCBSize);

        TextView TView_CableSize = (TextView)findViewById (R.id.TVLoad_CableSize);

        TextView TView_Cabledescrip =(TextView) findViewById (R.id.TVLoad_CableSelection);

        TextView TView_VDResults = (TextView) findViewById(R.id.TV_VdropResults);




        if (VDDisplay.equals("YES")){

            String VoltDrop = "";

            if (VD_req.equals ("YES")){

                CableSelect_Str = "Min cable size for load and Volt-drop requirements:";

                if (VD_req.equals("YES")&& VD_satisf.equals ("NO")){
                    VoltDrop = "Unable to satisfy volt drop requirement." + "\nWith biggest cable size available of " + CableSize + ": " + "\n";
                    TView_CableSize.setText("n/a");

                }

                else{
                    TView_CableSize.setText (CableSize);
                }

            }

            else{

                TView_CableSize.setText (CableSize);
            }

            VoltDrop = VoltDrop + "Volt drop: " + VDrop + "V (= " + VDPercent + "%) over " + distance + " meters.";
            TView_VDResults.setVisibility(View.VISIBLE);
            TView_VDResults.setText(VoltDrop);

        }
        else{

            TView_CableSize.setText (CableSize);
            TView_VDResults.setVisibility(View.INVISIBLE);
        }

        TView_Cabledescrip.setText(CableSelect_Str);

    }
}
