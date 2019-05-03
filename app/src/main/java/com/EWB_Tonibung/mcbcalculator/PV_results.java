package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PV_results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_results);

        //get the intent in the target activity
        Intent intentPVprotect = getIntent();

        //get the attached bundle from the intent
        Bundle PVProtectionSet = intentPVprotect.getExtras();

        //Extracting the stored data from the bundle
        //String user_name = extras.getString("USER_NAME");

        String DispSCC_Idesign = PVProtectionSet.getString("I_DESIGN") + " A (< " + PVProtectionSet.getString("SCC_MAX_A") + " A)";
        String Disp_PV_Series = PVProtectionSet.getString("NUM_SERIES");
        String Disp_PV_Parallel = PVProtectionSet.getString("NUM_PARALLEL");

        String DispSCC_V_In = PVProtectionSet.getString("V_DC_IN") + " V";
        String DispSCC_V_Out = PVProtectionSet.getString("V_DC_OUT") + " V";



        String DispSCC_In_MCB = PVProtectionSet.getString("SCC_IN_MCB");
        String DispSCC_In_Cu = PVProtectionSet.getString("SCC_IN_CU");
        String DispSCC_In_Al = PVProtectionSet.getString("SCC_IN_AL");

        String DispSCC_Out_MCB = PVProtectionSet.getString("SCC_OUT_MCB");
        String DispSCC_Out_Cu = PVProtectionSet.getString("SCC_OUT_CU");
        String DispSCC_Out_Al = PVProtectionSet.getString("SCC_OUT_AL");

        //Inputs to charge controller

        if (DispSCC_In_MCB.equals("-1")){
            DispSCC_In_MCB = "n/a";
        }
        else DispSCC_In_MCB = DispSCC_In_MCB + " A";


        if (DispSCC_In_Cu.equals ("-1.0")){
            DispSCC_In_Cu = "n/a";
        }
        else DispSCC_In_Cu = DispSCC_In_Cu + " sqmm";


        if (DispSCC_In_Al.equals ("-1.0")){
            DispSCC_In_Al = "n/a";
        }
        else DispSCC_In_Al = DispSCC_In_Al + " sqmm";

        //Outputs to charge controller

        if (DispSCC_Out_MCB.equals("-1")){
            DispSCC_Out_MCB = "n/a";
        }
        else DispSCC_Out_MCB = DispSCC_Out_MCB + " A";


        if (DispSCC_Out_Cu.equals ("-1.0")){
            DispSCC_Out_Cu = "n/a";
        }
        else DispSCC_Out_Cu = DispSCC_Out_Cu + " sqmm";

        if (DispSCC_Out_Al.equals ("-1.0")){
            DispSCC_Out_Al = "n/a";
        }
        else DispSCC_Out_Al = DispSCC_Out_Al + " sqmm";



        //Capture the layout of the textviews and set the strings as their text

        TextView TV_Idesign = findViewById (R.id.TV_Idesign);
        TV_Idesign.setText (DispSCC_Idesign);

        TextView n_series = findViewById(R.id.TV_n_series);
        n_series.setText(Disp_PV_Series);

        TextView n_parallel = findViewById(R.id.TV_n_parallel);
        n_parallel.setText(Disp_PV_Parallel);

        TextView TV_SCC_In_MCB = findViewById (R.id.Show_SCC_in_MCC);
        TV_SCC_In_MCB.setText (DispSCC_In_MCB);

        TextView TV_SCC_V_in = findViewById (R.id.TV_DC_Vrating_IN);
        TV_SCC_V_in.setText (DispSCC_V_In);

        TextView TV_SCC_In_Cu = findViewById (R.id.Show_SCC_In_Cu);
        TV_SCC_In_Cu.setText (DispSCC_In_Cu);

        TextView TV_SCC_In_Al = findViewById (R.id.Show_SCC_In_Al);
        TV_SCC_In_Al.setText (DispSCC_In_Al);

        TextView TV_SCC_Out_MCB = findViewById (R.id.Show_SCC_Out_MCB);
        TV_SCC_Out_MCB.setText (DispSCC_Out_MCB);

        TextView TV_SCC_V_out = findViewById (R.id.TV_DC_Vrating_OUT);
        TV_SCC_V_out.setText (DispSCC_V_Out);

        TextView TV_SCC_Out_Cu = findViewById (R.id.Show_SCC_Out_Cu);
        TV_SCC_Out_Cu.setText (DispSCC_Out_Cu);

        TextView TV_SCC_Out_Al = findViewById (R.id.Show_SCC_Out_Al);
        TV_SCC_Out_Al.setText (DispSCC_Out_Al);

    }
}
