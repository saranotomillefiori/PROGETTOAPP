package com.example.progetto;


import java.io.IOException;

import lungsimulator.interfaceclass.LungSimulatorInterface;

public class Ventilator implements Runnable{
    LungSimulatorInterface simulator;
    int value;
    private double VMAX;
    private double PEEP;
    private double RR;
    private double IE;

   // String status;

    MainActivity3 parentActivity;
    PopActivityVentilator parentActivity3;
    PopActivityPatient parentActivity2;
    public Ventilator(double RR, double IE, double VMAX, double PEEP, PopActivityVentilator parentActivity) throws
            IOException
    {this.RR=RR; this.IE=IE; this.VMAX=VMAX; this.PEEP=PEEP; // this.parentActivity3=parentActivity3; this.parentActivity2= parentActivity2;
    } // anche qui come metto tutti i pop up? con una lista progressiva di this? o basta così?
    public int getvalue()  {return value;}
    @Override
    public void run() {
        while (true) { // il true poi quando funziona lo cambiamo con una variabile boolean che richiama i bottoni, così da fare andare il run solo quando si preme il bottone e stopparlo se necessario
            value = (int)VMAX; //capiamo come si richiama il dato dall'altra pagina
            try {
                Thread.sleep((int) ((60/RR)/(1+IE))); // inspiro // i grafici vanno bene così?
            } catch (InterruptedException e) {
                throw new RuntimeException(e);

            }
            value =(int)PEEP;
            try {
                Thread.sleep((int)((60/RR)*(1-(1/(1+IE))))); // espiro
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }






