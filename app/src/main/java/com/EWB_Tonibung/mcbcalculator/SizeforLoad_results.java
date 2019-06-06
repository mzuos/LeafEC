package com.EWB_Tonibung.mcbcalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
        String CableforLoad = Bundle_Load.getString("WIRE_4_LOAD") + " sqmm";
        String Cable4Load_rating = "Cable rating: " + Bundle_Load.get("RATING_4_LOAD") + " A";
        String LoadType = Bundle_Load.getString("LOAD_TYPE");
        String Voltage = Bundle_Load.getString("VOLTAGE");
        String Watts = Bundle_Load.getString("POWER");
        String Amps = Bundle_Load.getString("AMPS");
        String OvLoad = Bundle_Load.getString("OVERLOAD");

        String VDDisplay = Bundle_Load.getString("VDDISPLAY");
        String VDrop = Bundle_Load.getString("VOLTDROP");
        String VDPercent = Bundle_Load.getString("VDPERCENT");
        String VD_max_PC = Bundle_Load.getString("MAX_VD_PC");
        String distance = Bundle_Load.getString("DISTANCE");
        String VD_req = Bundle_Load.getString ("VD_REQ");
        String VD_satisf = Bundle_Load.getString ("VD_SATISFIED");

        int size_array = Bundle_Load.getInt ("ARRAY_SIZE");
        int wire_position = Bundle_Load.getInt ("CHOSEN_WIRE_POSITION");

        String [] mm2_array = Bundle_Load.getStringArray("SQMM_ARRAY");
        String [] Ohm_array = Bundle_Load.getStringArray("OHMS_ARRAY");
        String [] VD_array = Bundle_Load.getStringArray("VD_ARRAY");
        String [] VDPC_array = Bundle_Load.getStringArray("VDPC_ARRAY");

        ArrayList<String> mm2_list = new ArrayList<String>();
        ArrayList<String> Ohm_list = new ArrayList<String>();
        ArrayList<String> VD_list = new ArrayList<String>();
        ArrayList<String> VDPC_list = new ArrayList<String>();



        for (int i = 0; i < size_array; i++){

            mm2_list.add (mm2_array[i]);
            Ohm_list.add (Ohm_array[i]);
            VD_list.add (VD_array[i]);
            VDPC_list.add (VDPC_array[i]);
        }

        String LoadDescription = "Load: " + Watts + " Watt - " + LoadType + ". "+OvLoad+" overload factor";
        String DesignCurrent =  "Design current for MCB sizing: " + Amps + " A";

        String CableSelect_Str = "";



        if (MCBSize.equals ("n/a")){ // this is the correct way to compare strings
            MCBSize = "no MCB available";
            CableSelect_Str = "Cable size calculated for design load:";
        }
        else{
            MCBSize = MCBSize + " Amps";
            CableSelect_Str = "Minimum suggested cable size for that MCB is:\n     (" + Cable4Load_rating +")";

        }

        double Chosen_size = 0;


        if (CableSize.equals ("n/a")){
            CableSize = "no cable available for load";
            VDDisplay = "NO";
        }
        else{
            Chosen_size = Float.parseFloat(CableSize);
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

        //List view and titles

        TextView TView_lv_sqmm = (TextView) findViewById (R.id.TV_lv_sqmm);
        TextView TView_lv_VD = (TextView) findViewById (R.id.TV_lv_VD);
        TextView TView_lv_VDPC = (TextView) findViewById (R.id.TV_lv_VDPC);

        ListView VD_lview = (ListView) findViewById(R.id.VD_ListView);

        // Set listview size based on number or rows (size_array)

        int pixels = size_array * 36; //height of each individual array

        DisplayMetrics displaymetrics;

        displaymetrics = new DisplayMetrics();

        Activity thisactivity;

        thisactivity = SizeforLoad_results.this;

        thisactivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        float density = getResources().getDisplayMetrics().density;

        int size_dp = (int) Math.round (density) * pixels;

        VD_lview.getLayoutParams().height = size_dp;

        /***********************/

        if (VDDisplay.equals("YES")){

            String VoltDrop = "";


            if (VD_req.equals ("YES")){

                //Display voltdrop listview and relevant headings
                TView_lv_sqmm.setVisibility(View.VISIBLE);
                TView_lv_VD.setVisibility(View.VISIBLE);
                TView_lv_VDPC.setVisibility(View.VISIBLE);
                VD_lview.setVisibility(View.VISIBLE);

                //CALL TO THE LIST VIEW ADAPTER:

                if (VD_satisf.equals("NO")){
                    Chosen_size = 100000; // ridiculous large value, otherwise final cable will show GREEN
                }

                // Call list view
                listview_adapter_array adapter = new listview_adapter_array(this, Chosen_size, mm2_list, VD_list, VDPC_list);
                VD_lview.setAdapter(adapter);

               /* // Set list view focus to selected cable (not needed anymore)

                if (wire_position > 1){
                    VD_lview.setSelection(wire_position-2);
                } */


                /*VD_lview.setOnTouchListener(new View.OnTouchListener() {

                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_MOVE) {
                            return true; // Indicates that this has been handled by you and will not be forwarded further.
                        }
                        return false;
                    }
                });*/


                //CableSelect_Str = "Min cable size for load and Volt-drop requirements:";

                CableSelect_Str = "Min cable size for load is: "+ CableforLoad + ".\n     (" + Cable4Load_rating + ")\nMin cable size for load and Volt-drop requirements:\n";

                if (VD_req.equals("YES")&& VD_satisf.equals ("NO")){
                    VoltDrop = "Unable to satisfy volt drop requirement." + "\nWith biggest cable size available of " + CableSize + ": " + "\n";
                    TView_CableSize.setText("n/a");

                }

                else{
                    TView_CableSize.setText (CableSize);
                    VoltDrop = "";
                }

                VoltDrop = VoltDrop + "Volt drop: " + VDrop + "V (= " + VDPercent + "% < " + VD_max_PC +"% ) over " + Voltage + " V and " + distance + " meters.";
                TView_VDResults.setVisibility(View.VISIBLE);
                TView_VDResults.setText(VoltDrop);

            }

            else{

                TView_CableSize.setText (CableSize);

                //Hide listview and titles
                TView_lv_sqmm.setVisibility(View.INVISIBLE);
                TView_lv_VD.setVisibility(View.INVISIBLE);
                TView_lv_VDPC.setVisibility(View.INVISIBLE);
                VD_lview.setVisibility(View.INVISIBLE);

                VoltDrop = "Volt drop: " + VDrop + "V (= " + VDPercent + "%) over " + Voltage + " V and " + distance + " meters.";
                TView_VDResults.setVisibility(View.VISIBLE);
                TView_VDResults.setText(VoltDrop);

            }


        }
        else{

            TView_CableSize.setText (CableSize);
            TView_VDResults.setVisibility(View.INVISIBLE);
            TView_lv_sqmm.setVisibility(View.INVISIBLE);
            TView_lv_VD.setVisibility(View.INVISIBLE);
            TView_lv_VDPC.setVisibility(View.INVISIBLE);
            VD_lview.setVisibility(View.INVISIBLE);

        }

        TView_Cabledescrip.setText(CableSelect_Str);

    }


}
