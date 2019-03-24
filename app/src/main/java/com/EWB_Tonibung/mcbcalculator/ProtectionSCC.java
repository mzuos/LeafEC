package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

        String DispSCC_In_MCB = PVProtectionSet.getString("SCC_IN_MCB");
        String DispSCC_In_Cu = PVProtectionSet.getString("SCC_IN_CU");
        String DispSCC_In_Al = PVProtectionSet.getString("SCC_IN_AL");
        String DispSCC_Out_MCB = PVProtectionSet.getString("SCC_OUT_MCB");
        String DispSCC_Out_Cu = PVProtectionSet.getString("SCC_OUT_CU");
        String DispSCC_Out_Al = PVProtectionSet.getString("SCC_OUT_AL");

        //Capture the layout of the textviews and set the strings as their text

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
