package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.Locale;

import static java.lang.Boolean.TRUE;

public class PV_input_data extends AppCompatActivity {

    double PV_Voc = 0, Panel_Watts = 0, Isc = 0, ImaxSCC = 0;
    int SystemV = 0, NumPV = 0,  n_series = 1, n_parallel;
    int V_dc_rating_in = 0, V_dc_rating_out = 0;
    public double SCC_In_WireSize_Cu = 0, SCC_In_WireSize_Al = 0;
    public double SCC_Out_WireSize_Cu=0, SCC_Out_WireSize_Al=0;
    public int SCC_In_MCBSize = 0, SCC_Out_MCBSize = 0;
    double PV_In_Design_Current, MPPT_Vmax = 0;
    double SCC_Out_Current;
    String ControllerType, ImaxSCC_srt, Panel_Watts_srt;
    int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pv_input_data);
        //initialise array adapters
        ArrayAdapter adapter_SCCtype;

        //get the spinner from the xml.
        Spinner sp_SCCtype = findViewById(R.id.SCC_spinner);
        adapter_SCCtype = ArrayAdapter.createFromResource(this,R.array.SCC_type,R.layout.custom_spinner_dropdown);

        //set the spinners adapter to the previously created one.
        sp_SCCtype.setAdapter(adapter_SCCtype);

        //Setting OnItemClickListener to the SCC type Spinner
        sp_SCCtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EditText ED_SCC_Vmax = (EditText) findViewById(R.id.ED_SCC_Vmax);

                ControllerType = (String) parent.getItemAtPosition(position);
                if (position == 0){ //PWM
                    ED_SCC_Vmax.setEnabled (false);
                    ED_SCC_Vmax.setHint("n/a");
                }
                else{
                    ED_SCC_Vmax.setEnabled (true);
                    ED_SCC_Vmax.setHint("0");
                }
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


        EditText editTextVpmax = (EditText) findViewById(R.id.Insert_Voc);
        String Vpmax_srt = editTextVpmax.getText().toString();
        if (Vpmax_srt.isEmpty()) {
            PV_Voc = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            PV_Voc = Float.parseFloat(Vpmax_srt);
        }


        EditText editTextPanelWatts = (EditText) findViewById(R.id.Insert_PV_Watts);
        Panel_Watts_srt = editTextPanelWatts .getText().toString();
        if (Panel_Watts_srt.isEmpty()) {
            Panel_Watts = 0.1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Panel_Watts = Float.parseFloat(Panel_Watts_srt);
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

        EditText ED_MPPT_Vmax = (EditText) findViewById(R.id.ED_SCC_Vmax);

        String MTTP_Vmax_str = ED_MPPT_Vmax.getText().toString();

        if (ControllerType.equals("MPPT")){

            if(MTTP_Vmax_str.isEmpty()){
                MPPT_Vmax = 1; //just giving a random value that is not zero for Toasts to work properly
            }
            else{
                MPPT_Vmax = Float.parseFloat(MTTP_Vmax_str);
            }
        }
        else{
            MTTP_Vmax_str = "-1";
            MPPT_Vmax = -1 ; //Random non-zero value, controller is PWM, we don't need to know Vmax
        }


        EditText editTextNumPV = (EditText) findViewById(R.id.Insert_NumPV);
        String NumPV_srt = editTextNumPV.getText().toString();
        if (NumPV_srt.isEmpty()) {
            NumPV = 1;//just giving a random value that is not zero for Toasts to work properly
        }
        else {
            NumPV = Integer.parseInt(NumPV_srt);
        }

        boolean dataOK = true;
        String PopUpText = "";

        if (PV_Voc == 0.0 || Panel_Watts == 0.0 || ImaxSCC == 0.0 || Isc == 0.0 || SystemV == 0 || NumPV == 0 || MPPT_Vmax == 0) {
            dataOK = false;
            PopUpText = "Input field cannot be zero";
        }

        if (PV_Voc > 1000 || Panel_Watts > 2000 || ImaxSCC > 1000 || Isc > 1000 || SystemV > 1000 || NumPV >1000 || MPPT_Vmax > 1000) {
            dataOK = false;
            PopUpText = "At least one of the input values is too large";
        }

        if (Vpmax_srt.isEmpty() || Panel_Watts_srt.isEmpty() || Isc_srt.isEmpty() || ImaxSCC_srt.isEmpty() || SystemV_srt.isEmpty() || NumPV_srt.isEmpty() || MTTP_Vmax_str.isEmpty()) {
            dataOK = false;
            PopUpText = "Input field cannot be blank";
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
        int MaxNumPanels = 0;
        double V_design_in = 0, V_design_out;
        String Text;

        SCC_Out_Current = 0;
        PV_In_Design_Current = 0;

        // Calculate number of panels in series. Depends on systemV and controller type.

        if (ControllerType.equals("PWM")){

            while (TRUE) {
                PVSeriesV = PV_Voc * n_series;
                if (PVSeriesV < SystemV) { //Series voltage has to be higher than SystemV
                    n_series++;
                }
                else {
                    break;
                }
            }


            n_parallel = NumPV / n_series;  // n_parallel and n_series are integer values

            PV_In_Design_Current = SCC_In_C_SF * Isc * n_parallel;

            SCC_Out_Current = PV_In_Design_Current;

            // Calculate maximum current from the PV panels, including safety factor
            //MaxNumPanels is an integer: Controller max current/current per branch

            MaxNumPanels = (int)(ImaxSCC/(SCC_In_C_SF * Isc))*n_series; //(SCC_In_C_SF * Isc) = current in each branch

        }
        else { // it's a MPPT type

            /*while (TRUE){

                PVSeriesV = PV_Voc * n_series;
                if (PVSeriesV <= MPPT_Vmax / 1.25) { //Safety factor 1.25 or 1.56??
                    n_series++;
                }
                else {
                    break;
                }
            }*/

            double dummy = MPPT_Vmax/PV_Voc/1.25;
            n_series = (int)dummy;

            PVSeriesV = n_series * PV_Voc;


            n_parallel = NumPV / n_series;  // n_parallel and n_series are integer values

            PV_In_Design_Current = SCC_In_C_SF * Isc * n_parallel;

            SCC_Out_Current = NumPV * Panel_Watts/ SystemV;   // Because MPPT controllers are rated at DC out current

            // Calculate maximum current from the PV panels, including safety factor
            //MaxNumPanels is an integer: Controller max current/current per branch

            MaxNumPanels = (int)(ImaxSCC * SystemV / Panel_Watts); //(SCC_In_C_SF * Isc) = current in each branch

            MaxNumPanels = ((int) MaxNumPanels / n_series) *n_series;

        }

        int dummy_check = n_parallel*n_series;

        //Check if number of panels exceeds charge controller current rating

        if (SCC_Out_Current > ImaxSCC) {

            designOK = false;
            SCC_In_MCBSize = 0;
            Text = "Current exceeds Charge Controller limit. Maximum number of panels allowable per controller: " + MaxNumPanels;
            duration = Toast.LENGTH_LONG;
            DisplayToast(Text, duration);
        }

        else {

            if (dummy_check < NumPV){ // means the amount of panels does not match the series-parallel arrangement
                designOK = false;

                String Text1 = "Charge controller requires " + n_series + " panels in series per branch.";
                String Text2 = "\nExample: " + (((MaxNumPanels/n_series) - 1) * n_series) + " or " + (MaxNumPanels) + " panels.";

                Text = Text1 + Text2;
                duration = Toast.LENGTH_LONG;
                DisplayToast(Text, duration);
            }

            /*else {

                PV_In_Design_Current = SCC_In_C_SF * Isc * n_parallel;
            }*/

        }

        V_design_in = PV_Voc * n_series * 1.25; // 1.25 factor for sunnier conditions than standard test conditions;

        V_design_out = SystemV * 1.25;

        V_dc_rating_in = GeneralCalculations.DC_V_Rating_Calculator(V_design_in);
        V_dc_rating_out = GeneralCalculations.DC_V_Rating_Calculator(V_design_out);

        // we know there are "n" panels in series, we can calculate the maximum combined PV array current

        // The MCB and wire sizing is per Charge Controller. There may be more than one charge controller.
       if (designOK == true) {
            SCC_In_MCBSize = GeneralCalculations.DC_MCB_Calculator(PV_In_Design_Current);

           if (SCC_In_MCBSize == -1){// means we wer unable to find a suitable MCB

               SCC_In_WireSize_Cu = -1;
               SCC_In_WireSize_Al = -1;
           }

           else { // means we have a suitable MCB

               SCC_In_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize, 0);
               SCC_In_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_In_MCBSize,2);

           }



            // Solar Charger to Battery MCB and Cable Sizes:

            if (ControllerType.equals("PWM")){// PWM type. Input and output currents are the same
                SCC_Out_MCBSize = SCC_In_MCBSize;
                SCC_Out_WireSize_Cu = SCC_In_WireSize_Cu;
                SCC_Out_WireSize_Al = SCC_In_WireSize_Al;
            }
            else { // =MPPT type controller - match controller output * 1.25
                double Idesign_MPPT = ImaxSCC *1.25; // continuous use de-rating
                SCC_Out_MCBSize = GeneralCalculations.DC_MCB_Calculator (Idesign_MPPT);

                if (SCC_Out_MCBSize == -1){// means we wer unable to find a suitable MCB

                    SCC_Out_WireSize_Cu = -1;
                    SCC_Out_WireSize_Al = -1;

                }
                else { // means we have a suitable MCB

                    SCC_Out_WireSize_Cu = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize, 0);
                    SCC_Out_WireSize_Al = GeneralCalculations.CableSizeCalculator (SCC_Out_MCBSize,2);
                }

            }

            DisplaySelectedProtection(); // Call the function that displays the data

        }

    }

    public void DisplayToast(String PopUpText, int duration){

            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();
            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(getResources().getColor(R.color.color_leaf_StatusBar), PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 500);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(Color.WHITE);
            toast.show();
    }


    public void DisplaySelectedProtection() {

        // Save the MCB and cable size values as Strings

        String SrtSCC_Idesign_In = String.format(Locale.UK,"%.1f",PV_In_Design_Current); // Show 1 decimal only
        String StrSCC_In_MCB, StrSCC_In_Cu, StrSCC_In_Al, StrSCC_Out_MCB, StrSCC_Out_Cu, StrSCC_Out_Al;

        //Input to charge controller
        String StrSCC_Idesign_Out = String.format(Locale.UK,"%.1f",SCC_Out_Current);
        String StrSCC_In_MCB_Vrating = Integer.toString (V_dc_rating_in);
        StrSCC_In_MCB = Integer.toString(SCC_In_MCBSize);
        StrSCC_In_Cu = Double.toString(SCC_In_WireSize_Cu);
        StrSCC_In_Al = Double.toString(SCC_In_WireSize_Al);


        //Output to charge controller
        String StrSCC_Out_MCB_Vrating = Integer.toString (V_dc_rating_out);
        StrSCC_Out_MCB = Integer.toString(SCC_Out_MCBSize);
        StrSCC_Out_Cu = Double.toString(SCC_Out_WireSize_Cu);
        StrSCC_Out_Al = Double.toString(SCC_Out_WireSize_Al);


        //Create a Bundle object and add key value pairs to the bundle.

        Bundle PV_Protection_Set = new Bundle ();

        PV_Protection_Set.putString("I_DESIGN_IN", SrtSCC_Idesign_In);
        PV_Protection_Set.putString("SCC_MAX_A",ImaxSCC_srt );
        PV_Protection_Set.putDouble("PANEL_SIZE", Panel_Watts);
        PV_Protection_Set.putInt("NUM_SERIES",n_series);
        PV_Protection_Set.putInt("NUM_PARALLEL", n_parallel);
        PV_Protection_Set.putString("SCC_TYPE", ControllerType);

        PV_Protection_Set.putString("SCC_IN_MCB", StrSCC_In_MCB);
        PV_Protection_Set.putString("V_DC_IN", StrSCC_In_MCB_Vrating);
        PV_Protection_Set.putString("SCC_IN_CU", StrSCC_In_Cu);
        PV_Protection_Set.putString("SCC_IN_AL", StrSCC_In_Al);

        PV_Protection_Set.putString("I_DESIGN_OUT", StrSCC_Idesign_Out);
        PV_Protection_Set.putString("SCC_OUT_MCB", StrSCC_Out_MCB);
        PV_Protection_Set.putString("V_DC_OUT", StrSCC_Out_MCB_Vrating);
        PV_Protection_Set.putString("SCC_OUT_CU", StrSCC_Out_Cu);
        PV_Protection_Set.putString ("SCC_OUT_AL", StrSCC_Out_Al);

        // Create and initialise the Intent

        Intent intentPVprotect = new Intent(this, PV_results.class);

        //attach the bundle to the Intent object

        intentPVprotect.putExtras(PV_Protection_Set);

        //start the activity

        startActivity(intentPVprotect);
    }
}

