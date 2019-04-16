package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Locale;

public class ProtectionSCC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protection_scc);

        //get the intent in the target activity
        Intent intentPVprotect = getIntent();

        //get the attached bundle from the intent
        Bundle PVProtectionSet = intentPVprotect.getExtras();

        //Extracting the stored data from the bundle
        //String user_name = extras.getString("USER_NAME");

        String DispSCC_Idesign = PVProtectionSet.getString("I_DESIGN") + " A (< " + PVProtectionSet.getString("SCC_MAX_A") + "A)";
        String Disp_PV_Series = PVProtectionSet.getString("NUM_SERIES");
        String Disp_PV_Parallel = PVProtectionSet.getString("NUM_PARALLEL");

        String DispSCC_In_MCB = PVProtectionSet.getString("SCC_IN_MCB")+ " A";
        String DispSCC_In_Cu = PVProtectionSet.getString("SCC_IN_CU")+ " sqmm";
        String DispSCC_In_Al = PVProtectionSet.getString("SCC_IN_AL")+ " sqmm";

        String DispSCC_Out_MCB = PVProtectionSet.getString("SCC_OUT_MCB")+ " A";
        String DispSCC_Out_Cu = PVProtectionSet.getString("SCC_OUT_CU")+ " sqmm";
        String DispSCC_Out_Al = PVProtectionSet.getString("SCC_OUT_AL")+" sqmm";

        //Capture the layout of the textviews and set the strings as their text

        TextView TV_Idesign = findViewById (R.id.TV_Idesign);
        TV_Idesign.setText (DispSCC_Idesign);

        TextView n_series = findViewById(R.id.TV_n_series);
        n_series.setText(Disp_PV_Series);

        TextView n_parallel = findViewById(R.id.TV_n_parallel);
        n_parallel.setText(Disp_PV_Parallel);

        TextView TV_SCC_In_MCB = findViewById (R.id.Show_SCC_in_MCC);
        TV_SCC_In_MCB.setText (DispSCC_In_MCB);

        TextView TV_SCC_In_Cu = findViewById (R.id.Show_SCC_In_Cu);
        TV_SCC_In_Cu.setText (DispSCC_In_Cu);

        TextView TV_SCC_In_Al = findViewById (R.id.Show_SCC_In_Al);
        TV_SCC_In_Al.setText (DispSCC_In_Al);

        TextView TV_SCC_Out_MCB = findViewById (R.id.Show_SCC_Out_MCB);
        TV_SCC_Out_MCB.setText (DispSCC_Out_MCB);

        TextView TV_SCC_Out_Cu = findViewById (R.id.Show_SCC_Out_Cu);
        TV_SCC_Out_Cu.setText (DispSCC_Out_Cu);

        TextView TV_SCC_Out_Al = findViewById (R.id.Show_SCC_Out_Al);
        TV_SCC_Out_Al.setText (DispSCC_Out_Al);

    }
}
