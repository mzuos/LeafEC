package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class GeneralCalculations {

 // MCB and Cable catalogue

    // Preferred values are: The preferred values are 6, 8, 10, 13, 16, 20, 25, 32, 40, 50, 63, 80, 100 and 125 A

    public static int[] DC_MCB_Catalogue = new int[]{6, 10, 16, 32, 40, 63, 125};
    public static int[] AC_MCB_Catalogue = new int[]{6, 10, 16, 20, 32, 40, 63, 125};

    //Data from 17th Edition of BS7671 for Single Core Copper Conductors, Unarmoured, PVC
    // Same values used for copper twin flat cable
    public static double [] CopperWireArray = new double[] {1, 1.5, 2.5, 4, 6, 10, 16, 25, 35, 50, 70, 95,120};
    //Current Rating for Clipped Direct:
    public static double [] Rating_Cu_Clipped= new double [] {15.5, 20, 27, 37, 47, 65, 87, 114, 141, 181, 234, 284, 330,};
    //Current Rating in conduit
    public static double [] Rating_Cu_Conduit = new double [] {12, 15.5, 21, 28, 36, 50, 68, 89, 110, 134, 171, 207,239};

    //Data from 17th Edition of BS7671 for Multicore non-armoured aluminium cables, PVC
    // no data available for single core aluminium smaller than 50mm2
    public static double [] AlumWireArray = new double [] {16, 25, 35, 50, 70, 95};
    //Current Rating for cables in free Air
    public static double [] Rating_Al_Air= new double [] {73, 89, 111, 135, 173, 210};

    //Data from Aerial Bundle Aluminium cables, compliant with IEC 60502 (PVC) and IEC 60502 (XLPE)
    public static double [] AlumWireSize_IEC = new double [] {10, 16, 25, 35, 50};
    //Current Rating for cables in free Air
    public static double [] Rating_Al_Air_IEC_PVC= new double [] {35, 56, 70, 90, 110};
    public static double [] Rating_Al_Air_IEC_XLPE= new double [] {47, 74, 102, 124, 157};
    //Resistance [Ohm/km] for volt drop
    public static double [] Ohm_Al_Air_IEC= new double [] {3.08, 1.91, 1.2, 0.868, 0.641};


    //************************* DC_MCB CALCULATOR **********************************

    public static int DC_MCB_Calculator (double Imax){

        int MCBList = DC_MCB_Catalogue.length;
        int DC_MCBSize=0;

        for (int i = 0; i < MCBList; i++) {

            if (Imax < DC_MCB_Catalogue[i]) {
                DC_MCBSize = DC_MCB_Catalogue[i];
                break;
            }
        }

        if (DC_MCBSize ==0) {// means it hasn't been able to find a big enough MCB
            DC_MCBSize = 71; // Random value so that I know we don't have a suitable MCB
        }

        return DC_MCBSize;

    }

    //************************* AC_MCB CALCULATOR **********************************

    public static int AC_MCB_Calculator (double Imax){

        int MCBList = AC_MCB_Catalogue.length;
        int AC_MCBSize=0;

        for (int i = 0; i < MCBList; i++) {

            if (Imax < AC_MCB_Catalogue[i]) {
                AC_MCBSize = AC_MCB_Catalogue[i];
                break;
            }
        }
        if (AC_MCBSize ==0) {// means it hasn't been able to find a big enough MCB
            AC_MCBSize = 71; // Random value so that I know we don't have a suitable MCB
        }
        return AC_MCBSize;
    }


    public static double CableCurrentRating (int CableType,double CableSize){
        double Rating=0;
       double [] AmpacityArray = new double []{};
       double [] WireSizeArray = new double[]{};
        int CableList = 0;

        // Get the ampacity and cable size arrays for the selected cable type

        if (CableType==0 ||CableType==1){
            CableList = CopperWireArray.length;
            WireSizeArray=CopperWireArray;

            if (CableType==0){
                AmpacityArray=Rating_Cu_Clipped;
            }
            else if (CableType==1){
                AmpacityArray=Rating_Cu_Conduit;
            }
        }
        else if (CableType==2) {
            CableList = AlumWireArray.length;
            WireSizeArray=AlumWireArray;
            AmpacityArray=Rating_Al_Air;

        }

        for (int w=0; w<CableList; w++){
            if (CableSize==WireSizeArray[w]){
                   Rating=AmpacityArray[w];
                   break;
            }
        }

        return Rating;
    }


    //************************* CABLE SIZE FOR MCB CALCULATOR **********************************

    public static double CableSizeCalculator (int MCB_size,int CableType){

        double [] WireSizeArray = new double[]{};
        double [] AmpacityArray = new double []{};

        // Get the ampacity and cable size arrays for the selected cable type

        double WireSize=-1; // Negative value to detect code malfunctioning

        int CableList = 0;

        if (CableType==0 ||CableType==1){
            CableList = CopperWireArray.length;
            WireSizeArray=CopperWireArray;

            if (CableType==0){
                AmpacityArray=Rating_Cu_Clipped;
            }
            else if (CableType==1){
                AmpacityArray=Rating_Cu_Conduit;
            }
        }
        else if (CableType==2) {
            CableList = AlumWireArray.length;
            WireSizeArray=AlumWireArray;
            AmpacityArray=Rating_Al_Air;

        }

        // Calculate the required wire size

        for (int w=0; w < CableList; w++){
            if (MCB_size<AmpacityArray[w]){
                WireSize= WireSizeArray[w];
                break;
            }
        }
        return WireSize;
    }

    //************************* MCB FOR CABLE CALCULATOR **********************************

    public static int Calculator_MCBforCable(double CableSize,int CableType){

        double [] WireSizeArray = new double[]{};
        double [] AmpacityArray = new double []{};

        // Get the ampacity and cable size arrays for the selected cable type

        double WireAmpacity=0;
        int MCBRating=0;
        int CableList = 0;
        int MCBList=AC_MCB_Catalogue.length; // For Now, AC catalogue only

        if (CableType==0 ||CableType==1){
            CableList = CopperWireArray.length;
            WireSizeArray=CopperWireArray;

            if (CableType==0){
                AmpacityArray=Rating_Cu_Clipped;
            }
            else if (CableType==1){
                AmpacityArray=Rating_Cu_Conduit;
            }
        }

        else if (CableType==2) {
            CableList = AlumWireArray.length;
            WireSizeArray=AlumWireArray;
            AmpacityArray=Rating_Al_Air;
        }

        // Find the amp rating of that wire size

        for (int w=0; w < CableList; w++){
            if (CableSize==WireSizeArray[w]){
                WireAmpacity = AmpacityArray[w];
                break;
            }
        }

        for (int a=(MCBList-1);a>=0;a--){
            if (AC_MCB_Catalogue[a]<WireAmpacity){
                MCBRating=AC_MCB_Catalogue[a];
                break;
            }
        }

        return MCBRating;
    }

}

