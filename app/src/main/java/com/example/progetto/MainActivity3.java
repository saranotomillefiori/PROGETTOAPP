package com.example.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity3 extends AppCompatActivity {

    Button button2;
    Button button6;
    //EditText FirstNameText;
    //TextView FirstNameView;

    public LineChart chart3;
    public LineChart chart2;

    PlotUpdater updater;
    Thread updaterThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // FirstNameText = (EditText) findViewById(R.id.FirstNameText);
        // FirstNameView = (TextView) findViewById(R.id.FirstNameView);


        LineChart chart = (LineChart) findViewById(R.id.chart);

        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0,1));
        entries.add(new Entry(1,5));
        entries.add(new Entry(2,3));
        entries.add(new Entry(3,2));
        entries.add(new Entry(4,6));

        LineDataSet dataSet = new LineDataSet(entries, "Volume / Time"); // add entries to dataset
        dataSet.setColor(Color.RED);
        dataSet.setValueTextColor(Color.BLACK);

        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        // seconda
        chart2 = (LineChart) findViewById(R.id.chart2);
        chart2.getLegend().setEnabled(false);

        // terza
        chart3 = (LineChart) findViewById(R.id.chart3);
        chart3.getLegend().setEnabled(false);

        // bottone start
        button6 = findViewById(R.id.button6);
        button6.bringToFront();
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(button6.getText().equals("START")) {
                    // double R, double C, String gender, int age, double h, double w, double step, MainActivity3 parentActivity
                    double r = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.editTextRes)).getText()));
                    double c = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.editTextComp)).getText()));
                    String gender = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
                    int age = Integer.parseInt(String.valueOf(((EditText)findViewById(R.id.editTextAge)).getText()));
                    double h = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.editTextHeight)).getText())) / 100;
                    double w = Double.parseDouble(String.valueOf(((EditText)findViewById(R.id.editTextWeight)).getText()));;

                    try {
                        updater = new PlotUpdater(r, c, gender, age, h, w, 0.1, (MainActivity3) view.getContext());
                        button6.setText("FREEZE");
                        updaterThread = new Thread(updater);
                        updaterThread.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    updater.setStatus("Freeze");
                    button6.setText("START");
                }
            }
        });

        // bottone stop


        //BOTTONE trova il bottone//
        button2 = findViewById(R.id.button2);
        button2.bringToFront();
        // BOTTONE imposta azione quando il bottone viene cliccato tutto quello da qui in poi
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button2= (Button) findViewById(R.id.button2);
                Intent i = new Intent(getApplicationContext(), PopActivity2.class);
                startActivity(i);

            }
        });

    }

}
