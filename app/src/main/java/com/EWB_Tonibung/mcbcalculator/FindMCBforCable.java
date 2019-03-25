package com.EWB_Tonibung.mcbcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FindMCBforCable extends AppCompatActivity {

    Spinner sp_cabletype, sp_cablesize;
    int CableType=0, dummy=0;
    double CableSize=0;
    String SelectedType, SelectedSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mcbfor_cable);

        //initialise array adapters
        ArrayAdapter adapter_cabletype;

        //get the spinner from the xml.
        sp_cabletype = findViewById(R.id.cableSpinner);
        sp_cablesize = findViewById(R.id.CableSizeSpinner);
        adapter_cabletype = ArrayAdapter.createFromResource(this, R.array.CableTypeCatalogue, R.layout.multiline_spinner_dropdown_item);

        // Specify the layout to use when the list of choices appears
        adapter_cabletype.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        sp_cabletype.setAdapter(adapter_cabletype);

        //Setting OnItemClickListener to the CABLE TYPE Spinner
        sp_cabletype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //ArrayAdapter adapter_cablesize;
                CableType=position;
                SelectedType=(String) parent.getItemAtPosition(position);


                if (CableType==0 || CableType==1){ //copper cables

                    sp_cablesize.setAdapter(new ArrayAdapter <String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.Cu_WireSizeList)));

                }
                else {// for now we do it with an else, if more cable sizes available, make else_if
                    sp_cablesize.setAdapter(new ArrayAdapter <String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                            getResources().getStringArray(R.array.Al_WireSizeList)));
                }
                            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

        // Cable Size Spinner implementing onItemSelectedListener
        sp_cablesize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                SelectedSize = parent.getItemAtPosition(position).toString();

                // Convert the cable size to a real number

                CableSize=Float.parseFloat(SelectedSize);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

    }


    public void SelectMCBforCable (View view){

        // Call the Cable Size method in General Calculations
        int MCB_Selection=0;
        MCB_Selection = GeneralCalculations.Calculator_MCBforCable(CableSize, CableType);

        // Save the MCB size values as String
        String Str_MCBSize =Integer.toString(MCB_Selection);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Bundle_MCBforCable = new Bundle ();

        Bundle_MCBforCable.putString("MCB_SIZE", Str_MCBSize);


        // Create and initialise the Intent

        Intent intentMCBforCable = new Intent(this, MCBForCable.class);

        //attach the bundle to the Intent object

        intentMCBforCable.putExtras(Bundle_MCBforCable);

        //start the activity

        startActivity(intentMCBforCable);

    }

}
