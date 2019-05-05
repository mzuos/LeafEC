package com.EWB_Tonibung.mcbcalculator;


import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class listview_adapter_array extends BaseAdapter
{
    public ArrayList<String> list_sqmm;
    public ArrayList<String> list_VD;
    public ArrayList<String> list_VDPC;
    Activity activity;
    public double chosen_wire;

    public listview_adapter_array
            (Activity activity, double chosen_wire, ArrayList<String> list_sqmm, ArrayList<String> list_VD, ArrayList<String> list_VDPC ) {
        super();
        this.activity = activity;
        this.list_sqmm = list_sqmm;
        this.list_VD = list_VD;
        this.list_VDPC = list_VDPC;
        this.chosen_wire = chosen_wire;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list_sqmm.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list_sqmm.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        // TODO Auto-generated method stub
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_rowlayout, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
            holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.txtFirst.setText(list_sqmm.get(position));
        holder.txtSecond.setText(list_VD.get(position));
        holder.txtThird.setText(list_VDPC.get(position));

        String dummy_sqmm = list_sqmm.get(position);
        String [] dummy_sqmm_array  = dummy_sqmm.split (" ");
        dummy_sqmm = dummy_sqmm_array [0];

        double display_sqmm = Float.parseFloat(dummy_sqmm);


        if (display_sqmm  < chosen_wire){

            holder.txtFirst.setTextColor(Color.RED);
            holder.txtSecond.setTextColor(Color.RED);
            holder.txtThird.setTextColor(Color.RED);
            holder.txtFirst.setTypeface(null, Typeface.NORMAL);
            holder.txtSecond.setTypeface(null, Typeface.NORMAL);
            holder.txtThird.setTypeface(null, Typeface.NORMAL);
        }

        else if (display_sqmm  > chosen_wire){
            holder.txtFirst.setTextColor(Color.parseColor("#6CB876"));
            holder.txtSecond.setTextColor(Color.parseColor("#6CB876"));
            holder.txtThird.setTextColor(Color.parseColor("#6CB876"));
            holder.txtFirst.setTypeface(null, Typeface.NORMAL);
            holder.txtSecond.setTypeface(null, Typeface.NORMAL);
            holder.txtThird.setTypeface(null, Typeface.NORMAL);
        }
        else{

            holder.txtFirst.setTypeface(null, Typeface.BOLD);
            holder.txtSecond.setTypeface(null, Typeface.BOLD);
            holder.txtThird.setTypeface(null, Typeface.BOLD);
            holder.txtFirst.setTextSize (16);
            holder.txtSecond.setTextSize (16);
            holder.txtThird.setTextSize (16);
            holder.txtSecond.setTypeface(null, Typeface.BOLD);
            holder.txtThird.setTypeface(null, Typeface.BOLD);
            holder.txtFirst.setTextColor(Color.parseColor("#6CB876"));
            holder.txtSecond.setTextColor(Color.parseColor("#6CB876"));
            holder.txtThird.setTextColor(Color.parseColor("#6CB876"));

        }



        return convertView;
    }

}

