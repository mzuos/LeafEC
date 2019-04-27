package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Inverter_input_data extends AppCompatActivity {

    int InverterV = 0; //to avoid it being zero
    double InvRating = 0, kWLimit=0;
    //kWLimit = 0;
    int Inv_DC_MCB_Size = 0, Inv_AC_MCB_Size = 0;
    int Num_DC=1, Num_AC=1; //Number or MCBs of each type in case one MCB is not big enough
    double Inv_DC_Wire_Size_Cu = 0, Inv_DC_Wire_Size_Al = 0;
    double Inv_AC_Wire_Size_Cu = 0, Inv_AC_Wire_Size_Al = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverter_input_data);
    }

    public void InverterDataValidation(View view) {

        boolean dataOK = true;
        CharSequence PopUpText = "";

        EditText editTextInvV = (EditText) findViewById(R.id.Insert_Vinverter);
        String InvVsrt = editTextInvV.getText().toString();
        if (InvVsrt.isEmpty()) {
            InverterV = 2;//just giving a random value that is not zero for Toasts to work properly
        } else {
            InverterV = Integer.parseInt(InvVsrt);
        }

        EditText editTextInvkW = (EditText) findViewById(R.id.Insert_InvRating);
        String InvkWsrt = editTextInvkW.getText().toString();
        if (InvkWsrt.isEmpty()) {
            InvRating = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            InvRating = Float.parseFloat(InvkWsrt);
        }


        EditText editTextkwLim = (EditText) findViewById(R.id.Insert_kWLimit);
        String kWLimSrt = editTextkwLim.getText().toString();
        if (!kWLimSrt.isEmpty()){
            kWLimit = Float.parseFloat(kWLimSrt);
            if (kWLimit > InvRating){
                dataOK = false;
                PopUpText = "Kampung Load cannot be higher than inverter output";
            }
            else if (kWLimit < InvRating/5){
                dataOK=false;
                PopUpText = "Kampung load is unusually low";
            }
        }


        if (InverterV == 0 || InvRating == 0.0) {
            dataOK = false;
            PopUpText = "Input field cannot be zero";
        }

        if (InvVsrt.isEmpty() || InvkWsrt.isEmpty()) {
            dataOK = false;
            PopUpText = "Input field cannot be blank";
        }

        if (dataOK == false) {

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();

            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(getResources().getColor(R.color.color_leaf_Toast), PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, -200);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(Color.WHITE);
            toast.show();

        } else {
            Calculate_Inverter();

        }
    }

    void Calculate_Inverter() {
        double Imax_DC = 0, Imax_AC = 0;

         // Size DC for full inverter capacity, AC for (optional) reduced load. If reduced overload, trip AC only, to keep inverter running.

        Imax_DC = InvRating / 0.9 / InverterV * 1.25; // 0.9 as inverter efficiency, 1.25 de-rating factor

        if (kWLimit!=0){
            Imax_AC = kWLimit / 230 * 1.25; // For now. Make the voltage User Defined
        }
        else{
            Imax_AC = InvRating / 230 * 1.25; // For now. Make the voltage User Defined
        }


        for (Num_DC=1;Num_DC<=10;Num_DC++){
            Inv_DC_MCB_Size = GeneralCalculations.DC_MCB_Calculator(Imax_DC);
            if (Inv_DC_MCB_Size==0){

                Imax_DC=Imax_DC/(Num_DC+1);

            }
            else {
                break;
            }
        }


        Inv_AC_MCB_Size = GeneralCalculations.AC_MCB_Calculator(Imax_AC);

        Inv_DC_Wire_Size_Cu = GeneralCalculations.CableSizeCalculator(Inv_DC_MCB_Size, 0);
        Inv_DC_Wire_Size_Al = GeneralCalculations.CableSizeCalculator(Inv_DC_MCB_Size, 2);

        Inv_AC_Wire_Size_Cu = GeneralCalculations.CableSizeCalculator(Inv_AC_MCB_Size, 0);
        Inv_AC_Wire_Size_Al = GeneralCalculations.CableSizeCalculator(Inv_AC_MCB_Size, 2);

        DisplayInverterProtection();

    }

    public void DisplayInverterProtection() {

        // Save the MCB and cable size values as Strings

        String Str_DC_MCB =Integer.toString(Inv_DC_MCB_Size);
        String Str_DC_Num = Integer.toString(Num_DC);
        String Str_DC_Cu = Double.toString(Inv_DC_Wire_Size_Cu);
        String Str_DC_Al = Double.toString(Inv_DC_Wire_Size_Al);
        String Str_AC_MCB = Integer.toString(Inv_AC_MCB_Size);
        String Str_AC_Cu = Double.toString(Inv_AC_Wire_Size_Cu);
        String Str_AC_Al = Double.toString(Inv_AC_Wire_Size_Al);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Inv_Protection_Set = new Bundle ();

        Inv_Protection_Set.putString("DC_MCB", Str_DC_MCB);
        Inv_Protection_Set.putString("DC_NUM", Str_DC_Num);
        Inv_Protection_Set.putString("DC_CU", Str_DC_Cu);
        Inv_Protection_Set.putString("DC_AL", Str_DC_Al);
        Inv_Protection_Set.putString("AC_MCB", Str_AC_MCB);
        Inv_Protection_Set.putString("AC_CU", Str_AC_Cu);
        Inv_Protection_Set.putString ("AC_AL", Str_AC_Al);

        // Create and initialise the Intent

        Intent intentINVprotect = new Intent(this, Inverter_results.class);

        //attach the bundle to the Intent object

        intentINVprotect.putExtras(Inv_Protection_Set);

        //start the activity

        startActivity(intentINVprotect);
    }
}
