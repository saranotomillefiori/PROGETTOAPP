package com.example.progetto;

import java.io.IOException;

public class Otis {
    //LungSimulatorInterface simulator;
    //String status;
    //Ventilator ventilator;
   MainActivity3 parentActivity;
    PopActivityPatient parentActivity2;
    PopActivityVentilator parentActivity3;
   // private double ventoutput;




        static float IBW;
        static float f;
       // float tidalVolume;

    public static void calculatewithOtisFormula( String gender, double Res, double Comp, double MinVolume, double RR, double weight, double height) throws IOException {

            if (gender.equals("Female")) { // come chiamiamo lo spinner? static va bene?
                IBW = (float) (45.5 + (0.91 * (height - 152.4)));
            }
            else {
                IBW = (float) (50 + (0.91 * (height - 152.4)));
                }

           // float tidalVolume = (float) (4.4 * IBW);
            float deadSpace = (float) (2.2 / weight);

            f = (float) ((1 + 0.66 *(Res * Comp)*(MinVolume - (RR * deadSpace))/deadSpace ) -1/(0.33* Res* Comp ));
        }


    public static float OtisChartDistance(double MinVolume, double RR, double weight){
        double tidalVolume = 4.4 * weight;
        // formula della distanza
        return (float)Math.sqrt((RR*RR + f - 2*f*RR + (MinVolume / RR)*(MinVolume / RR)+ tidalVolume*tidalVolume - (2*tidalVolume*MinVolume) / RR ));
                // se la distanza supera un valore x accendo rosso altrimenti verde altrimenti arancio

        }



}
