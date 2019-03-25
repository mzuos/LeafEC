package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoadInput extends AppCompatActivity {

    double Watts;
    int LoadType;
    int CableType;
    Spinner Spinner_CableType, Spinner_LoadType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_input);

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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void LoadInput_DataValidation(View view) {
        int ToastXOffset = 280;

        EditText editText_LoadInput = (EditText) findViewById(R.id.ET_InsertLoad);
        String LoadSrt = editText_LoadInput.getText().toString();
        if (LoadSrt.isEmpty()) {
            Watts = -1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            Watts = Float.parseFloat(LoadSrt);
        }

        boolean dataOK = true;
        CharSequence PopUpText = "";

        if (Watts == 0 ) {
            dataOK = false;
            PopUpText = "Load cannot be zero";
        }

        if (LoadSrt.isEmpty()) {
            dataOK = false;
            PopUpText = "Load cannot be blank";
        }

        if (!dataOK) {

            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();
            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(72 - 61 - 139, PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, ToastXOffset, 400);
            toast.show();
        }

       else DesignForLoad();

    }

    public void DesignForLoad(){

        double Amps=0;
        int MCBforLoad=0;
        double CableForLoad=0;


        if (LoadType==0){ //AC - Single Phase
            Amps=Watts/230/0.85; //0.85 power factor  (this can be made dynamic)

        }
        else if (LoadType==1){ //AC - Three Phase
            Amps=Watts/1.732/400/0.85;
        }
        MCBforLoad=GeneralCalculations.AC_MCB_Calculator(Amps);
        CableForLoad=GeneralCalculations.CableSizeCalculator(MCBforLoad,CableType);

        // Save the MCB and cable size values as Strings

        String Str_MCBSize =Integer.toString(MCBforLoad);
        String Str_WireSize = Double.toString(CableForLoad);
        String Str_LoadType = Integer.toString(LoadType);
        String Str_Amps = Double.toString(Amps);
        String Str_Watts = Double.toString (Watts);


        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Bundle_Load = new Bundle ();

        Bundle_Load.putString("MCB_SIZE", Str_MCBSize);
        Bundle_Load.putString("WIRE_SIZE", Str_WireSize);
        Bundle_Load.putString("LOAD_TYPE", Str_LoadType);
        Bundle_Load.putString ("POWER", Str_Watts);
        Bundle_Load.putString("AMPS", Str_Amps);


        // Create and initialise the Intent

        Intent Intent_Load = new Intent(this, DesignForLoad.class);

        //attach the bundle to the Intent object

        Intent_Load.putExtras(Bundle_Load);

        //start the activity

        startActivity(Intent_Load);

    }


}
