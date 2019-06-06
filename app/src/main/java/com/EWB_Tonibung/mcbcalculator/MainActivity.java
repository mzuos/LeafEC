package com.EWB_Tonibung.mcbcalculator;


import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        int ScreenWidth = getScreenWidth();
        int ScreenHeight = getScreenHeight();
        double Leaf_w = ScreenWidth*0.5;
        double logos_w = Leaf_w*0.5;
        int Leaf_size=(int)Leaf_w;
        int Logo_size = (int) logos_w;
        ImageView Leaf_logo = (ImageView)findViewById(R.id.Leaf_logo);
        ImageView EWB_logo = (ImageView)findViewById(R.id.EWB_logo);
        ImageView Tonibung_logo = (ImageView)findViewById(R.id.Tonibung_logo);

        Leaf_logo.getLayoutParams().width = Leaf_size;
        Leaf_logo.getLayoutParams().height = Leaf_size;
        EWB_logo.getLayoutParams().width = Logo_size;
        EWB_logo.getLayoutParams().height = Logo_size;
        Tonibung_logo.getLayoutParams().width = Logo_size;
        Tonibung_logo.getLayoutParams().height = Logo_size;

    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    //Launched by selecting Main Menu Option 1
    public void LaunchSolarPVScreen(View view) {
        Intent intentSolarPVScreen = new Intent(this, PV_main_screen.class);
        startActivity(intentSolarPVScreen);
    }

    //Launched by selecting Main Menu Option 2
    public void LaunchGeneralCalcScreen(View view) {
        Intent intentMainCalcScreen = new Intent(this, GeneralCalcs_screen.class);
        startActivity(intentMainCalcScreen);
    }

    //Launched by selecting Main Menu Option 3
    public void LaunchResourcesScreen(View view) {
        Intent intentResourcesScreen = new Intent(this, Resources_screen.class);
        startActivity(intentResourcesScreen);
    }









}





