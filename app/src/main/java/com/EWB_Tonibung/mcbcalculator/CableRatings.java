package com.EWB_Tonibung.mcbcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class CableRatings extends AppCompatActivity {

    int CableType=0, LoadType=0;
    ListView listview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cable_ratings);

        Spinner Spinner_CableType, Spinner_LoadType;

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
                CableType=position;
                LoadCableData(CableType, LoadType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //Setting OnItemClickListener to Load Type Spinner
        Spinner_LoadType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LoadType=position;
                LoadCableData(CableType, LoadType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                LoadCableData(CableType, LoadType);
                TextView Dummy = (TextView) findViewById(R.id.TV_CableSTD); // initial value
                Dummy.setText("Cable standard and data source: BS7671 - 4D1");
            }
        });

    }

    public void LoadCableData(int Cable_Type, int Load_Type){

        /*CableType 0 = Cu Clipped Direct
        * CableType 1 = Cu in conduit
        * CableType 2 = Al - PVC - free air
        * CableType 3 = Al - XLPE - free air*/

        // Load type: 0 = 1phAC, 1 = 3phAC, 2 = DC

        TextView TV_ratingtitle=(TextView)findViewById(R.id.TV_RatingsTittle);
        TextView TV_cableSTD = (TextView) findViewById(R.id.TV_CableSTD);
        listview = (ListView) findViewById(R.id.Ratings_listview);

        ArrayAdapter adapter_listview;
        if (CableType == 0 || CableType == 1){
            TV_cableSTD.setText("Cable standard and data source: BS7671 - 4D1");
            TV_ratingtitle.setText("Size  -  Rating  -  VoltDrop");
        }


        if (Cable_Type == 0 && Load_Type == 0){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Clipped_1ph,R.layout.listview_simplelayout );

        }
        else if (Cable_Type == 0 && Load_Type == 1){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Clipped_3ph, R.layout.listview_simplelayout);
        }
        else if (Cable_Type == 0 && Load_Type == 2){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Clipped_DC, R.layout.listview_simplelayout);
        }
        else if (Cable_Type == 1 && Load_Type == 0){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Conduit_1ph, R.layout.listview_simplelayout);
        }
        else if (Cable_Type == 1 && Load_Type == 1){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Conduit_3ph, R.layout.listview_simplelayout);
        }

        else if (Cable_Type == 1 && Load_Type == 2){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Cu_WireRating_Conduit_DC, R.layout.listview_simplelayout);
        }
        else if (Cable_Type == 2){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Al_WireRating_IEC_PVC, R.layout.listview_simplelayout);
            TV_ratingtitle.setText("Size   -   Rating   -   Resistivity");
            TV_cableSTD.setText("Cable Standard: IEC 60227 - Aerial Bundle Aluminium cable (PVC)");
        }

        else if (Cable_Type == 3){
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Al_WireRating_IEC_XLPE, R.layout.listview_simplelayout);
            TV_ratingtitle.setText("Size   -   Rating   -   Resistivity");
            TV_cableSTD.setText("Cable Standard: IEC 60502 - Aerial Bundle Aluminium cable (XLPE)");
        }
        else{// Redundant, simply here so that adapter has a default value
            adapter_listview = ArrayAdapter.createFromResource(getApplicationContext(), R.array.Al_WireRating_IEC_XLPE, R.layout.listview_simplelayout);
            TV_ratingtitle.setText("Size  -  Rating  -  Resistivity");
        }



        listview.setAdapter(adapter_listview);

    }

}
