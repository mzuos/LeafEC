package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Inverter_input_data extends AppCompatActivity {

    int InverterV = 0; //to avoid it being zero
    double InvRating = 0, inv_eff=0, V1ph=0, V3ph=0;
    static double de_rating = 1.25;
    //kWLimit = 0;
    int Inv_DC_MCB_Size = 0, Inv_AC_MCB_Size = 0, DC_MCB_V_rating;
    int Num_DC=1, Num_AC=1; //Number or MCBs of each type in case one MCB is not big enough
    double Inv_DC_Wire_Size_Cu = 0, Inv_DC_Wire_Size_Al = 0;
    double Inv_AC_Wire_Size_Cu = 0, Inv_AC_Wire_Size_Al = 0;
    String ACtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inverter_input_data);

        //initialise array adapters
        ArrayAdapter adapter_ACtype;

        //get the spinner from the xml.
        Spinner sp_ACtype = findViewById(R.id.Inv_spinner);
        adapter_ACtype = ArrayAdapter.createFromResource(this,R.array.AC_output_type,R.layout.multiline_spinner_dropdown_item );

        //set the spinners adapter to the previously created one.
        sp_ACtype.setAdapter(adapter_ACtype);

        //Setting OnItemClickListener to the CABLE TYPE Spinner
        sp_ACtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    ACtype = "Single phase";
                }
                else {
                    ACtype = "Three phase";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

        /*final EditText Ed_V1ph = (EditText) findViewById(R.id.ED_Inv_1ph);
        final EditText Ed_V3ph = (EditText) findViewById(R.id.ED_Inv_3ph);

        Ed_V1ph.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            Adjust_V_3ph();
        });

        Ed_V3ph.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                Adjust_V_1ph();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });*/

    }

    /*public void Adjust_V_3ph(){
        EditText Ed_V1ph = (EditText) findViewById(R.id.ED_Inv_1ph);
        EditText Ed_V3ph = (EditText) findViewById(R.id.ED_Inv_3ph);
        String Str_V1ph = Ed_V1ph.getText().toString();
        if (!Str_V1ph.isEmpty()){

            V1ph = Float.parseFloat(Str_V1ph);
            V3ph = V1ph*1.73;
            String Str_V3ph = String.format (Locale.UK, "%.0f", V3ph);
            Ed_V3ph.setText(Str_V3ph);

        }

    }

    public void Adjust_V_1ph(){
        EditText Ed_V1ph = (EditText) findViewById(R.id.ED_Inv_1ph);
        EditText Ed_V3ph = (EditText) findViewById(R.id.ED_Inv_3ph);
        String Str_V3ph = Ed_V3ph.getText().toString();
        if (!Str_V3ph.isEmpty()){

            V3ph = Float.parseFloat(Str_V3ph);
            V1ph = V1ph/1.73;
            String Str_V1ph = String.format (Locale.UK, "%.0f", V1ph);
            Ed_V1ph.setText(Str_V3ph);

        }
    }*/

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

        EditText Ed_V1ph = (EditText) findViewById(R.id.ED_Inv_1ph);
        String V1ph_Str = Ed_V1ph.getText().toString();
        if (V1ph_Str.isEmpty()) {
            InvRating = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            V1ph = Float.parseFloat(V1ph_Str);
        }

        EditText Ed_V3ph = (EditText) findViewById(R.id.ED_Inv_3ph);
        String V3ph_Str = Ed_V3ph.getText().toString();

        if (V3ph_Str.isEmpty()) {
            InvRating = 1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            V3ph = Float.parseFloat(V3ph_Str);
        }



        EditText Ed_InvEff = (EditText) findViewById(R.id.ED_InvEff);
        String InvEff_str = Ed_InvEff.getText().toString();

        if (InvEff_str.isEmpty()){
            inv_eff = 2; // random value for toast to work
        }
        else{
            inv_eff = Float.parseFloat (InvEff_str);
            if (inv_eff < 0 || inv_eff > 1){
                dataOK = false;
                PopUpText = "Inverter efficiency must be between 0 and 1";
            }
        }


        if (InverterV == 0 || InvRating == 0.0 || V1ph == 0.0 || V3ph == 0.0) {
            dataOK = false;
            PopUpText = "Input field cannot be zero";
        }

        if (InverterV > 5000 || InvRating > 10000000 || V1ph > 40000 || V3ph >100000) {
            dataOK = false;
            PopUpText = "At least one of the input numbers is too large";
        }

        if (InvVsrt.isEmpty() || InvkWsrt.isEmpty() || V1ph_Str.isEmpty() || V3ph_Str.isEmpty()) {
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

        double V_DC_design = InverterV * 1.25;



         // Size DC for full inverter capacity

        Imax_DC = InvRating / inv_eff / InverterV * de_rating;


        for (Num_DC = 1; Num_DC <= 10 ;Num_DC++){ // in case one MCB is not big enough

            Inv_DC_MCB_Size = GeneralCalculations.DC_MCB_Calculator(Imax_DC);

            if (Inv_DC_MCB_Size == -1){ // means it hasn't been able to find an MCB for that size

                Imax_DC = Imax_DC/(Num_DC+1);

            }
            else {
                break;
            }
        }

        if (ACtype.equals("Single phase") ) {// 0 == 1ph AC, 1 == 3ph AC
            Imax_AC = InvRating / V1ph * de_rating;
        }

        else {// 3ph AC
            Imax_AC = InvRating / 1.73 / V3ph * de_rating;
        }

        Inv_AC_MCB_Size = GeneralCalculations.AC_MCB_Calculator(Imax_AC);

        if (Inv_DC_MCB_Size == -1) {

            Inv_DC_Wire_Size_Cu = -1; // MCB size is wrong, no point to calculate cable size
            Inv_DC_Wire_Size_Al = -1;

        }
        else{

            Inv_DC_Wire_Size_Cu = GeneralCalculations.CableSizeCalculator(Inv_DC_MCB_Size, 0, 2);
            Inv_DC_Wire_Size_Al = GeneralCalculations.CableSizeCalculator(Inv_DC_MCB_Size, 2, 2);

        }


        if (Inv_AC_MCB_Size == -1){

            Inv_AC_Wire_Size_Cu = -1; // MCB size is wrong, no point to calculate cable size
            Inv_AC_Wire_Size_Al = -1;
        }

        else{

            Inv_AC_Wire_Size_Cu = GeneralCalculations.CableSizeCalculator(Inv_AC_MCB_Size, 0, 0);
            Inv_AC_Wire_Size_Al = GeneralCalculations.CableSizeCalculator(Inv_AC_MCB_Size, 2, 0);
        }

        DC_MCB_V_rating = GeneralCalculations.DC_V_Rating_Calculator(V_DC_design);

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
        String Str_DC_MCB_V_rating = Integer.toString(DC_MCB_V_rating);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Inv_Protection_Set = new Bundle ();

        Inv_Protection_Set.putString("DC_MCB", Str_DC_MCB);
        Inv_Protection_Set.putString("DC_NUM", Str_DC_Num);
        Inv_Protection_Set.putString("DC_CU", Str_DC_Cu);
        Inv_Protection_Set.putString("DC_AL", Str_DC_Al);
        Inv_Protection_Set.putString("AC_MCB", Str_AC_MCB);
        Inv_Protection_Set.putString("AC_CU", Str_AC_Cu);
        Inv_Protection_Set.putString ("AC_AL", Str_AC_Al);
        Inv_Protection_Set.putString("AC_TYPE", ACtype);
        Inv_Protection_Set.putString ("DC_V_RATING", Str_DC_MCB_V_rating);

        // Create and initialise the Intent

        Intent intentINVprotect = new Intent(this, Inverter_results.class);

        //attach the bundle to the Intent object

        intentINVprotect.putExtras(Inv_Protection_Set);

        //start the activity

        startActivity(intentINVprotect);
    }
}
