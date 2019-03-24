package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class PVMCBCalcs extends AppCompatActivity {

    double Vpmax = 0, Ipmax = 0, Isc = 0, ImaxSCC = 0;
    int SystemV = 0, NumPV = 0;
    public double SCC_In_WireSize_Cu = 0, SCC_In_WireSize_Al = 0;
    public double SCC_Out_WireSize_Cu=0, SCC_Out_WireSize_Al=0;
    public int SCC_In_MCBSize = 0, SCC_Out_MCBSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvmcbcalcs);
    }

    public void PVDataValidation(View view) {
        int ToastXOffset = 280;

        EditText editTextSystemV = (EditText) findViewById(R.id.Insert_SystemV);
        String SystemVsrt = editTextSystemV.getText().toString();
        if (SystemVsrt.isEmpty()) {
            SystemV = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            SystemV = Integer.parseInt(SystemVsrt);
        }


        EditText editTextVpmax = (EditText) findViewById(R.id.Insert_Vpmax);
        String Vpmaxsrt = editTextVpmax.getText().toString();
        if (Vpmaxsrt.isEmpty()) {
            Vpmax = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Vpmax = Float.parseFloat(Vpmaxsrt);
        }


        EditText editTextIpmax = (EditText) findViewById(R.id.Insert_Ipmax);
        String Ipmaxsrt = editTextIpmax.getText().toString();
        if (Ipmaxsrt.isEmpty()) {
            Ipmax = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Ipmax = Float.parseFloat(Ipmaxsrt);
        }

        EditText editTextIsc = (EditText) findViewById(R.id.Insert_Isc);
        String Iscsrt = editTextIsc.getText().toString();
        if (Iscsrt.isEmpty()) {
            Isc = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Isc = Float.parseFloat(Iscsrt);
        }

        EditText editTextISCC = (EditText) findViewById(R.id.Insert_ImaxSCC);
        String ISCCsrt = editTextISCC.getText().toString();
        if (ISCCsrt.isEmpty()) {
            ImaxSCC = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            ImaxSCC = Float.parseFloat(ISCCsrt);
        }

        EditText editTextNumPV = (EditText) findViewById(R.id.Insert_NumPV);
        String NumPVsrt = editTextNumPV.getText().toString();
        if (NumPVsrt.isEmpty()) {
            NumPV = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            NumPV = Integer.parseInt(NumPVsrt);
        }

        boolean dataOK = true;
        CharSequence PopUpText = "";

        if (Vpmax == 0.0 || Ipmax == 0.0 || ImaxSCC == 0.0 || Isc == 0.0 || SystemV == 0 || NumPV == 0) {
            dataOK = false;
            PopUpText = "Input field cannot be zero";
        }

        if (Vpmaxsrt.isEmpty() || Ipmaxsrt.isEmpty() || Iscsrt.isEmpty() || ISCCsrt.isEmpty() || SystemVsrt.isEmpty() || NumPVsrt.isEmpty()) {
            dataOK = false;
            PopUpText = "Input field cannot be blank";
        }

        if (Ipmax > Isc) {
            dataOK = false;
            ToastXOffset = 150;
            PopUpText = "Isc smaller than Ipmax. Review input data";
        }

        if (dataOK == false) {
            // dataOK=true;
            Context context = getApplicationContext();
            //CharSequence PopUpText = "Review your input data";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();
            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(72 - 61 - 139, PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, ToastXOffset, 400);
            toast.show();
        } else {
            PV_Calculate();

        }

    }

    public void PV_Calculate() {
        double PVSeriesV = 0;
        double MaxPV_In_Current = 0;
        double SCC_In_C_SF = 1.56; // PV current input safety factor: 125% x 125% = 156%.
        int n = 1;

        // Calculate maximum current from the PV panels, including safety factor

        while (TRUE) {
            PVSeriesV = Vpmax * n;
            if (PVSeriesV < SystemV) {
                n++;
            }
            else {
                break;
            }
        }
        // we know there are "n" panels in series, we can calculate the maximum combined PV array current

        MaxPV_In_Current = SCC_In_C_SF * Isc * NumPV / n; // NumPV/n gives the branches in series. This is the maximum short circuit current off all the branches

        if (MaxPV_In_Current > ImaxSCC) {
            // Pop UP: more than one Charge controller or reduce panels
            SCC_In_MCBSize = 131;
        }
        else{
            SCC_In_MCBSize = GeneralCalculations.DC_MCB_Calculator(MaxPV_In_Current);

        }

        SCC_In_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize, 0);
        SCC_In_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize,1);

        // Solar Charger to Battery MCB and Cable Sizes:

        SCC_Out_MCBSize = GeneralCalculations.DC_MCB_Calculator (ImaxSCC);

        SCC_Out_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize, 0);
        SCC_Out_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize,1);

        DisplaySelectedProtection(); // Call the function that displays the data

    }


    public void DisplaySelectedProtection() {

        // Save the MCB and cable size values as Strings

        String StrSCC_In_MCB =Integer.toString(SCC_In_MCBSize);
        String StrSCC_In_Cu = Double.toString(SCC_In_WireSize_Cu);
        String StrSCC_In_Al = Double.toString(SCC_In_WireSize_Al);
        String SrtSCC_Out_MCB = Integer.toString(SCC_Out_MCBSize);
        String SrtSCC_Out_Cu = Double.toString(SCC_Out_WireSize_Cu);
        String SrtSCC_Out_Al = Double.toString(SCC_Out_WireSize_Al);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle PV_Protection_Set = new Bundle ();

        PV_Protection_Set.putString("SCC_IN_MCB", StrSCC_In_MCB);
        PV_Protection_Set.putString("SCC_IN_CU", StrSCC_In_Cu);
        PV_Protection_Set.putString("SCC_IN_AL", StrSCC_In_Al);
        PV_Protection_Set.putString("SCC_OUT_MCB", SrtSCC_Out_MCB);
        PV_Protection_Set.putString("SCC_OUT_CU", SrtSCC_Out_Cu);
        PV_Protection_Set.putString ("SCC_OUT_AL", SrtSCC_Out_Al);

        // Create and initialise the Intent

        Intent intentPVprotect = new Intent(this, ProtectionSCC.class);

        //attach the bundle to the Intent object

        intentPVprotect.putExtras(PV_Protection_Set);

        //start the activity

        startActivity(intentPVprotect);
    }
}

