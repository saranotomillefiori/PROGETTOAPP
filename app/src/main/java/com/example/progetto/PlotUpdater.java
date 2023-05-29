package com.example.progetto;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.example.progetto.MainActivity3;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lungsimulator.exceptions.InspireException;
import lungsimulator.interfaceclass.LungSimulatorInterface;

public class PlotUpdater implements Runnable {

    LungSimulatorInterface simulator;
    String status;
    MainActivity3 parentActivity;

    public PlotUpdater(double R, double C, String gender, int age, double h, double w, double step, MainActivity3 parentActivity) throws IOException {
        String modelDescription = "---\n" +
                "schema: 2\n" +
                "elementsList:\n" +
                "- elementName: Resistance\n" +
                "  associatedFormula:\n" +
                "    isTimeDependent: false\n" +
                "    isExternal: false\n" +
                "    formula: resistance1\n" +
                "    variables:\n" +
                "    - resistance1\n" +
                "  type: ResistorElm\n" +
                "  x: 0\n" +
                "  y: 1\n" +
                "  x1: 2 \n" +
                "  y1: 1\n" +
                "- elementName: Compliance\n" +
                "  associatedFormula:\n" +
                "    isTimeDependent: false\n" +
                "    isExternal: false\n" +
                "    formula: capacitor1\n" +
                "    variables:\n" +
                "    - capacitor1\n" +
                "  type: CapacitorElm\n" +
                "  x: 2\n" +
                "  y: 1\n" +
                "  x1: 4 \n" +
                "  y1: 1\n" +
                "  showLeft: true\n" +
                "  idLeft: Alveoli\n" +
                "  showRight: true\n" +
                "  idRight: Intrathoracic Pressure\n" +
                "- elementName: Ventilator\n" +
                "  associatedFormula:\n" +
                "    isTimeDependent: false\n" +
                "    isExternal: true\n" +
                "  type: ExternalVoltageElm\n" +
                "  x: 4\n" +
                "  y: 1\n" +
                "  x1: 0 \n" +
                "  y1: 1";
        // Build the simulator with simple RC circuit
        simulator = new LungSimulatorInterface(modelDescription);
        simulator.setRandC(R, C);
        simulator.setDemographicData(gender, age, h, w);
        // Check the correctness and completeness of data
        try {
            simulator.initializeSimulator();
            // Simulation step
            simulator.setStep(step);
            // Start the simulation and set the initial time
            simulator.startSimulation();
            status = "Start";
            // Set the parent activity
            this.parentActivity = parentActivity;
        } catch(InspireException ex) {
            Log.d("t", ex.getMessage());
            status = "Stop";
        }
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
                Log.d("t","Update flow chart");
                updateFlowChart();
                Log.d("t","Update pressure chart");
                updatePressureChart();
            } else if (status.equals("Stop")) {
                break;
            } else if (status.equals("Freeze")) {
                // Do nothing
            }
        }
    }

    public void updateFlowChart() {
        try {
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
            dataSet3.setColor(Color.GREEN);
            dataSet3.setDrawCircles(false);
            dataSet3.setDrawValues(false);
            dataSet3.setValueTextColor(Color.BLACK);
            // Set the CHART3 dataset to that previously build
            LineData lineData3 = new LineData(dataSet3);
            parentActivity.chart3.setData(lineData3);
            parentActivity.chart3.invalidate();
        } catch (IndexOutOfBoundsException ex) {}
    }

    public void updatePressureChart() {
        try {
            // Get the values of the last 50 pressures measured
            List<Double> pressureValues = simulator.getPressure();
            // Build the list of entries
            List<Entry> entries2 = new ArrayList<Entry>();
            int index = 0;
            for (double pressure : pressureValues) {
                entries2.add(new Entry(index, (float) pressure));
                index++;
            }
            // Build the dataset
            LineDataSet dataSet2 = new LineDataSet(entries2, "Pressure / Time"); // add entries to dataset
            dataSet2.setColor(Color.BLUE);
            dataSet2.setDrawCircles(false);
            dataSet2.setDrawValues(false);
            dataSet2.setValueTextColor(Color.BLACK);
            // Set the CHART2 dataset to that previously build
            LineData lineData2 = new LineData(dataSet2);
            parentActivity.chart2.setData(lineData2);
            parentActivity.chart2.invalidate();
        } catch (IndexOutOfBoundsException ex) {}
    }

}
