package com.EWB_Tonibung.mcbcalculator;

import static java.lang.Boolean.TRUE;

public class GeneralCalculations {

 // MCB and Cable catalogue

    public static int[] DC_MCB_Catalogue = new int[]{6, 10, 16, 32, 40, 63, 125};
    public static int[] AC_MCB_Catalogue = new int[]{6, 10, 16, 32, 40, 63, 125};

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
        return AC_MCBSize;
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

