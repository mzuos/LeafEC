package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

public class SizeCable_input_data extends AppCompatActivity {

    int MCBSize;
    public double WireSizeForMCB = 0, WireRating=0;
    int CableType;
    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sizecable_input_data);

        //get the spinner from the xml.
        dropdown = findViewById(R.id.CableSpinner);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.

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
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        ImageView ClippedvsConduit = (ImageView) findViewById(R.id.IV_size_MCB) ;

        ClippedvsConduit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SizeCable_input_data.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_custom_layout, null);
                PhotoView photoView = mView.findViewById(R.id.imageView);
                photoView.setImageResource(R.drawable.clipped_vs_conduit);
                mBuilder.setView(mView);
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });



    }

    public void CableForMCB_DataValidation(View view) {
        int ToastXOffset = 280;

        EditText editTextMCBSize = (EditText) findViewById(R.id.MCBSize);
        String MCBSizeSrt = editTextMCBSize.getText().toString();
        if (MCBSizeSrt.isEmpty()) {
            MCBSize = -1;//just giving a random value that is not zero for Toasts to work properly
        } else {
            MCBSize = Integer.parseInt(MCBSizeSrt);
        }

        boolean dataOK = true;
        CharSequence PopUpText = "";

        if (MCBSize == 0 ) {
            dataOK = false;
            PopUpText = "MCB size cannot be zero";
        }

        if (MCBSizeSrt.isEmpty()) {
            dataOK = false;
            PopUpText = "MCB size cannot be blank";
        }

        if (!dataOK) {
            // dataOK=true;
            Context context = getApplicationContext();
            //CharSequence PopUpText = "Review your input data";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, PopUpText, duration);
            View viewtext = toast.getView();

            //Gets the actual oval background of the Toast then sets the colour filter
            viewtext.getBackground().setColorFilter(getResources().getColor(R.color.color_leaf_Toast), PorterDuff.Mode.SRC_IN);
            toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, -380);
            TextView toastMessage = (TextView) toast.getView().findViewById(android.R.id.message);
            toastMessage.setTextColor(Color.WHITE);
            toast.show();


        }

        else Calculator_CableforMCB();

    }

    public void Calculator_CableforMCB(){

        // Call the Cable Size method in General Calculations

        WireSizeForMCB = GeneralCalculations.CableSizeCalculator (MCBSize, CableType,0 );
        WireRating = GeneralCalculations.CableCurrentRating(CableType,0, WireSizeForMCB);


        // Save the MCB and cable size values as Strings

        String Str_MCBSize =Integer.toString(MCBSize);
        String Str_WireSize = Double.toString(WireSizeForMCB);
        String Str_WireRating = Double.toString(WireRating);

        //Create a Bundle object and add key value pairs to the bundle.

        Bundle Bundle_CableForMCB = new Bundle ();

        Bundle_CableForMCB.putString("MCB_SIZE", Str_MCBSize);
        Bundle_CableForMCB.putString("WIRE_SIZE", Str_WireSize);
        Bundle_CableForMCB.putString("WIRE_RATING", Str_WireRating);

        // Create and initialise the Intent

        Intent intentCableForMCB = new Intent(this, SizeCable_results.class);

        //attach the bundle to the Intent object

        intentCableForMCB.putExtras(Bundle_CableForMCB);

        //start the activity

        startActivity(intentCableForMCB);
    }

}




