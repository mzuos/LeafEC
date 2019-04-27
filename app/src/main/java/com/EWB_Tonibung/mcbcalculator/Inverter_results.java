package com.EWB_Tonibung.mcbcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Inverter_results extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverter_results);

        //get the intent in the target activity
        Intent intentINVprotect = getIntent();

        //get the attached bundle from the intent
        Bundle INVProtectionSet = intentINVprotect.getExtras();

        //Extracting the stored data from the bundle
        //String user_name = extras.getString("USER_NAME");


        String Disp_DC_MCB = INVProtectionSet.getString("DC_MCB");
        String Disp_DC_Num = INVProtectionSet.getString("DC_NUM");
        String Disp_DC_Cu = INVProtectionSet.getString("DC_CU");
        String Disp_DC_Al = INVProtectionSet.getString("DC_AL");
        String Disp_AC_MCB = INVProtectionSet.getString("AC_MCB");
        String Disp_AC_Cu = INVProtectionSet.getString("AC_CU");
        String Disp_AC_Al = INVProtectionSet.getString("AC_AL");


        //Capture the layout of the textviews and set the strings as their text

        TextView TView_DC_MCB = findViewById (R.id.TV_DC_MCB);
        TView_DC_MCB.setText(Disp_DC_MCB);
        TextView TView_DC_Num = findViewById (R.id.TV_DC_Num);
        TView_DC_Num.setText(Disp_DC_Num);
        TextView TView_DC_Cu = findViewById (R.id.TV_DC_Cu);
        TView_DC_Cu.setText(Disp_DC_Cu);
        TextView TView_DC_Al = findViewById (R.id.TV_DC_Al);
        TView_DC_Al.setText(Disp_DC_Al);

        TextView TView_AC_MCB = findViewById (R.id.TV_AC_MCB);
        TView_AC_MCB.setText(Disp_AC_MCB);
        TextView TView_AC_Cu = findViewById (R.id.TV_AC_Cu);
        TView_AC_Cu.setText(Disp_AC_Cu);
        TextView TView_AC_Al = findViewById (R.id.TV_AC_Al);
        TView_AC_Al.setText(Disp_AC_Al);


    }
}
