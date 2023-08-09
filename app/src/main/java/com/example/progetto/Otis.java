package com.example.progetto;

import android.widget.ImageView;

import java.io.IOException;

public class Otis implements Runnable {
    //LungSimulatorInterface simulator;
    //String status;
    //Ventilator ventilator;
   MainActivity3 parentActivity;
    PopActivityPatient parentActivity2;
    PopActivityVentilator parentActivity3;
   // private double ventoutput;



    public class OtisFormula {
        static float IBW;
        static float f;
       // float tidalVolume;

        public static float calculatewithOtisFormula( String gender, double Res, double Comp, double MinVolume, double RR, double weight, double height, PopActivityPatient parentActivity2, PopActivityVentilator parentActivity3, MainActivity3 parentactivity) throws IOException {

            if (gender.equals("Female")) { // come chiamiamo lo spinner? static va bene?
                IBW = (float) (45.5 + (0.91 * (height - 152.4)));
            }
            else {
                IBW = (float) (50 + (0.91 * (height - 152.4)));
                }

           // float tidalVolume = (float) (4.4 * IBW);
            float deadSpace = (float) (2.2 / weight);

            return f = (float) ((1 + 0.66 *(Res * Comp)*(MinVolume - (RR * deadSpace))/deadSpace ) -1/(0.33* Res* Comp ));
        }
    }
    float distance;
    public float OtisChartDistance(double Res, double Comp, double MinVolume, double RR, double weight, double height,  PopActivityPatient parentActivity2, PopActivityVentilator parentActivity3){
        double tidalVolume = 4.4 * weight;
        // formula della distanza
        return distance = (float)Math.sqrt((RR*RR + OtisFormula.f - 2*OtisFormula.f*RR + (MinVolume / RR)*(MinVolume / RR)+ tidalVolume*tidalVolume - (2*tidalVolume*MinVolume) / RR ));
                // se la distanza supera un valore x accendo rosso altrimenti verde altrimenti arancio

        }


// start stop e status li inserisco nel plot updater o qui, se qui dove?

    @Override
    public void run() {
        while (true) {
            try {

                if (distance <= 10) { // i valori????
                    ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                    imageView.setImageResource(R.drawable.semverdesupernova);

                }
                else if (distance>10 && distance < 20) {
                    ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                    imageView.setImageResource(R.drawable.semgiallosupernova);
                }
                else if (distance >= 20 ) {
                    ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                    imageView.setImageResource(R.drawable.semrossosupernova);
                }
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
            }
            }

        }
