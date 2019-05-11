package com.EWB_Tonibung.mcbcalculator;

import static com.EWB_Tonibung.mcbcalculator.listview_constant.FIRST_COLUMN;
import static com.EWB_Tonibung.mcbcalculator.listview_constant.SECOND_COLUMN;
import static com.EWB_Tonibung.mcbcalculator.listview_constant.THIRD_COLUMN;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.widget.Spinner;
import android.widget.TextView;

// CODE SOURCE: http://www.technotalkative.com/android-multi-column-listview/

    public class AWG_mm2_converter extends AppCompatActivity{

        public static int [] AWG_size_list = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    private ArrayList<HashMap> list;

    String SelectedSize;
    double I_know_mm2 = 0, I_know_AWG = 0, mm2_equiv = 0;
    int AWG_equiv = 0;
    boolean mode = true;
    Spinner spinner_cable_sizes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awg_mm2_converter);

        ListView lview = (ListView) findViewById(R.id.AWG_listview);
        populateList();
        listviewAdapter adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);

        spinner_cable_sizes = (Spinner) findViewById(R.id.spinner_cable_sizes);
        spinner_cable_sizes.setAdapter(new ArrayAdapter <String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.Cu_WireSizeList))); //On Create comes in Iknowmm2 mode


        spinner_cable_sizes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (mode == true){

                    if (position == 0){

                        I_know_mm2 = 0.5; // position "0" is 23/0.15mm = 0.5mm2
                    }
                    else {

                        String Size_dummy = parent.getItemAtPosition(position).toString();

                        //SelectedSize = Size_dummy.substring(0,1);
                        String [] dummy  = Size_dummy.split(" ");
                        SelectedSize = dummy [0];

                        // Convert the cable size to a real number
                        I_know_mm2 = Float.parseFloat(SelectedSize);
                    }

                    find_AWG_equivalent();
                }
                else{

                    double AWG_exact_mm2 = 0;

                    if (position > 3){
                       String AWG_selection = parent.getItemAtPosition(position).toString();
                       String [] AWG = AWG_selection.split(" ");
                       int n = Integer.parseInt (AWG[1]);

                       double exp = (36-n)/19.5;
                       AWG_exact_mm2 = 0.012668 * Math.pow(92,exp);
                    }
                    else if (position == 3){ //(1/0)
                        AWG_exact_mm2 = 53.4751; // The exact area for the cable
                    }
                    else if (position == 2){ //(2/0)
                        AWG_exact_mm2 = 67.4309; // The exact area for the cable
                    }
                    else if (position == 1){ //(3/0)
                        AWG_exact_mm2 = 85.0288; // The exact area for the cable
                    }
                    else if (position == 0){ //(4/0)
                        AWG_exact_mm2 = 107.2193; // The exact area for the cable
                    }

                    find_sqmm_equivalent(AWG_exact_mm2);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}

        });

    }

    public void swap_converter_mode (View view){

        mode = !mode;

        //setContentView(R.layout.activity_awg_mm2_converter);

        TextView TV_Iknow = (TextView) findViewById(R.id.TV_Iknow);
        TextView TV_Iwant = (TextView) findViewById(R.id.TV_Iwant);


        if (mode == true){ // I know mm2 - I want AWG

            TV_Iknow.setText("I know sqmm size");
            TV_Iwant.setText ("AWG equivalent");
            spinner_cable_sizes.setAdapter(new ArrayAdapter <String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.Cu_WireSizeList)));
        }
        else{ // I know AWG - I want mm2

            TV_Iknow.setText("I know AWG size");
            TV_Iwant.setText ("sqmm equivalent");

            spinner_cable_sizes.setAdapter(new ArrayAdapter <String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,
                    getResources().getStringArray(R.array.AWG_sizes)));
        }

    }

   public void find_sqmm_equivalent (double exact_AWG_sqmm){

        int sqmm_sizes = GeneralCalculations.CopperWireArray.length;

        if (exact_AWG_sqmm >= GeneralCalculations.CopperWireArray [sqmm_sizes-1]){//largest than larger sqmm
            mm2_equiv = 120; // next size to 95sqmm is 120sqmm
        }
        else{


            double diff_up, diff_down;

            for (int i = 1; i < sqmm_sizes; i++){

                if (exact_AWG_sqmm < GeneralCalculations.CopperWireArray[i]){

                    diff_down = exact_AWG_sqmm - GeneralCalculations.CopperWireArray [i-1];
                    diff_up = exact_AWG_sqmm - GeneralCalculations.CopperWireArray [i];
                    diff_up = Math.abs (diff_up);

                    if (diff_down > diff_up ){
                        mm2_equiv = GeneralCalculations.CopperWireArray [i];
                    }

                    else{

                        mm2_equiv = GeneralCalculations.CopperWireArray [i-1];
                    }

                    break;
                }

            }

        }

        TextView TV_sqmm_equiv = (TextView) findViewById(R.id.TV_size_equiv);
        String Str_sqmm_equiv = String.format (Locale.UK, "%.1f", mm2_equiv);
        Str_sqmm_equiv = Str_sqmm_equiv + " sqmm";
        TV_sqmm_equiv.setText(Str_sqmm_equiv);



    }

    private void find_AWG_equivalent(){

        TextView TV_AWG_equiv = (TextView) findViewById(R.id.TV_size_equiv);
        String Str_AWG_equiv;
        double diff_up, diff_down, previous;

        if (I_know_mm2 > 42.4) { //AWG 1

            if (I_know_mm2 < 53.47){

                diff_up = 53.47 - I_know_mm2;
                diff_down = I_know_mm2 - 42.4;

                if (diff_up > diff_down){
                    Str_AWG_equiv = "AWG 1";
                }
                else Str_AWG_equiv = "AWG (1/0)";

            }
            else if (I_know_mm2 < 67.43){

                diff_up =  67.43 - I_know_mm2;
                diff_down = I_know_mm2 - 53.47;

                if (diff_up > diff_down){
                    Str_AWG_equiv = "AWG (1/0)";
                }
                else Str_AWG_equiv = "AWG (2/0)";

            }

            else if (I_know_mm2 < 85.02){

                diff_up = 85.02 - I_know_mm2;
                diff_down = I_know_mm2 - 67.43;

                if (diff_up > diff_down){
                    Str_AWG_equiv = "AWG (2/0)";
                }
                else Str_AWG_equiv = "AWG (3/0)";

            }

            else if (I_know_mm2 < 107.21){

                diff_up = 107.21 - I_know_mm2;
                diff_down = I_know_mm2 - 85.02;

                if (diff_up > diff_down){
                    Str_AWG_equiv = "AWG (3/0)";
                }
                else Str_AWG_equiv = "AWG (4/0)";

            }

            else Str_AWG_equiv = "AWG (4/0)";

            TV_AWG_equiv.setText(Str_AWG_equiv);

        }

        else{

            // mm2 to AWG formula converter:

            // Formula: mm2 = 0.012668 [mm2] x 92^((36-n)/19.5)

            int AWG_sizes = AWG_size_list.length; // array goes from AWG 1 to AWG 20, does not include (1/0)-(4/0)

            double AWG_exact_mm2_dummy = 0;

            for ( int i = 0; i < AWG_sizes; i++){

                int n = AWG_size_list [i];
                previous = AWG_exact_mm2_dummy;
                AWG_exact_mm2_dummy = (36-n)/19.5;
                AWG_exact_mm2_dummy = 0.012668 * Math.pow(92,AWG_exact_mm2_dummy);

                if (AWG_exact_mm2_dummy < I_know_mm2){

                    diff_up = previous - I_know_mm2;
                    diff_down = I_know_mm2 - AWG_exact_mm2_dummy;

                    if (diff_up > diff_down) {
                        AWG_equiv = AWG_size_list [i];
                    }
                    else {
                        AWG_equiv = AWG_size_list [i-1];
                    }

                    break;
                }

                else {

                    AWG_equiv = 20; //smallest AWG in the list (area = 0.5176)
                }

            }

            Str_AWG_equiv = Integer.toString(AWG_equiv);
            Str_AWG_equiv = "AWG " + Str_AWG_equiv;
            TV_AWG_equiv.setText(Str_AWG_equiv);

        }

    }

    private void populateList() {

        list = new ArrayList <HashMap>();

        //data source: https://www.rapidtables.com/calc/wire/awg-to-mm.html

        HashMap temp = new HashMap();
        temp.put(FIRST_COLUMN, "(4/0)");
        temp.put(SECOND_COLUMN, "11.6840");
        temp.put(THIRD_COLUMN, "107.2193");
        list.add(temp);

        HashMap temp1 = new HashMap();
        temp1.put(FIRST_COLUMN, "(3/0)");
        temp1.put(SECOND_COLUMN, "10.4049");
        temp1.put(THIRD_COLUMN, "85.0288");
        list.add(temp1);

        HashMap temp2 = new HashMap();
        temp2.put(FIRST_COLUMN, "(2/0)");
        temp2.put(SECOND_COLUMN, "9.2658");
        temp2.put(THIRD_COLUMN, "67.4309");
        list.add(temp2);

        HashMap temp3 = new HashMap();
        temp3.put(FIRST_COLUMN, "(1/0)");
        temp3.put(SECOND_COLUMN, "8.2515");
        temp3.put(THIRD_COLUMN, "53.4751");
        list.add(temp3);

        HashMap temp4 = new HashMap();
        temp4.put(FIRST_COLUMN, "1");
        temp4.put(SECOND_COLUMN, "7.3481");
        temp4.put(THIRD_COLUMN, "42.4077");
        list.add(temp4);


        HashMap temp5 = new HashMap();
        temp5.put(FIRST_COLUMN, "2");
        temp5.put(SECOND_COLUMN, "6.5437");
        temp5.put(THIRD_COLUMN, "33.6308");
        list.add(temp5);

        HashMap temp6 = new HashMap();
        temp6.put(FIRST_COLUMN, "3");
        temp6.put(SECOND_COLUMN, "5.8273");
        temp6.put(THIRD_COLUMN, "26.6705");
        list.add(temp6);

        HashMap temp7 = new HashMap();
        temp7.put(FIRST_COLUMN, "4");
        temp7.put(SECOND_COLUMN, "5.1894");
        temp7.put(THIRD_COLUMN, "21.1506");
        list.add(temp7);

        HashMap temp8 = new HashMap();
        temp8.put(FIRST_COLUMN, "5");
        temp8.put(SECOND_COLUMN, "4.6213");
        temp8.put(THIRD_COLUMN, "16.7732");
        list.add(temp8);

        HashMap temp9 = new HashMap();
        temp9.put(FIRST_COLUMN, "6");
        temp9.put(SECOND_COLUMN, "4.1154");
        temp9.put(THIRD_COLUMN, "13.3018");
        list.add(temp9);

        HashMap temp10 = new HashMap();
        temp10.put(FIRST_COLUMN, "7");
        temp10.put(SECOND_COLUMN, "3.6649");
        temp10.put(THIRD_COLUMN, "10.5488");
        list.add(temp10);

        HashMap temp11 = new HashMap();
        temp11.put(FIRST_COLUMN, "8");
        temp11.put(SECOND_COLUMN, "3.2636");
        temp11.put(THIRD_COLUMN, "8.3656");
        list.add(temp11);

        HashMap temp12 = new HashMap();
        temp12.put(FIRST_COLUMN, "9");
        temp12.put(SECOND_COLUMN, "2.9064");
        temp12.put(THIRD_COLUMN, "6.6342");
        list.add(temp12);

        HashMap temp13 = new HashMap();
        temp13.put(FIRST_COLUMN, "10");
        temp13.put(SECOND_COLUMN, "2.5882");
        temp13.put(THIRD_COLUMN, "5.2612");
        list.add(temp13);

        HashMap temp14 = new HashMap();
        temp14.put(FIRST_COLUMN, "11");
        temp14.put(SECOND_COLUMN, "2.3048");
        temp14.put(THIRD_COLUMN, "4.1723");
        list.add(temp14);

        HashMap temp15 = new HashMap();
        temp15.put(FIRST_COLUMN, "12");
        temp15.put(SECOND_COLUMN, "2.0525");
        temp15.put(THIRD_COLUMN, "3.3088");
        list.add(temp15);

        HashMap temp16 = new HashMap();
        temp16.put(FIRST_COLUMN, "13");
        temp16.put(SECOND_COLUMN, "1.8278");
        temp16.put(THIRD_COLUMN, "2.6240");
        list.add(temp16);

        HashMap temp17 = new HashMap();
        temp17.put(FIRST_COLUMN, "14");
        temp17.put(SECOND_COLUMN, "1.6277");
        temp17.put(THIRD_COLUMN, "2.0809");
        list.add(temp17);

        HashMap temp18 = new HashMap();
        temp18.put(FIRST_COLUMN, "15");
        temp18.put(SECOND_COLUMN, "1.4495");
        temp18.put(THIRD_COLUMN, "1.6502");
        list.add(temp18);

        HashMap temp19 = new HashMap();
        temp19.put(FIRST_COLUMN, "16");
        temp19.put(SECOND_COLUMN, "1.2908");
        temp19.put(THIRD_COLUMN, "1.3087");
        list.add(temp19);

        HashMap temp20 = new HashMap();
        temp20.put(FIRST_COLUMN, "17");
        temp20.put(SECOND_COLUMN, "1.1495");
        temp20.put(THIRD_COLUMN, "1.0378");
        list.add(temp20);

        HashMap temp21 = new HashMap();
        temp21.put(FIRST_COLUMN, "18");
        temp21.put(SECOND_COLUMN, "1.0237");
        temp21.put(THIRD_COLUMN, "0.8230");
        list.add(temp21);

        HashMap temp22 = new HashMap();
        temp22.put(FIRST_COLUMN, "19");
        temp22.put(SECOND_COLUMN, "0.9116");
        temp22.put(THIRD_COLUMN, "0.6527");
        list.add(temp22);

        HashMap temp23 = new HashMap();
        temp23.put(FIRST_COLUMN, "20");
        temp23.put(SECOND_COLUMN, "0.8118");
        temp23.put(THIRD_COLUMN, "0.5176");
        list.add(temp23);
    }

}