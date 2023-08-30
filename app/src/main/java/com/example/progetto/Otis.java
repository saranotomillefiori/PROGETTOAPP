package com.example.progetto;

import java.io.IOException;

public class Otis {

        static float IBW;
        static float f;

    public static void calculatewithOtisFormula( String gender, double Res, double Comp, double MinVolume, double RR, double weight, double height) throws IOException {

        IBW = (float) (22 * (height*height));
           /* if (gender.equals("Female")) {
                //IBW = (float) (45.5 + (0.91 * (height - 152.4))); // cambiata

            }
            else {
                IBW = (float) (50 + (0.91 * (height - 152.4)));
                }*/

            float deadSpace = (float) (2.2 / weight);
            float VE = (float) MinVolume * (IBW/750);

            f = (float) ((1 + 0.66 *(Res * Comp)*(VE - (RR * deadSpace))/deadSpace ) -1/(0.33* Res* Comp ));
        }

    public static float OtisChartDistance(double VE, double RR, double weight){
        //double tidalVolume = 4.4 * weight;
        double tidalVolume = 0.1 * weight;
        return (float)Math.sqrt((RR*RR + f - 2*f*RR + (VE / RR)*(VE / RR)+ tidalVolume*tidalVolume - (2*tidalVolume*VE) / RR ));
        }
}
