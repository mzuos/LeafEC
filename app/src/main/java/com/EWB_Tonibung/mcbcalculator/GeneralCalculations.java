package com.EWB_Tonibung.mcbcalculator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Boolean.TRUE;

public class
GeneralCalculations {

 // MCB and Cable catalogue

    // Preferred values are: The preferred values are 6, 8, 10, 13, 16, 20, 25, 32, 40, 50, 63, 80, 100 and 125 A

    public static int[] DC_MCB_Catalogue = new int[] {3, 6, 10, 16, 20, 25, 32, 40, 50, 63, 80, 100, 125};

    //original catalog (based on available on Lazada)

    public static int[] AC_MCB_Catalogue = new int[]{1, 2, 4, 6, 10, 13, 16, 20, 25, 32, 40, 50, 63, 80, 100, 125};

    //****** Data from 17th Edition of BS7671 for Single Core Copper Conductors, Unarmoured, PVC ********

    //**** Clipped direct *****

    // Same values used for copper twin flat cable
    public static double [] CopperWireArray = new double[] {0.5, 1, 1.5, 2.5, 4, 6, 10, 16, 25, 35, 50, 70, 95, 120, 150, 185, 240};

    //Current Rating for Clipped Direct, DC or 1phAC:
    public static double [] Rating_Cu_Clipped_1phAC_DC = new double [] {3, 15.5, 20, 27, 37, 47, 65, 87, 114, 141, 182, 234, 284, 330, 381, 436, 515};

    //Current Rating for Clipped Direct, 3phAC:
    public static double [] Rating_Cu_Clipped_3phAC = new double [] {3, 14, 18, 25, 33, 43, 59, 79, 104, 129, 167, 214, 261, 303, 349, 400, 472};


    //Clipped direct - volt drop DC in(mV/A*m)
    public static double [] VD_Cu_Clipped_DC = new double [] { 93, 44, 29, 18, 11, 7.3, 4.4, 2.8, 1.75, 1.25, 0.93, 0.63, 0.46, 0.36, 0.29, 0.23, 0.18};

    //Clipped direct - volt drop AC-1ph in(mV/A*m)
    public static double [] VD_Cu_Clipped_1phAC = new double [] { 93, 44, 29, 18, 11, 7.3, 4.4, 2.8, 1.80, 1.30, 0.97, 0.69, 0.54, 0.45, 0.39, 0.35, 0.31};

    //Clipped direct - volt drop AC-3ph in(mV/A*m)
    public static double [] VD_Cu_Clipped_3phAC = new double [] { 80, 38, 25, 15, 9.5, 6.4, 3.8, 2.4, 1.55, 1.15, 0.86, 0.63, 0.51, 0.44, 0.4, 0.36, 0.34};


    //**** In Conduit *****

    //Current Rating in conduit, DC or 1phAC
    public static double [] Rating_Cu_Conduit__1phAC_DC = new double [] {3, 13.5, 17.5, 24, 32, 41, 57, 76, 101, 125, 151, 192, 232, 269, 300, 341, 400};

    //Current Rating in conduit, 3phAC
    public static double [] Rating_Cu_Conduit__3phAC = new double [] {3, 12, 15.5, 21, 28, 36, 50, 68, 89, 110, 134, 171, 207, 239, 262, 296, 346};


    //In conduit - volt drop DC in(mV/A*m) (DC volt drop is the same in conduit or clipped)
    public static double [] VD_Cu_Conduit_DC = new double [] { 93, 44, 29, 18, 11, 7.3, 4.4, 2.8, 1.75, 1.25, 0.93, 0.63, 0.46, 0.36, 0.29, 0.23, 0.18};

    //In conduit - volt drop AC-1ph in(mV/A*m)
    public static double [] VD_Cu_Conduit_1phAC = new double [] { 93, 44, 29, 18, 11, 7.3, 4.4, 2.8, 1.80, 1.30, 1.00, 0.72, 0.56, 0.47, 0.41, 0.37, 0.33};

    //In conduit - volt drop AC-3ph in(mV/A*m)
    public static double [] VD_Cu_Conduit_3phAC = new double [] { 80, 38, 25, 15, 9.5, 6.4, 3.8, 2.4, 1.55, 1.10, 0.85, 0.61, 0.48, 0.41, 0.36, 0.32, 0.29};


    // ************** ALUMINIUM CABLES *******************************************************

    /* ** NOT USED ** Data from 17th Edition of BS7671 for Multicore non-armoured aluminium cables, PVC
    // no data available for single core aluminium smaller than 50mm2 ** NOT USED **
    public static double [] AlumWireArray = new double [] {16, 25, 35, 50, 70, 95};
    //Current Rating for cables in free Air
    public static double [] Rating_Al_Air= new double [] {73, 89, 111, 135, 173, 210};*/


    //Data from Aerial Bundle Aluminium cables, compliant with IEC 60227 (PVC) and IEC 60502 (XLPE)
    public static double [] AlumWireSize_IEC = new double [] {10, 16, 25, 35, 50, 70, 95, 120, 150, 240};

    //Current Rating for cables in free Air
    //Latest values from supplier, received 8 July 2019, Amp capacity at 30degC
    public static double [] Rating_Al_Air_IEC_PVC= new double [] {63, 88, 120, 146, 181, 232, 293, 342, 392, 547};
    public static double [] Rating_Al_Air_IEC_XLPE= new double [] {68, 93, 125, 150, 185, 235, 295, 345, 395, 550};



    //Resistance [Ohm/km] for volt drop
    public static double [] Ohm_Al_Air_IEC= new double [] {3.08, 1.91, 1.2, 0.868, 0.641, 0.443, 0.32, 0.253, 0.19, 0.119};



    //**************** ARRAY DECLARATION *********************************************

    // To be used by various functions within General Calculations class
    public static double [] AmpacityArray;
    public static double [] WireSizeArray;
    public static double [] VoltdropArray;
    public static int CableList;


    //********************** DC_MCB VOLTAGE RATING ******************************

    public static int DC_V_Rating_Calculator (double Vdesign){
        int DC_MCB_V_Rating = 0;

        if (Vdesign <= 125){ // typical DC ratings are either 125V or 625V
            DC_MCB_V_Rating = 125;
        }
        else if (Vdesign <= 625){
            DC_MCB_V_Rating = 625;
        }
        else{
            DC_MCB_V_Rating = -1;
        }
        return DC_MCB_V_Rating; //if the return is -1 we know something went wrong
    }

    //************************* DC_MCB CALCULATOR **********************************

    public static int DC_MCB_Calculator (double Imax){

        int MCBList = DC_MCB_Catalogue.length;
        int DC_MCBSize = 0;

        for (int i = 0; i < MCBList; i++) {

            if (Imax < DC_MCB_Catalogue[i]) {
                DC_MCBSize = DC_MCB_Catalogue[i];
                break;
            }
        }

        if (DC_MCBSize == 0) {// means it hasn't been able to find a big enough MCB
            DC_MCBSize = -1; // Negative value to show that there's no suitable MCB
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
        if (AC_MCBSize == 0) {// means it hasn't been able to find a big enough MCB
            AC_MCBSize = -1; // Negative value to show that there's no suitable MCB
        }
        return AC_MCBSize;
    }

    // ************** LOAD SPEC FOR CABLE TYPE ************

    public static void LoadCableSpec (int CableType, int LoadType){

        //CableType: 0 == Copper Clipped, 1 == Copper conduit, 2 == Al PVC, 3 == Al XLPE
        // LoadType: 0 == 1phAC, 1 == 3phAC, 2 == DC

        if (CableType==0 ||CableType==1){
            CableList = CopperWireArray.length;
            WireSizeArray = CopperWireArray;

            if (CableType == 0){
                if (LoadType == 1){
                    AmpacityArray = Rating_Cu_Clipped_3phAC;
                }
                else{
                    AmpacityArray = Rating_Cu_Clipped_1phAC_DC;
                }

            }
            else if (CableType == 1){
                if (LoadType == 1){
                    AmpacityArray = Rating_Cu_Conduit__3phAC;
                }
                else{
                    AmpacityArray = Rating_Cu_Conduit__1phAC_DC;
                }
            }
        }
        else if (CableType == 2 || CableType == 3) {
            CableList = AlumWireSize_IEC.length;
            WireSizeArray = AlumWireSize_IEC;

            if (CableType == 2){
                AmpacityArray=Rating_Al_Air_IEC_PVC;
            }
            else if (CableType == 3){
                AmpacityArray=Rating_Al_Air_IEC_XLPE;
            }

        }
    }

    //************************* CABLE RATING**********************************

    public static double CableCurrentRating (int CableType,int LoadType,double CableSize){

        double Rating = 0;

        // Load the ampacity and cable size arrays for the selected cable type
        LoadCableSpec (CableType, LoadType);

        // Find Cable Rating

        for (int w = 0; w < CableList; w++){
            if (CableSize == WireSizeArray[w]){
                   Rating = AmpacityArray[w];
                   break;
            }
        }

        return Rating;
    }


    //************************* CABLE SIZE FOR MCB CALCULATOR **********************************

    public static double CableSizeCalculator (int MCB_size,int CableType, int LoadType){

        // Load the ampacity and cable size arrays for the selected cable type
        LoadCableSpec (CableType, LoadType);

        // Calculate the required wire size

        double WireSize = -1; // Negative value to detect code malfunctioning

        for (int w = 0; w < CableList; w++){
            if (MCB_size < AmpacityArray[w]){
                WireSize = WireSizeArray[w];
                break;
            }
        }
        return WireSize;
    }

    //************************* MCB FOR CABLE CALCULATOR **********************************

    public static int Calculator_MCBforCable(double CableSize,int CableType, int LoadType){

        // Load the ampacity and cable size arrays for the selected cable type
        LoadCableSpec (CableType, LoadType);

        double WireAmpacity = 0;
        int MCBRating = 0;
        int MCBList = AC_MCB_Catalogue.length; // For Now, AC catalogue only


        // Find the amp rating of that wire size
        for (int w = 0; w < CableList; w++){
            if (CableSize == WireSizeArray[w]){
                WireAmpacity = AmpacityArray[w];
                WireAmpacity = WireAmpacity * 0.8; // we want the cable to be bigger than the MCB
                break;
            }
        }

        // Find MCB rating to be just smaller than cable rating

        for (int a =(MCBList-1); a >= 0; a--){
            if (AC_MCB_Catalogue[a] < WireAmpacity){
                MCBRating = AC_MCB_Catalogue[a];
                break;
            }
        }

        return MCBRating;
    }


    //************************* LOAD VOLT DROP VALUES  **********************************

    public static void LoadVoltDrop (int CableType, int LoadType){

        if (CableType == 2 || CableType ==3){
            VoltdropArray = Ohm_Al_Air_IEC;
        }
        else if (CableType == 0){ //Cu - clipped
            if (LoadType == 0){ //AC-1ph
                VoltdropArray = VD_Cu_Clipped_1phAC;
            }
            else if (LoadType == 1){ //AC-3ph
                VoltdropArray = VD_Cu_Clipped_3phAC;
            }
            else if (LoadType == 2) { //DC
                VoltdropArray = VD_Cu_Clipped_DC;
            }
        }
        else if (CableType == 1){ //Cu - conduit
            if (LoadType == 0){ //AC - 1ph
                VoltdropArray = VD_Cu_Conduit_1phAC;
            }
            else if (LoadType == 1){ //AC-3h
                VoltdropArray = VD_Cu_Conduit_3phAC;
            }
            else if (LoadType == 2) { //DC
                VoltdropArray = VD_Cu_Conduit_DC;
            }
        }

    }

    //************************* GET VOLT DROP RESISTANCE FOR GIVEN CABLE **********************************

    public static double VoltDropInfo (int CableType, int LoadType, double CableForLoad){

        // Load the ampacity and cable size arrays for the selected cable type
        LoadCableSpec (CableType, LoadType);

        // Load the correspondent volt drop array
        LoadVoltDrop(CableType, LoadType);

        double Ohm = 0;

        for (int w = 0; w < CableList; w++){
            if (CableForLoad == WireSizeArray[w]){
                Ohm = VoltdropArray[w];
                break;
            }
        }

        return Ohm;
    }


    //************************* FIND CABLE FOR MAX VOLT DROP **********************************

    public static double CableForVoltDrop (int CableType, int LoadType, double VD_goal){

        // Load the ampacity and cable size arrays for the selected cable type
        LoadCableSpec (CableType, LoadType);

        // Load the correspondent volt drop array
        LoadVoltDrop(CableType, LoadType);

        double CableforVD = 0;

        int w = 0;

        for ( w = 0; w < CableList; w++){

            if (VD_goal >= VoltdropArray [w]){
                CableforVD = WireSizeArray [w]; // just take the previous value, the one that just fullfills the requirement
                break;
            }
        }

        if ( (w == CableList) && (VD_goal < VoltdropArray [w-1])){ //means we didn't find a suitable cable, choose largest

            CableforVD = WireSizeArray [CableList-1];

        }

        return CableforVD;
    }


    public static int FindCablePosition (int CableType, int LoadType, double CableForLoad){

        LoadCableSpec (CableType,LoadType);

        int position = 0;

        for (int i = 0; i < CableList; i++){
            if (CableForLoad == WireSizeArray [i]){
                position = i;
                break;
            }
        }

        return position;

    }



}

