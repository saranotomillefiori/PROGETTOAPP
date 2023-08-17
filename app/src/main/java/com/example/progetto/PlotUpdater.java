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

public class PlotUpdater implements Runnable {

    LungSimulatorInterface simulator;
    String status;
    Ventilator ventilator;
    MainActivity3 parentActivity;

    public double Res;
    public double C;
    public String gender;
    public double MinVolume;
    public double RR;
    public double weight;
    public double height;
    public double IE;
    public double PEEP;
    public double VMAX;

    public PlotUpdater(double Res, double C, String gender, int age, double h, double w, double step,double RR, double IE, double VMAX, double PEEP, double MinVol, MainActivity3 parentActivity) throws IOException {
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
        ventilator= new Ventilator(RR,IE, VMAX, PEEP);
        // DEVO SCRIVERE LA STESSA RIGA DI CODICE PER IL SIMULATORE CON L'ALTRO POP UP????
        // Build the simulator with simple RC circuit
        simulator = new LungSimulatorInterface(modelDescription);
        simulator.setRandC(Res, C);
        simulator.setDemographicData(gender, age, h, w);
        // Check the correctness and completeness of data
        this.C = C;
        this.Res = Res;
        this.gender = gender;
        this.MinVolume= MinVol;
        this.RR= RR;
        this.weight=w;
        this.height=h;
        this.IE=IE;
        this.PEEP=PEEP;
        this.VMAX=VMAX;




        try {
            simulator.initializeSimulator();
            // Simulation step
            simulator.setStep(step);
            // Start the simulation and set the initial time
            simulator.startSimulation();
            Thread Vventilator= new Thread(ventilator);
            Vventilator.start();

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
                simulator.simulationStep(ventilator.getvalue());
                Log.d("t", "Update flow chart");
                updateFlowChart();
                Log.d("t", "Update pressure chart");
                updatePressureChart();

                try {
                    Otis.calculatewithOtisFormula(gender, Res, C, MinVolume, RR, weight, height);
                   float distance = Otis.OtisChartDistance(MinVolume, RR, weight);
                   calculateOtis(distance);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Log.d("t", "Update otis chart");


            } else if (status.equals("Stop")) {
                break;
            } else if (status.equals("Freeze")) {
                // Do nothing
            }
        }
    }

public void calculateOtis(float distance) {

        try {

            if (distance <= 3) { // i valori????
                ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                imageView.setImageResource(R.drawable.semverdesupernova);

            }
            else if (distance>3 && distance < 5) {
                ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                imageView.setImageResource(R.drawable.semgiallosupernova);
            }
            else if (distance >= 5 ) {
                ImageView imageView = (ImageView) parentActivity.findViewById(R.id.imageViewSemaforo);
                imageView.setImageResource(R.drawable.semrossosupernova);
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
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


        } catch (IndexOutOfBoundsException ex) {
        }
    }

    public void updatePressureChart() {
        try {
            List<Double> pressureValues = simulator.getPressure();
            List<Entry> entries2 = new ArrayList<Entry>();
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
            dataSet2.setLabel("AIRFLOW OVER TIME");
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
        } catch (IndexOutOfBoundsException ex) {
        }
    }


}