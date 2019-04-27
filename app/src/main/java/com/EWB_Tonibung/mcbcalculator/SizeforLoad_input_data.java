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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class SizeforLoad_input_data extends AppCompatActivity {

    double Watts, voltage_DC;
    int LoadType;
    int CableType;
    Spinner Spinner_CableType, Spinner_LoadType;

    // Declared at the start as used by different methods in the code

    String V1ph_Srt, V3ph_Srt,CosPhi_Srt, OL_Srt, Vdc_Str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizeforload_input_data);

        final TextView TV_V1ph = findViewById(R.id.TV_V1ph);
        final TextView TV_V3ph = findViewById(R.id.TV_V3ph);
        final TextView TV_cosphi = findViewById(R.id.TV_cosphi);
        final TextView TV_OL = findViewById(R.id.TV_OL);
        final TextView TV_Vdc = findViewById(R.id.TV_Vdc);

        final EditText ed_V1ph = findViewById(R.id.ed_V1ph);
        final EditText ed_V3ph = findViewById(R.id.ed_V3ph);
        final EditText ed_cosphi = findViewById(R.id.ed_cosphi);
        final EditText ed_OL = findViewById(R.id.ed_OL);
        final EditText ed_Vdc = findViewById(R.id.ed_Vdc);


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

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void LoadInput_DataValidation(View view) {

        /* Note to self: need to declare them again so that it updates the values with the
        * user input data. Cannot declare them as global outside OnCreate because the layout hasn't
        * been set up yet and it can't find the texboxes*/

        EditText ed_V1ph = findViewById(R.id.ed_V1ph);
        EditText ed_V3ph = findViewById(R.id.ed_V3ph);
        EditText ed_cosphi = findViewById(R.id.ed_cosphi);
        EditText ed_OL = findViewById(R.id.ed_OL);
        EditText ed_Vdc = findViewById(R.id.ed_Vdc);

        if (2==LoadType){
            Vdc_Str = ed_Vdc.getText().toString();
        }

        else{
            V1ph_Srt = ed_V1ph.getText().toString();
            V3ph_Srt = ed_V3ph.getText().toString();
            CosPhi_Srt = ed_cosphi.getText().toString();
            OL_Srt = ed_OL.getText().toString();
        }



        int ToastXOffset = 280;

        boolean dataOK = true;
        CharSequence PopUpText = "";

        EditText editText_LoadInput = (EditText) findViewById(R.id.ET_InsertLoad);
        String LoadSrt = editText_LoadInput.getText().toString();
        if (LoadSrt.isEmpty()) {
            Watts = -1;//just giving a random value that is not zero for Toasts to work
            dataOK=false;
            PopUpText = "Load cannot be blank";
        } else {
            Watts = Float.parseFloat(LoadSrt);
        }
        if (Watts == 0 ) {
            dataOK = false;
            PopUpText = "Load cannot be zero";
        }

        if (2==LoadType){
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

        double Amps=0;
        int MCBforLoad=0;
        double CableForLoad=0;
        int voltage_1ph, voltage_3ph;
        double cosphi, Overload=1.1; //initialised at weird values to help me identify mistakes

        String Str_LoadType="TBC",Str_MCBSize,Str_WireSize;


        if (LoadType==2){ //DC Load

            Str_LoadType = "DC Load";
            Amps=Watts/voltage_DC*1.25; // Make overload dynamic!
            MCBforLoad=GeneralCalculations.DC_MCB_Calculator(Amps);
        }

        else{ //it's an AC Load

            voltage_1ph =Integer.parseInt(V1ph_Srt);
            voltage_3ph =Integer.parseInt(V3ph_Srt);
            cosphi =Float.parseFloat(CosPhi_Srt);
            Overload =Float.parseFloat(OL_Srt);

            if (LoadType==0){ //AC - Single Phase
                Str_LoadType = "Single Phase";
                Amps=Watts/voltage_1ph/cosphi*Overload;
            }
            else if (LoadType==1){ //AC - Three Phase
                Str_LoadType = "Three Phase";
                Amps=Watts/1.732/voltage_3ph/cosphi*Overload;
            }
            MCBforLoad=GeneralCalculations.AC_MCB_Calculator(Amps);


        }

        if (MCBforLoad !=71){// means we found a suitable MCB
            CableForLoad=GeneralCalculations.CableSizeCalculator(MCBforLoad,CableType);
            Str_MCBSize =Integer.toString(MCBforLoad);
            Str_WireSize = Double.toString(CableForLoad);
        }
        else{
            Str_MCBSize ="n/a";
            Str_WireSize = "n/a";
        }


        // Save the MCB and cable size values as Strings

        String Str_Amps = String.format (Locale.UK, "%.1f", Amps); // Display with one decimal only
        String Str_Watts = String.format (Locale.UK, "%.0f", Watts);
        String Str_OvLoad = String.format (Locale.UK, "%.2f", Overload);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Bundle_Load = new Bundle ();

        Bundle_Load.putString("MCB_SIZE", Str_MCBSize);
        Bundle_Load.putString("WIRE_SIZE", Str_WireSize);
        Bundle_Load.putString("LOAD_TYPE", Str_LoadType);
        Bundle_Load.putString ("POWER", Str_Watts);
        Bundle_Load.putString("AMPS", Str_Amps);
        Bundle_Load.putString("OVERLOAD", Str_OvLoad);


        // Create and initialise the Intent

        Intent Intent_Load = new Intent(this, SizeforLoad_results.class);

        //attach the bundle to the Intent object

        Intent_Load.putExtras(Bundle_Load);

        //start the activity

        startActivity(Intent_Load);

    }


}
