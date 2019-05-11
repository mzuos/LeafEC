package com.EWB_Tonibung.mcbcalculator;


import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class listview_adapter_cable_ratings extends BaseAdapter
{
    public ArrayList<String> list_sqmm;
    public ArrayList<String> list_Amp;
    public ArrayList<String> list_VD;
    Activity activity;

    public listview_adapter_cable_ratings
            (Activity activity,  ArrayList<String> list_sqmm, ArrayList<String> list_Amp, ArrayList<String> list_VD ) {
        super();
        this.activity = activity;
        this.list_sqmm = list_sqmm;
        this.list_Amp = list_Amp;
        this.list_VD = list_VD;
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
        holder.txtSecond.setText(list_Amp.get(position));
        holder.txtThird.setText(list_VD.get(position));

        return convertView;
    }

}

