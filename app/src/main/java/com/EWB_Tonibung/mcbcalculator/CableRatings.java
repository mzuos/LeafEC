package com.EWB_Tonibung.mcbcalculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CableRatings extends AppCompatActivity {

    int CableType = 0, LoadType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cable_ratings);


        Spinner  Spinner_CableType, Spinner_LoadType;

        //get the spinners from the xml.
        Spinner_CableType = findViewById(R.id.RatingsCableSpinner);
        Spinner_LoadType = findViewById(R.id.RatingsLoadSpinner);


        //create an adapter to describe how the items are displayed
        ArrayAdapter adapter1, adapter2;
        adapter1 = ArrayAdapter.createFromResource(this, R.array.CableTypeCatalogue, R.layout.multiline_spinner_dropdown_item);

        adapter2 = ArrayAdapter. createFromResource(this,R.array.Load_type,R.layout.multiline_spinner_dropdown_item);

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
                CableType = position;
                LoadCableData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //Setting OnItemClickListener to Load Type Spinner
        Spinner_LoadType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoadType = position;
                LoadCableData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LoadCableData();
                TextView Dummy = (TextView) findViewById(R.id.TV_CableSTD); // initial value
                Dummy.setText("Cable standard and data source: BS7671 - 4D1****");
            }
        });
    }

    public void LoadCableData(){

        int pixels = 0, size_dp =0;

        // Get Screen density to size listview

        DisplayMetrics displaymetrics;

        displaymetrics = new DisplayMetrics();

        Activity thisactivity;

        thisactivity = CableRatings.this;

        thisactivity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        float density = getResources().getDisplayMetrics().density;

        //pixels = size_array * 36; //height of each individual array

        //size_dp = (int) Math.round (density) * pixels;


        /***********************/

        /*CableType 0 = Cu Clipped Direct
         * CableType 1 = Cu in conduit
         * CableType 2 = Al - PVC - free air
         * CableType 3 = Al - XLPE - free air*/

        // Load type: 0 = 1phAC, 1 = 3phAC, 2 = DC


        ArrayList<String> mm2_list = new ArrayList<String>();
        ArrayList<String> Amp_list = new ArrayList<String>();
        ArrayList<String> VD_list = new ArrayList<String>();

        int cable_catalogue_length = 0;

        mm2_list.clear();
        Amp_list.clear();
        VD_list.clear();


        TextView TV_VD_Ohm =(TextView)findViewById(R.id.TV_VD);
        TextView TV_cableSTD = (TextView) findViewById(R.id.TV_CableSTD);
        TextView TV_Calc_note = (TextView) findViewById (R.id.TV_note);

        ListView Rating_lview = (ListView) findViewById(R.id.Ratings_listview);

        String dummy_mm2 = "", dummy_Amp = "", dummy_VD = "", note_Cu= "", note_Al = "";

        note_Cu = "Note: To calculate the actual voltage drop (in mV) the " +
                "tabulated value must be multiplied by the length of the run " +
                "(one way) in meters and the design current of the cable in Amps. " +
                "\n"+
                "\nFor 3-phase systems the Volt-Drop values are Line to Line. To obtain the Line to Neutral volt-drop" +
                "(4 wire, balanced system), divide the value by 1.73 (=sqrt(3)).";

        note_Al = "Note: To calculate the actual voltage drop (in mV) the " +
                "resistance value of the table must be multiplied by the following values: " +
                "\n"+"\n DC or 1ph AC:" +
                "\n    VD = resistance (Ohm/km) x 2 x cable length in km (one way) x current (Amps) . " +
                "\n"+"\n AC-3phase, line to line: " +
                "\n    VD_3ph_LL = 1.72 x resistance (Ohm/km) x cable length in km (one way) x current (Amps)"+
                "\n"+"\n AC-3phase, line to neutral: " +
                "\n    VD_3ph_LN = Resistance (Ohm/km) x cable length in km (one way) x current (Amps)";


        if (CableType == 0 || CableType == 1){
            TV_cableSTD.setText("Cable standard and data source: BS7671 - 4D1."+"\nTabulated Voltdrop values at maximum permitted conductor temperature");
            TV_VD_Ohm.setText("VoltDrop");
            TV_Calc_note.setText (note_Cu);

            mm2_list.add ("23/0.15 mm");
            dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Clipped_1phAC_DC [0]);
            dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_1phAC [0]);
            Amp_list.add (dummy_Amp + " A"); //rating is same as 0.5mm2
            VD_list.add (dummy_VD + " mV/A/m"); //rating is same as 0.5mm2

        }


        if (CableType == 0 && LoadType == 0){ //Cu-clipped, 1ph AC

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Clipped_1phAC_DC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_1phAC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);

        }

        else if (CableType == 0 && LoadType == 1){ //Cu-clipped, 3ph AC

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Clipped_3phAC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_3phAC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 0 && LoadType == 2){ //Cu-clipped, DC

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Clipped_1phAC_DC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_DC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 1 && LoadType == 0){// Cu-conduit, 1ph

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Conduit__1phAC_DC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_1phAC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }
            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 1 && LoadType == 1){ //Cu-conduit, 3ph AC

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Conduit__3phAC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_3phAC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 1 && LoadType == 2){ //Cu-conduit, DC

            cable_catalogue_length = GeneralCalculations.CopperWireArray.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.CopperWireArray [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Cu_Conduit__1phAC_DC [i]);
                dummy_VD = Double.toString(GeneralCalculations.VD_Cu_Clipped_DC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " mV/A/m");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 2){

            TV_VD_Ohm.setText("Resistance");
            TV_cableSTD.setText("Cable Standard: IEC 60227 - Aerial Bundle Aluminium cable (PVC)." + "\nResistance values of conductor at 20degC");
            TV_Calc_note.setText (note_Al);

            cable_catalogue_length = GeneralCalculations.AlumWireSize_IEC.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.AlumWireSize_IEC [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Al_Air_IEC_PVC [i]);
                dummy_VD = Double.toString(GeneralCalculations.Ohm_Al_Air_IEC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " Ohm/km");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);
        }

        else if (CableType == 3){

            TV_VD_Ohm.setText("Resistance");
            TV_cableSTD.setText("Cable Standard: IEC 60502 - Aerial Bundle Aluminium cable (XLPE)." + "\nResistance values of conductor at 20degC");
            TV_Calc_note.setText (note_Al);

            cable_catalogue_length = GeneralCalculations.AlumWireSize_IEC.length;

            for (int i = 0; i < cable_catalogue_length; i++){

                dummy_mm2 = Double.toString(GeneralCalculations.AlumWireSize_IEC [i]);
                dummy_Amp = Double.toString(GeneralCalculations.Rating_Al_Air_IEC_XLPE [i]);
                dummy_VD = Double.toString(GeneralCalculations.Ohm_Al_Air_IEC [i]);

                mm2_list.add (dummy_mm2 + " sqmm");
                Amp_list.add (dummy_Amp + " A");
                VD_list.add (dummy_VD + " Ohm/km");
            }

            pixels = cable_catalogue_length * 36; //height of each individual array
            size_dp = (int) Math.round (density) * pixels;

            Rating_lview.getLayoutParams().height = size_dp;

            // Load list view
            listview_adapter_cable_ratings adapter = new listview_adapter_cable_ratings(this, mm2_list, Amp_list, VD_list);

            // Display listview
            Rating_lview.setAdapter(adapter);

        }

    }

}
