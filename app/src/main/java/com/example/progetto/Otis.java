package com.example.progetto;

import android.util.Log;

import java.io.IOException;

import lungsimulator.interfaceclass.LungSimulatorInterface;

public class Otis implements Runnable {
    LungSimulatorInterface simulator;
    String status;
    Ventilator ventilator;
    MainActivity3 parentActivity;
    private double ventoutput;
    public Otis(double Res, double Comp, double MinVolume, double RR, double weight, MainActivity3 parentActivity) throws IOException {
    //sqrt()/()
    }

    void setStatus(String status) {
        this.status = status;
    }
    
        @Override
    public void run() {
            while (true) {
                if (status.equals("Start")) {
                    simulator.simulationStep(ventilator.getvalue()); // calcola la distanza e mostra la foto
                    Log.d("t", "Update Otis chart");
                    updateOtisChart();
                } else if (status.equals("Stop")) {
                    break;
                } else if (status.equals("Freeze")) {
                    // Do nothing
                }
            }
    }

    private void updateOtisChart() {
    }
}
