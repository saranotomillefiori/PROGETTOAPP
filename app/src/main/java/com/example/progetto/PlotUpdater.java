package com.example.progetto;

import android.graphics.Color;

import com.example.progetto.MainActivity3;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lungsimulator.interfaceclass.LungSimulatorInterface;

public class PlotUpdater implements Runnable {

    LungSimulatorInterface simulator;
    String status;
    MainActivity3 parentActivity;

    public PlotUpdater(double R, double C, String gender, int age, double h, double w, double step, MainActivity3 parentActivity) throws IOException {
        // Build the simulator with simple RC circuit
        simulator = new LungSimulatorInterface("libs/lung-model-Campbell-Brown.yaml");
        simulator.setRandC(R, C);
        simulator.setDemographicData(gender, age, h, w);
        // Check the correctness and completeness of data
        simulator.initializeSimulator();
        // Simulation step
        simulator.setStep(step);
        // Start the simulation and set the initial time
        simulator.startSimulation();
        status = "Start";
        // Set the parent activity
        this.parentActivity = parentActivity;
    }

    /**
     * Set the status of the com.example.progetto.PlotUpdater object
     *
     * @param status the status of the object (Start, Stop, Freeze)
     */
    void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void run() {
        while (true) {
            if (status.equals("Start")) {
                simulator.simulationStep(5);
                updateFlowChart();
                updatePressureChart();
            } else if (status.equals("Stop")) {
                break;
            } else if (status.equals("Freeze")) {
                // Do nothing
            }
        }
    }

    public void updateFlowChart() {
        // Get the values of the last 50 flows measured
        List<Double> flowValues = simulator.getFlow();
        // Build the list of entries
        List<Entry> entries3 = new ArrayList<Entry>();
        int index = 0;
        for (double flow : flowValues) {
            entries3.add(new Entry(index, (float)flow));
            index++;
        }
        // Build the dataset
        LineDataSet dataSet3 = new LineDataSet(entries3, "AirFlow / Time"); // add entries to dataset
        dataSet3.setColor(Color.BLUE);
        dataSet3.setValueTextColor(Color.BLACK);
        // Set the CHART3 dataset to that previously build
        LineData lineData3 = new LineData(dataSet3);
        parentActivity.chart3.setData(lineData3);
        parentActivity.chart3.invalidate();
    }

    public void updatePressureChart() {
        // Get the values of the last 50 pressures measured
        List<Double> pressureValues = simulator.getPressure();
        // Build the list of entries
        List<Entry> entries2 = new ArrayList<Entry>();
        int index = 0;
        for (double pressure : pressureValues) {
            entries2.add(new Entry(index, (float)pressure));
            index++;
        }
        // Build the dataset
        LineDataSet dataSet2 = new LineDataSet(entries2, "Pressure / Time"); // add entries to dataset
        dataSet2.setColor(Color.BLUE);
        dataSet2.setValueTextColor(Color.BLACK);
        // Set the CHART2 dataset to that previously build
        LineData lineData2 = new LineData(dataSet2);
        parentActivity.chart2.setData(lineData2);
        parentActivity.chart2.invalidate();
    }

}
