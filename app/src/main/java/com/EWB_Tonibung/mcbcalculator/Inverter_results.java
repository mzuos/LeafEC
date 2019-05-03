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
        String Disp_DC_V_rating = INVProtectionSet.getString("DC_V_RATING") + " V";
        String Disp_DC_Cu = INVProtectionSet.getString("DC_CU");
        String Disp_DC_Al = INVProtectionSet.getString("DC_AL");
        String Disp_AC_MCB = INVProtectionSet.getString("AC_MCB");
        String Disp_AC_Cu = INVProtectionSet.getString("AC_CU");
        String Disp_AC_Al = INVProtectionSet.getString("AC_AL");
        String AC_type = INVProtectionSet.getString("AC_TYPE");


        //DC INPUT TO INVERTER

        if (Disp_DC_MCB.equals("-1")){
            Disp_DC_MCB = "n/a";
        }
        else Disp_DC_MCB = Disp_DC_MCB + " A";


        if (Disp_DC_Cu.equals ("-1.0")){
            Disp_DC_Cu = "n/a";
        }
        else Disp_DC_Cu = Disp_DC_Cu + " sqmm";


        if (Disp_DC_Al.equals ("-1.0")){
            Disp_DC_Al = "n/a";
        }
        else Disp_DC_Al = Disp_DC_Al + " sqmm";


        //AC INPUT TO INVERTER

        if (Disp_AC_MCB.equals("-1")){
            Disp_AC_MCB = "n/a";
        }
        else Disp_AC_MCB = Disp_AC_MCB + " A";


        if (Disp_AC_Cu.equals ("-1.0")){
            Disp_AC_Cu = "n/a";
        }
        else Disp_AC_Cu = Disp_AC_Cu + " sqmm";


        if (Disp_AC_Al.equals ("-1.0")){
            Disp_AC_Al = "n/a";
        }
        else Disp_AC_Al = Disp_AC_Al + " sqmm";


        // DISP AC MCB TYPE:

        String Disp_AC_type;

        if (AC_type.equals("Single phase")){
            Disp_AC_type = "Single phase output: 2 pole MCB (or 1P&N) or 2 fuses";
        }
        else if (AC_type.equals("Three phase")){
            Disp_AC_type = "Three phase output: 4 pole MCB (or 3P&N) or 4 fuses";
        }
        else{
            Disp_AC_type = "This is bollocks";
        }



        //Capture the layout of the textviews and set the strings as their text

        TextView TView_DC_MCB = (TextView)findViewById (R.id.TV_DC_MCB);
        TView_DC_MCB.setText(Disp_DC_MCB);
        TextView TView_DC_Num = (TextView)findViewById (R.id.TV_DC_Num);
        TView_DC_Num.setText(Disp_DC_Num);
        TextView TView_DC_V_Rating = (TextView) findViewById(R.id.TV_V_DC) ;
        TView_DC_V_Rating.setText(Disp_DC_V_rating);
        TextView TView_DC_Cu = (TextView)findViewById (R.id.TV_DC_Cu);
        TView_DC_Cu.setText(Disp_DC_Cu);
        TextView TView_DC_Al = (TextView)findViewById (R.id.TV_DC_Al);
        TView_DC_Al.setText(Disp_DC_Al);

        TextView TView_AC_type = (TextView) findViewById(R.id.TV_MCB_type);
        TView_AC_type.setText (Disp_AC_type);
        TextView TView_AC_MCB = (TextView)findViewById (R.id.TV_AC_MCB);
        TView_AC_MCB.setText(Disp_AC_MCB);
        TextView TView_AC_Cu = (TextView)findViewById (R.id.TV_AC_Cu);
        TView_AC_Cu.setText(Disp_AC_Cu);
        TextView TView_AC_Al = (TextView)findViewById (R.id.TV_AC_Al);
        TView_AC_Al.setText(Disp_AC_Al);


    }
}
