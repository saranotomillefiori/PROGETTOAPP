package com.example.progetto;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.widget.ImageView;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lungsimulator.exceptions.InspireException;
import lungsimulator.interfaceclass.LungSimulatorInterface;

/**
 * This class implements the business logic that updates the three available
 * plots:
 * - Pressure
 * - Flow
 * - Otis semaphore
 */

public class PlotUpdater implements Runnable {

    LungSimulatorInterface simulator;
    String status;
    Ventilator ventilator;
    MainActivity3 parentActivity;

    public double res;
    public double c;
    public String gender;
    public double minVolume;
    public double rr;
    public double weight;
    public double height;
    public double ie;
    public double peep;
    public double vMax;

    public PlotUpdater(double res, double c, String gender, int age, double h,
                       double w, double step, double rr, double ie, double vMax,
                       double peep, double minVol, MainActivity3 parentActivity)
            throws IOException {
        String modelDescription = """
                ---
                schema: 2
                elementsList:
                - elementName: Resistance
                  associatedFormula:
                    isTimeDependent: false
                    isExternal: false
                    formula: resistance1
                    variables:
                    - resistance1
                  type: ResistorElm
                  x: 0
                  y: 1
                  x1: 2\s
                  y1: 1
                - elementName: Compliance
                  associatedFormula:
                    isTimeDependent: false
                    isExternal: false
                    formula: capacitor1
                    variables:
                    - capacitor1
                  type: CapacitorElm
                  x: 2
                  y: 1
                  x1: 4\s
                  y1: 1
                  showLeft: true
                  idLeft: Alveoli
                  showRight: true
                  idRight: Intrathoracic Pressure
                - elementName: Ventilator
                  associatedFormula:
                    isTimeDependent: false
                    isExternal: true
                  type: ExternalVoltageElm
                  x: 4
                  y: 1
                  x1: 0\s
                  y1: 1""";
        ventilator = new Ventilator(rr, ie, vMax, peep);
        // DEVO SCRIVERE LA STESSA RIGA DI CODICE PER IL SIMULATORE CON L'ALTRO POP UP????
        // Build the simulator with simple RC circuit
        simulator = new LungSimulatorInterface(modelDescription);
        simulator.setRandC(res, c);
        simulator.setDemographicData(gender, age, h, w);
        // Check the correctness and completeness of data
        this.c = c;
        this.res = res;
        this.gender = gender;
        this.minVolume = minVol;
        this.rr = rr;
        this.weight = w;
        this.height = h;
        this.ie = ie;
        this.peep = peep;
        this.vMax = vMax;

        try {
            simulator.initializeSimulator();
            // Simulation step
            simulator.setStep(step);
            // Start the simulation and set the initial time
            simulator.startSimulation();
            Thread vVentilator = new Thread(ventilator);
            vVentilator.start();

            status = "Start";
            // Set the parent activity
            this.parentActivity = parentActivity; // come faccio a cambiarlo e passare contemporaneamente i parametri dai due pop up?
        } catch (InspireException ex) {
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
                simulator.simulationStep(ventilator.getValue());
                Log.d("t", "Update flow chart");
                updateFlowChart();
                Log.d("t", "Update pressure chart");
                updatePressureChart();
                Log.d("t", "Update otis chart");
                try {
                    Otis.calculatewithOtisFormula(
                            gender, res, c, minVolume, rr, weight, height);
                    float distance = Otis.OtisChartDistance(minVolume, rr, weight);
                    calculateOtis(distance);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (status.equals("Stop")) {
                break;
            }
        }
    }

    public void calculateOtis(float distance) {
        // TODO: The thresholds have to be set in a reasonable way
        if (distance <= 3) {
            ImageView imageView = (ImageView) parentActivity.findViewById(
                    R.id.imageViewSemaforo);
            imageView.setImageResource(R.drawable.semverdesupernova);
        } else if (distance > 3 && distance < 5) {
            ImageView imageView = (ImageView) parentActivity.findViewById(
                    R.id.imageViewSemaforo);
            imageView.setImageResource(R.drawable.semgiallosupernova);
        } else if (distance >= 5) {
            ImageView imageView = (ImageView) parentActivity.findViewById(
                    R.id.imageViewSemaforo);
            imageView.setImageResource(R.drawable.semrossosupernova);
        }
    }

    public void updateFlowChart() {
        // Get the values of the last 50 flows measured
        List<Double> flowValues = simulator.getFlow();
        // Build the list of entries
        List<Entry> entries3 = new ArrayList<>();
        int index = 0;
        for (double flow : flowValues) {
            entries3.add(new Entry(index, (float) flow));
            index++;
        }
        // Build the dataset
        LineDataSet dataSet3 = new LineDataSet(entries3, "AirFlow over Time");
        dataSet3.setColor(Color.GREEN);
        dataSet3.setDrawCircles(false);
        dataSet3.setDrawValues(false);
        dataSet3.setValueTextColor(Color.BLACK);
        dataSet3.setLineWidth(3);
        int color = R.color.blu;
        dataSet3.setColor(color);
        // Set the CHART3 dataset to that previously build
        LineData lineData3 = new LineData(dataSet3);
        parentActivity.chart3.setData(lineData3);
        parentActivity.chart3.invalidate();
        Description description = new Description();
        description.setText("Air Flow");
        description.setTextSize(18);
        description.setTextColor(color);
        description.setTextAlign(Paint.Align.RIGHT);
        parentActivity.chart3.setDescription(description);
    }

    public void updatePressureChart() {
        List<Double> pressureValues = simulator.getPressure();
        List<Entry> entries2 = new ArrayList<>();
        int index = 0;
        for (double pressure : pressureValues) {
            entries2.add(new Entry(index, (float) pressure));
            index++;
        }
        // Build the dataset
        LineDataSet dataSet2 = new LineDataSet(entries2, "Pressure / Time");
        dataSet2.setColor(Color.BLUE);
        dataSet2.setDrawCircles(false);
        dataSet2.setDrawValues(false);
        dataSet2.setValueTextColor(Color.BLACK);
        dataSet2.setLabel("PRESSURE OVER TIME");
        dataSet2.setLineWidth(3);
        int color = R.color.blu;
        dataSet2.setColor(color);
        // Set the CHART2 dataset to that previously build
        LineData lineData2 = new LineData(dataSet2);
        parentActivity.chart2.setData(lineData2);
        parentActivity.chart2.invalidate();
        Description description = new Description();
        description.setText("Pressure");
        description.setTextSize(18);
        description.setTextColor(color);
        parentActivity.chart2.setDescription(description);
    }
}