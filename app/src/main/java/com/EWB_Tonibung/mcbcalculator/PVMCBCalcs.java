package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.Locale;

import static java.lang.Boolean.TRUE;

public class PVMCBCalcs extends AppCompatActivity {

    double Vpmax = 0, Ipmax = 0, Isc = 0, ImaxSCC = 0;
    int SystemV = 0, NumPV = 0,  n_series = 1, n_parallel;
    public double SCC_In_WireSize_Cu = 0, SCC_In_WireSize_Al = 0;
    public double SCC_Out_WireSize_Cu=0, SCC_Out_WireSize_Al=0;
    public int SCC_In_MCBSize = 0, SCC_Out_MCBSize = 0;
    double PV_In_Design_Current = 0;
    String SelectedType, ImaxSCC_srt;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pvmcbcalcs);
        //initialise array adapters
        ArrayAdapter adapter_SCCtype;

        //get the spinner from the xml.
        Spinner sp_SCCtype = findViewById(R.id.SCC_spinner);
        adapter_SCCtype = ArrayAdapter.createFromResource(this,R.array.SCC_type,R.layout.multiline_spinner_dropdown_item );

        //set the spinners adapter to the previously created one.
        sp_SCCtype.setAdapter(adapter_SCCtype);

        //Setting OnItemClickListener to the CABLE TYPE Spinner
        sp_SCCtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                SelectedType=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });
    }

    public void PVDataValidation(View view) {

        EditText editTextSystemV = (EditText) findViewById(R.id.Insert_SystemV);
        String SystemV_srt = editTextSystemV.getText().toString();
        if (SystemV_srt.isEmpty()) {
            SystemV = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            SystemV = Integer.parseInt(SystemV_srt);
        }


        EditText editTextVpmax = (EditText) findViewById(R.id.Insert_Vpmax);
        String Vpmax_srt = editTextVpmax.getText().toString();
        if (Vpmax_srt.isEmpty()) {
            Vpmax = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Vpmax = Float.parseFloat(Vpmax_srt);
        }


        EditText editTextIpmax = (EditText) findViewById(R.id.Insert_Ipmax);
        String Ipmax_srt = editTextIpmax.getText().toString();
        if (Ipmax_srt.isEmpty()) {
            Ipmax = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Ipmax = Float.parseFloat(Ipmax_srt);
        }

        EditText editTextIsc = (EditText) findViewById(R.id.Insert_Isc);
        String Isc_srt = editTextIsc.getText().toString();
        if (Isc_srt.isEmpty()) {
            Isc = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Isc = Float.parseFloat(Isc_srt);
        }

        EditText editTextISCC = (EditText) findViewById(R.id.Insert_ImaxSCC);
        ImaxSCC_srt = editTextISCC.getText().toString();
        if (ImaxSCC_srt.isEmpty()) {
            ImaxSCC = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            ImaxSCC = Float.parseFloat(ImaxSCC_srt);
        }

        EditText editTextNumPV = (EditText) findViewById(R.id.Insert_NumPV);
        String NumPV_srt = editTextNumPV.getText().toString();
        if (NumPV_srt.isEmpty()) {
            NumPV = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            NumPV = Integer.parseInt(NumPV_srt);
        }

        boolean dataOK = true;
        String PopUpText = "";

        if (Vpmax == 0.0 || Ipmax == 0.0 || ImaxSCC == 0.0 || Isc == 0.0 || SystemV == 0 || NumPV == 0) {
            dataOK = false;
            PopUpText = "Input field cannot be zero";
        }

        if (Vpmax_srt.isEmpty() || Ipmax_srt.isEmpty() || Isc_srt.isEmpty() || ImaxSCC_srt.isEmpty() || SystemV_srt.isEmpty() || NumPV_srt.isEmpty()) {
            dataOK = false;
            PopUpText = "Input field cannot be blank";
        }

        if (Ipmax > Isc) {
            dataOK = false;
            PopUpText = "Isc smaller than Ipmax. Review input data";
        }

        if (dataOK == false) {

            duration = Toast.LENGTH_SHORT;
            DisplayToast(PopUpText,duration);

        } else {
            PV_Calculate();

        }

    }

    public void PV_Calculate() {
        double PVSeriesV = 0;
        double SCC_In_C_SF = 1.56; // PV current input safety factor: 125% x 125% = 156%.
        boolean designOK = true;
        String Text;

        // Calculate maximum current from the PV panels, including safety factor

        while (TRUE) {
            PVSeriesV = Vpmax * n_series;
            if (PVSeriesV < SystemV) {
                n_series++;
            }
            else {
                break;
            }
        }
        // we know there are "n" panels in series, we can calculate the maximum combined PV array current

        n_parallel = NumPV / n_series; // n_parallel and n_series are integer values
        int dummy_check = n_parallel*n_series;

        if (dummy_check < NumPV){ // means the amount of panels does not match the series-parallel arrangement
            designOK = false;
            String Text1 = "Choose between " + (n_parallel*n_series) + " and " + ((n_parallel+1)*n_series) + " panels.";
            String Text2 = "\n(" + n_series + " panels in series per branch) ";
            Text = Text1 + Text2;
            duration = Toast.LENGTH_LONG;
            DisplayToast(Text, duration);
        }
        else{

            PV_In_Design_Current = SCC_In_C_SF * Isc * n_parallel; // (NumPV/n) gives the branches in parallel. This is the maximum short circuit current of all the branches

            if (PV_In_Design_Current > ImaxSCC) {
                // Pop UP: more than one Charge controller or reduce panels
                designOK = false;
                SCC_In_MCBSize = 0;
                int  MaxNumPanels = (int)(ImaxSCC/(SCC_In_C_SF * Isc))*n_series; //(SCC_In_C_SF * Isc) = current in each branch
                Text = "Current exceeds Charge Controller limit. Max num. of panels allowable: " + MaxNumPanels;
                duration = Toast.LENGTH_LONG;
                DisplayToast(Text, duration);
            }
        }
       if (designOK == true) {
            SCC_In_MCBSize = GeneralCalculations.DC_MCB_Calculator(PV_In_Design_Current);
            SCC_In_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize, 0);
            SCC_In_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize,1);

            // Solar Charger to Battery MCB and Cable Sizes:

            if (SelectedType=="PMW"){
                SCC_Out_MCBSize = SCC_In_MCBSize;
                SCC_Out_WireSize_Cu = SCC_In_WireSize_Cu;
                SCC_Out_WireSize_Al = SCC_In_WireSize_Al;
            }
            else { // =MPPT type controller
                SCC_Out_MCBSize = GeneralCalculations.DC_MCB_Calculator (ImaxSCC);
                SCC_Out_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize, 0);
                SCC_Out_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize,1);
            }

            DisplaySelectedProtection(); // Call the function that displays the data

        }

    }

    public void DisplayToast(String PopUpText, int duration){

            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();
            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(getResources().getColor(android.R.color.holo_blue_dark), PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 500);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(Color.WHITE);
            toast.show();
    }


    public void DisplaySelectedProtection() {

        // Save the MCB and cable size values as Strings

        String SrtSCC_Idesign = String.format(Locale.UK,"%.1f",PV_In_Design_Current); // Show 1 decimal only
        String Srt_n_series = Integer.toString(n_series);
        String Srt_n_parallel =  Integer.toString(n_parallel);

        String StrSCC_In_MCB =Integer.toString(SCC_In_MCBSize);
        String StrSCC_In_Cu = Double.toString(SCC_In_WireSize_Cu);
        String StrSCC_In_Al = Double.toString(SCC_In_WireSize_Al);

        String SrtSCC_Out_MCB = Integer.toString(SCC_Out_MCBSize);
        String SrtSCC_Out_Cu = Double.toString(SCC_Out_WireSize_Cu);
        String SrtSCC_Out_Al = Double.toString(SCC_Out_WireSize_Al);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle PV_Protection_Set = new Bundle ();

        PV_Protection_Set.putString("I_DESIGN", SrtSCC_Idesign);
        PV_Protection_Set.putString("SCC_MAX_A",ImaxSCC_srt );
        PV_Protection_Set.putString("NUM_SERIES",Srt_n_series);
        PV_Protection_Set.putString("NUM_PARALLEL", Srt_n_parallel);

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

