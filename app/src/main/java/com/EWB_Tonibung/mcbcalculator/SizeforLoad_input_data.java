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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SizeforLoad_input_data extends AppCompatActivity {

    double Watts, voltage_DC, distance, maxVD_percent, max_VoltDrop, VoltDrop, PercentVD, OL_DC;
    int LoadType;
    int CableType;
    Spinner Spinner_CableType, Spinner_LoadType;

    double Amps = 0, Amps_R =0; //Amps_R: real amps, Amps = aparent amps
    int MCBforLoad=0;
    double CableForLoad = 0, CableForVoltDrop = 0, ChosenCable = 0, Ohms = 0;
    double Cable4Load_rating = 0;
    double voltage_1ph = 1, voltage_3ph = 1;//initialised with silly values !=0
    double cosphi =0, Overload=0.999; //initialised at weird values to help me identify mistakes
    double VD_goal = 0 ; // The value we need to search for

    String Str_LoadType = "TBC", Str_MCBSize, Str_WireSize, Str_WireforLoad;
    String VD_requirement = "NO", VDShow = "NO", VD_satisfied = "YES";

    String Str_maxVD_PC, Str_VoltDrop, Str_PercentVD, Str_distance ;

    int catalog_length, load_cable_position = 0;

    ArrayList  <String> Ohms_array = new ArrayList<String>();
    ArrayList <String> VD_array = new ArrayList<String>();
    ArrayList <String> VD_percent_array = new ArrayList<String>();
    ArrayList <String> mm2_array = new ArrayList<String>();

    // Declared at the start as used by different methods in the code
    String V1ph_Srt, V3ph_Srt,CosPhi_Srt, OL_Srt, Vdc_Str, OL_dc_Str;
    CheckBox VD_CheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizeforload_input_data);

        //Listener for Volt drop check box:
        VD_CheckBox = (CheckBox) findViewById(R.id.VoltDrop_checkBox);
        addListenerOnVoltDropCheckBox();

       // declare all the fields that toggle visibility with Load Type
        final TextView TV_V1ph = (TextView)findViewById(R.id.TV_V1ph);
        final TextView TV_V3ph = (TextView)findViewById(R.id.TV_V3ph);
        final TextView TV_cosphi = (TextView)findViewById(R.id.TV_cosphi);
        final TextView TV_OL = (TextView)findViewById(R.id.TV_OL);
        final TextView TV_Vdc = (TextView)findViewById(R.id.TV_Vdc);
        final TextView TV_OL_DC = (TextView)findViewById(R.id.TV_OL_DC);

        final EditText ed_V1ph = (EditText)findViewById(R.id.ed_V1ph);
        final EditText ed_V3ph = (EditText)findViewById(R.id.ed_V3ph);
        final EditText ed_cosphi = (EditText)findViewById(R.id.ed_cosphi);
        final EditText ed_OL = (EditText)findViewById(R.id.ed_OL);
        final EditText ed_Vdc = (EditText)findViewById(R.id.ed_Vdc);
        final EditText ed_OL_DC = (EditText)findViewById(R.id.ED_OL_DC);

        //get the spinners from the xml.
        Spinner_CableType = findViewById(R.id.LoadCableSpinner);
        Spinner_LoadType = findViewById(R.id.LoadTypeSpinner);

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        ArrayAdapter adapter1, adapter2;
        adapter1 = ArrayAdapter.createFromResource(this, R.array.CableTypeCatalogue, R.layout.multiline_spinner_dropdown_item);
        adapter2 = ArrayAdapter.createFromResource (this, R.array.Load_type, R.layout.multiline_spinner_dropdown_item);

        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);

        //set the spinners adapter to the previously created one.
        Spinner_CableType.setAdapter(adapter1);
        Spinner_LoadType.setAdapter(adapter2);


        //Setting OnItemClickListener to Cable Type Spinner
        Spinner_CableType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CableType=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Setting OnItemClickListener to Load Type Spinner
        Spinner_LoadType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoadType = position;

                int MakeACVisible, MakeDCVisible;

                if (2==LoadType){
                    MakeACVisible=0X00000004;
                    MakeDCVisible=0X00000000;//0X00000000=invisible, 0X00000004=invisible
                }
                else {
                    MakeACVisible=0X00000000;
                    MakeDCVisible=0X00000004;//0X00000000=invisible, 0X00000004=invisible
                }

                // SET Visibility for AC and DC inputs
                TV_V1ph.setVisibility(MakeACVisible);
                TV_V3ph.setVisibility(MakeACVisible);
                TV_cosphi.setVisibility(MakeACVisible);
                TV_OL.setVisibility(MakeACVisible);
                ed_V1ph.setVisibility(MakeACVisible);
                ed_V3ph.setVisibility(MakeACVisible);
                ed_cosphi.setVisibility(MakeACVisible);
                ed_OL.setVisibility(MakeACVisible);

                //SHOW DC-input related fields - hidden by default

                TV_Vdc.setVisibility(MakeDCVisible);
                ed_Vdc.setVisibility(MakeDCVisible);
                TV_OL_DC.setVisibility(MakeDCVisible);
                ed_OL_DC.setVisibility(MakeDCVisible);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


    }

    public void addListenerOnVoltDropCheckBox() {

        VD_CheckBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                TextView TV_EnterDistance = (TextView)findViewById(R.id.TV_distance);
                EditText ED_distance = (EditText)findViewById(R.id.ET_km);
                TextView TV_maxVoltDrop = (TextView)findViewById(R.id.TV_maxVoltDrop);
                EditText ED_maxVoltDrop = (EditText) findViewById(R.id.ET_maxVD) ;

                if (((CheckBox) v).isChecked()) {
                    TV_EnterDistance.setVisibility(View.VISIBLE);
                    ED_distance.setVisibility(View.VISIBLE);
                    TV_maxVoltDrop.setVisibility(View.VISIBLE);
                    ED_maxVoltDrop.setVisibility(View.VISIBLE);
                }

                else {
                    TV_EnterDistance.setVisibility(View.INVISIBLE);
                    ED_distance.setVisibility(View.INVISIBLE);
                    TV_maxVoltDrop.setVisibility(View.INVISIBLE);
                    ED_maxVoltDrop.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    public void LoadInput_DataValidation(View view) {

        // LOAD RELEVANT DATA

        /* Note to self: need to declare them again so that it updates the values with the
        * user input data. Cannot declare them as global outside OnCreate because the layout hasn't
        * been set up yet and it can't find the texboxes*/

        EditText ed_V1ph = (EditText)findViewById(R.id.ed_V1ph);
        EditText ed_V3ph = (EditText)findViewById(R.id.ed_V3ph);
        EditText ed_cosphi = (EditText)findViewById(R.id.ed_cosphi);
        EditText ed_OL = (EditText)findViewById(R.id.ed_OL);
        EditText ed_Vdc = (EditText)findViewById(R.id.ed_Vdc);
        EditText ed_OL_DC = (EditText)findViewById(R.id.ED_OL_DC);

        if (2 == LoadType){
            Vdc_Str = ed_Vdc.getText().toString();
            OL_dc_Str = ed_OL_DC.getText().toString();
        }

        else{
            V1ph_Srt = ed_V1ph.getText().toString();
            V3ph_Srt = ed_V3ph.getText().toString();
            CosPhi_Srt = ed_cosphi.getText().toString();
            OL_Srt = ed_OL.getText().toString();
        }

        EditText editText_LoadInput = (EditText) findViewById(R.id.ET_InsertLoad);
        String LoadSrt = editText_LoadInput.getText().toString();

        int ToastXOffset = 280;

        boolean dataOK = true;
        CharSequence PopUpText = "";

        // DATA VALIDATION CHECK

        if (LoadSrt.isEmpty()) {
            Watts = -1;//just giving a random value that is not zero for Toasts to work
            dataOK = false;
            PopUpText = "Load cannot be blank";
        }

        else {
            Watts = Float.parseFloat(LoadSrt);
        }

        if (Watts == 0 ) {
            dataOK = false;
            PopUpText = "Load cannot be zero";
        }
        else if (Watts > 10000000){
            dataOK = false;
            PopUpText = "Watts value is too large";
        }

        if (2 == LoadType){
            if (Vdc_Str.isEmpty()){
                voltage_DC = 1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "DC voltage cannot be blank";
            }
            else{
                voltage_DC = Float.parseFloat(Vdc_Str);
            }

            if (voltage_DC == 0 ) {
                dataOK = false;
                PopUpText = "DC voltage cannot be zero";
            }
            else if (voltage_DC > 1000){
                dataOK = false;
                PopUpText = "DC voltage value is too large";
            }

            if (OL_dc_Str.isEmpty()){
                OL_DC = -1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "Overload cannot be blank";
            }
            else{
                OL_DC = Float.parseFloat(OL_dc_Str);
            }

            if (OL_DC == 0 ) {
                dataOK = false;
                PopUpText = "Overload cannot be zero";
            }
            else if (OL_DC > 1000){
                dataOK = false;
                PopUpText = "Overload value is too large";
            }
        }
        
        else{
            
            if (V1ph_Srt.isEmpty()){
                voltage_1ph = 1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "1ph-AC voltage cannot be blank";
            }
            else{
                voltage_1ph = Float.parseFloat(V1ph_Srt);
            }

            if (voltage_1ph == 0 ) {
                dataOK = false;
                PopUpText = "1ph-AC voltage cannot be zero";
            }
            else if (voltage_1ph > 1000000){
                dataOK = false;
                PopUpText = "1ph-AC voltage value is too large";
            }


            if (V3ph_Srt.isEmpty()){
                voltage_3ph = 1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "3ph-AC voltage cannot be blank";
            }
            else{
                voltage_3ph = Float.parseFloat (V3ph_Srt);
            }

            if (voltage_3ph == 0 ) {
                dataOK = false;
                PopUpText = "3ph-AC voltage cannot be zero";
            }
            else if (voltage_3ph > 6000000){
                dataOK = false;
                PopUpText = "3ph-AC voltage value is too large";
            }

            if (CosPhi_Srt.isEmpty()){
                cosphi = 1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "Power factor cannot be blank";
            }
            else{
                cosphi = Float.parseFloat(CosPhi_Srt);
            }

            if (cosphi >1 || cosphi<-1 ) {
                dataOK = false;
                PopUpText = "Power factor must be between 0 and 1";
            }


            if (OL_Srt.isEmpty()){
                Overload = 1; //just giving a random value that is not zero for Toasts to work
                dataOK = false;
                PopUpText = "Overload cannot be blank";
            }
            else{
                Overload = Float.parseFloat(OL_Srt);
            }

            if (Overload == 0 ) {
                dataOK = false;
                PopUpText = "Overload cannot be zero";
            }
            else if (Overload > 100){
                dataOK = false;
                PopUpText = "Overload value is too large";
            }
        }

        if (VD_CheckBox.isChecked()){

            EditText ED_distance = (EditText)findViewById(R.id.ET_km);
            String Str_distance = ED_distance.getText().toString();
            EditText ED_maxVdrop = (EditText)findViewById(R.id.ET_maxVD);
            Str_maxVD_PC = ED_maxVdrop.getText().toString();

            if (Str_distance.isEmpty()){
                distance = -1;
                dataOK = false;
                PopUpText = "Enter distance for distance";
            }
            else{
                distance = Float.parseFloat(Str_distance);
            }

            if (distance == 0){
                dataOK=false;
                PopUpText = "Distance cannot be zero";
            }
            else if (distance > 10000000){
                dataOK=false;
                PopUpText = "Distance value is too large";
            }

            if (!Str_maxVD_PC.isEmpty()){
                maxVD_percent = Float.parseFloat(Str_maxVD_PC);
                if (maxVD_percent >= 100 || maxVD_percent <= 0){
                    dataOK=false;
                    PopUpText = "Maximum volt drop must be between 0 and 100";
                }
            }
            else{
                maxVD_percent = -1; // Random value so that I know the field is empty
            }


        }


        if (!dataOK) {

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

        }

       else DesignForLoad();

    }

    public void DesignForLoad(){


        if (LoadType == 2){ //DC Load

            Str_LoadType = "DC Load";
            Amps_R = Watts / voltage_DC ; //for volt drop we consider the actual current, no overload
            Amps = Amps_R * OL_DC;
            MCBforLoad = GeneralCalculations.DC_MCB_Calculator(Amps);
        }

        else{ //it's an AC Load
            
            if (LoadType == 0){ //AC - Single Phase
                Str_LoadType = "Single Phase";
                Amps_R = Watts / voltage_1ph ; //for volt drop we consider the actual current, no overload
                Amps = Amps_R  / cosphi * Overload ;
            }
            else if (LoadType == 1){ //AC - Three Phase
                Str_LoadType = "Three Phase";
                Amps_R = Watts /1.732 / voltage_3ph ; //for volt drop we consider the actual current, no overload
                Amps = Amps_R / cosphi * Overload;
            }

            MCBforLoad = GeneralCalculations.AC_MCB_Calculator(Amps);

        }

        if (MCBforLoad == -1){// means we found a suitable MCB

            Str_MCBSize ="n/a";
            MCBforLoad = (int)Math.round(Amps); // We are gonna calculate the cable size and volt drop for the load
            // convert the amps into and INT because that's what the cable sizing function needs
        }

        else{

            Str_MCBSize =Integer.toString(MCBforLoad);
        }

        CableForLoad = GeneralCalculations.CableSizeCalculator(MCBforLoad,CableType);

        if (CableForLoad != -1){ //it means we were able to find a cable


            Cable4Load_rating = GeneralCalculations.CableCurrentRating(CableType, CableForLoad);



            //FIND POSITION OF "CABLE FOR LOAD" IN THAT SPECIFIC CABLE CATALOGUE

            if (CableType == 0 || CableType == 1){//Copper cables

                catalog_length = GeneralCalculations.CopperWireArray.length;

            }
            else if (CableType == 2 || CableType ==3){//Aluminium cables

                catalog_length = GeneralCalculations.AlumWireSize_IEC.length;

            }

            load_cable_position = GeneralCalculations.FindCablePosition (CableType, CableForLoad);

            if (VD_CheckBox.isChecked()){ //
                VDShow = "YES";

                //********** VOLT DROP CALCULATIONS *************

                Volt_drop_calculations ();

                //****************************8******************
            }

            else{
                VDShow = "NO";
                ChosenCable = CableForLoad;
            }

            Str_WireforLoad = Double.toString(CableForLoad);
            Str_WireSize = Double.toString(ChosenCable);



            if (VD_requirement.equals("YES") && (max_VoltDrop < VoltDrop)){ //means we were unable ot find a suitable cable

                VD_satisfied = "NO";
            }
            else{

                VD_satisfied = "YES";
            }
        }

        else{
            Str_WireSize = "n/a";
        }


        Prepare_return_values(); // CAll the functions that prepares the Bundle


    }

    void Volt_drop_calculations (){

        double cablesize;

        if (maxVD_percent != -1) {//userspecified max. volt drop
            VD_requirement = "YES";

            if (LoadType == 0){ //AC - 1ph
                max_VoltDrop = voltage_1ph * maxVD_percent / 100;
            }
            else if (LoadType == 1) { //AC - 3ph
                max_VoltDrop = voltage_3ph * maxVD_percent / 100;
            }
            else if (LoadType == 2) { //DC
                max_VoltDrop = voltage_DC * maxVD_percent / 100;
            }

            if (CableType == 0 || CableType == 1){ //VD tables in (mV/Amp*m), distance one way
                VD_goal = max_VoltDrop * 1000 / Amps_R / distance;
            }

            else if (CableType == 2 || CableType == 3){ //VD tables in (Ohm/km), distance 2 ways
                VD_goal = max_VoltDrop / Amps_R / (distance / 1000 * 2);
            }

            CableForVoltDrop = GeneralCalculations.CableForVoltDrop (CableType, LoadType, VD_goal);

            if (CableForVoltDrop > CableForLoad) { // Choose highest cable
                ChosenCable = CableForVoltDrop;
            }
            else{
                ChosenCable = CableForLoad;
            }

            // LOOP THE CALCULATION FOR THE REST OF THE CABLES IN THE CATALOGUE

            cablesize = ChosenCable;

            Calculate_Ohm_VD_VDPC (cablesize);

            Str_VoltDrop = String.format (Locale.UK, "%.1f", VoltDrop);
            Str_PercentVD = String.format (Locale.UK, "%.2f", PercentVD);
            Str_distance = Double.toString(distance);

            mm2_array.clear();
            Ohms_array.clear();
            VD_array.clear();
            VD_percent_array.clear();

            for (int i = load_cable_position; i < catalog_length; i++ ){

                if (CableType == 0 || CableType ==1){

                    cablesize = GeneralCalculations.CopperWireArray [i];

                }
                else if (CableType == 2 || CableType ==3){

                    cablesize = GeneralCalculations.AlumWireSize_IEC [i];

                }

                Calculate_Ohm_VD_VDPC (cablesize);

                String dummy_cablesize =  String.format (Locale.UK, "%.1f", cablesize) + " sqmm";
                String dummy_ohms =  String.format (Locale.UK, "%.3f", Ohms);
                String dummy_VD = String.format (Locale.UK, "%.2f", VoltDrop) + " V";
                String dummy_VD_percent = String.format (Locale.UK, "%.2f", PercentVD) + " %";

                mm2_array.add (dummy_cablesize);
                Ohms_array.add(dummy_ohms);
                VD_array.add (dummy_VD);
                VD_percent_array.add(dummy_VD_percent);
            }

        }

        else{
            VD_requirement = "NO";
            ChosenCable = CableForLoad;
            //Retrieve volt drop information for selected cable, regardless it's been selected for Load or Voltdrop
            Calculate_Ohm_VD_VDPC (ChosenCable);
            Str_VoltDrop = String.format (Locale.UK, "%.1f", VoltDrop);
            Str_PercentVD = String.format (Locale.UK, "%.2f", PercentVD);
            Str_distance = Double.toString(distance);
        }

    }

    void Calculate_Ohm_VD_VDPC (double cable){

        Ohms = GeneralCalculations.VoltDropInfo (CableType, LoadType, cable);

        if (CableType == 2 || CableType == 3){ //Aluminium cables
            // Ohms comes in(Ohm/A*km)
            VoltDrop = Ohms * (distance / 1000 * 2) * Amps_R; //We consider 2 times the distance
        }
        else if (CableType == 0 || CableType ==1){ //Copper cables
            // Ohms comes in(mV/A*m)
            VoltDrop = (Ohms / 1000) * distance * Amps_R; //We consider 1 time the distance
        }

        if (LoadType == 0){ //AC - 1ph
            PercentVD = VoltDrop / voltage_1ph *100;
        }
        else if (LoadType == 1) { //AC - 3ph
            PercentVD = VoltDrop / voltage_3ph *100;
        }
        else if (LoadType == 2) { //DC
            PercentVD = VoltDrop / voltage_DC * 100;
        }

    }

    void Prepare_return_values (){

        //************ DISPLAY VALUES ***************************************************

        // Save the MCB and cable size values as Strings

        String Str_Amps = String.format (Locale.UK, "%.1f", Amps); // Display with one decimal only
        String Str_Watts = String.format (Locale.UK, "%.0f", Watts);
        String Str_OvLoad = "tbc";
        String Str_Volts = "tbc";
        String Cable4Load_rating_str = Double.toString(Cable4Load_rating);

        int array_size = Ohms_array.size();

        String [] mm2_array_Str = new String [array_size];
        String [] Ohms_array_Str = new String [array_size];
        String [] VD_array_Str = new String [array_size];
        String [] VDPC_array_Str = new String [array_size];

        for (int i=0; i <  array_size; i++){

            mm2_array_Str [i] = mm2_array.get(i);
            Ohms_array_Str [i] = Ohms_array.get(i);
            VD_array_Str [i] = VD_array.get(i);
            VDPC_array_Str [i] = VD_percent_array.get(i);
        }

        if (LoadType == 2){ //DCLOAD

            Str_OvLoad = String.format (Locale.UK, "%.2f", OL_DC);
            Str_Volts = Vdc_Str;
        }
        else{
            Str_OvLoad = String.format (Locale.UK, "%.2f", Overload);

            if (LoadType == 0){ //AC Single Phase
                Str_Volts = V1ph_Srt;
            }
            else{
                Str_Volts = V3ph_Srt; //AC Three Phase
            }
        }

        String.format (Locale.UK, "%.2f", Overload);



        //Find position of chosen cable in the cable catalog (used for ListView focus)

        int chosen_cable_position = GeneralCalculations.FindCablePosition(CableType,ChosenCable);

        chosen_cable_position = chosen_cable_position - load_cable_position;

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Bundle_Load = new Bundle ();

        Bundle_Load.putString("MCB_SIZE", Str_MCBSize);
        Bundle_Load.putString("WIRE_4_LOAD", Str_WireforLoad);
        Bundle_Load.putString ("RATING_4_LOAD", Cable4Load_rating_str);
        Bundle_Load.putString("WIRE_SIZE", Str_WireSize);
        Bundle_Load.putString("LOAD_TYPE", Str_LoadType);
        Bundle_Load.putString ("POWER", Str_Watts);
        Bundle_Load.putString ("VOLTAGE", Str_Volts);
        Bundle_Load.putString("AMPS", Str_Amps);
        Bundle_Load.putString("OVERLOAD", Str_OvLoad);
        Bundle_Load.putString("VDDISPLAY", VDShow);

        Bundle_Load.putString("VOLTDROP", Str_VoltDrop);
        Bundle_Load.putString("VDPERCENT", Str_PercentVD);
        Bundle_Load.putString("MAX_VD_PC", Str_maxVD_PC);
        Bundle_Load.putString("DISTANCE", Str_distance);
        Bundle_Load.putString ("VD_REQ", VD_requirement);
        Bundle_Load.putString ("VD_SATISFIED", VD_satisfied);

        Bundle_Load.putInt ("ARRAY_SIZE", array_size);
        Bundle_Load.putInt ("CHOSEN_WIRE_POSITION", chosen_cable_position);
        Bundle_Load.putStringArray("SQMM_ARRAY", mm2_array_Str);
        Bundle_Load.putStringArray("OHMS_ARRAY", Ohms_array_Str);
        Bundle_Load.putStringArray("VD_ARRAY", VD_array_Str);
        Bundle_Load.putStringArray("VDPC_ARRAY", VDPC_array_Str);

        // Create and initialise the Intent

        Intent Intent_Load = new Intent(this, SizeforLoad_results.class);

        //attach the bundle to the Intent object

        Intent_Load.putExtras(Bundle_Load);

        //start the activity

        startActivity(Intent_Load);

    }

}
