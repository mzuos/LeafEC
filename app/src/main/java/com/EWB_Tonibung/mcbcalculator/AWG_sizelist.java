package com.EWB_Tonibung.mcbcalculator;

import static com.EWB_Tonibung.mcbcalculator.listview_constant.FIRST_COLUMN;
import static com.EWB_Tonibung.mcbcalculator.listview_constant.SECOND_COLUMN;
import static com.EWB_Tonibung.mcbcalculator.listview_constant.THIRD_COLUMN;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;

// CODE SOURCE: http://www.technotalkative.com/android-multi-column-listview/

public class AWG_sizelist extends AppCompatActivity{


    private ArrayList<HashMap> list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awg_sizelist);

        ListView lview = (ListView) findViewById(R.id.AWG_listview);
        populateList();
        listviewAdapter adapter = new listviewAdapter(this, list);
        lview.setAdapter(adapter);
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