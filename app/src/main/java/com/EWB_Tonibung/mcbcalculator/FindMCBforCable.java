package com.EWB_Tonibung.mcbcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FindMCBforCable extends AppCompatActivity {

    Spinner dropdown;
    int CableType;
    double CableSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_mcbfor_cable);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.cableSpinner);
        ArrayAdapter adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.CableTypeCatalogue, R.layout.multiline_spinner_dropdown_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.multiline_spinner_dropdown_item);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

        //Setting OnItemClickListener to the Spinner
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CableType=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void CableForMCB_DataValidation(View view) {
        int ToastXOffset = 280;

        EditText editTextCableSize = (EditText) findViewById(R.id.Input_CableSize);
        String CableSizeSrt = editTextCableSize.getText().toString();
        if (CableSizeSrt.isEmpty()) {
            CableSize = -1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            CableSize = Integer.parseInt(CableSizeSrt);
        }

        boolean dataOK = true;
        CharSequence PopUpText = "";

        if (CableSize == 0.0 ) {
            dataOK = false;
            PopUpText = "Cable size cannot be zero";
        }

        if (CableSizeSrt.isEmpty()) {
            dataOK = false;
            PopUpText = "Cable size cannot be blank";
        }

        if (!dataOK) {
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
        }

        // Toast showing available cable sizes for that cable type and Stop the Code

        else SelectMCBforCable();
    }

    public void SelectMCBforCable (){

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
