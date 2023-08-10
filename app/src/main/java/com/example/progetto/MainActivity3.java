package com.example.progetto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.io.IOException;


public class MainActivity3 extends AppCompatActivity {

    ImageButton imageButtonStop;
    ImageButton imageButtonStart;

    Button patient;
    Button ventilator;
    //EditText FirstNameText;
    //TextView FirstNameView;
    public LineChart chart;
    public LineChart chart3;
    public LineChart chart2;

    PlotUpdater updater;
    Thread updaterThread;
// pop up paziente
    public EditText FirstName;
    public EditText SecondName;
    public EditText Age;
    public Spinner spinner;
    public EditText Weight;
    public EditText Height;
    public EditText Comp;
    public EditText Res;
    // pop up ventilatore
    public EditText PEEP;
    public EditText fio2;
    public EditText pmax;
    public EditText VM;
    public EditText VMAX;
    public EditText MinVolume;
    public EditText IE;
    public EditText RR;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // FirstNameText = (EditText) findViewById(R.id.FirstNameText);
        // FirstNameView = (TextView) findViewById(R.id.FirstNameView);

        //prima
        // chart = (LineChart) findViewById(R.id.chart);
        // chart.getLegend().setEnabled(false);


        // seconda
        chart2 = (LineChart) findViewById(R.id.chart2);
        chart2.getLegend().setEnabled(false);

        // terza
        chart3 = (LineChart) findViewById(R.id.chart3);
        chart3.getLegend().setEnabled(false);

        // bottone start
        imageButtonStart = findViewById(R.id.imageButtonStart);
        imageButtonStart.bringToFront();
        imageButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageButtonStart.getBackground().equals(R.drawable.playblu)) {
                    // double R, double C, String gender, int age, double h, double w, double step, MainActivity3 parentActivity
                    double r = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextRes)).getText()));
                    double c = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextComp)).getText()));
                    String gender = ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString();
                    int age = Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.editTextAge)).getText()));
                    double h = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextHeight)).getText())) / 100;
                    double w = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextWeight)).getText()));
                    ;
                    double RR = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextRR)).getText()));
                    double IE = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextIE)).getText()));
                    double PEEP = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextPEEP)).getText()));
                    double VMAX = Double.parseDouble(String.valueOf(((EditText) findViewById(R.id.editTextVMAX)).getText()));

                    try {
                        updater = new PlotUpdater(r, c, gender, age, h, w, 0.1, RR, IE, VMAX, PEEP, (MainActivity3) view.getContext(), (PopActivityPatient) view.getContext(), (PopActivityVentilator) view.getContext());
                        imageButtonStart.setBackgroundResource(R.drawable.pauseblu);


                        updaterThread = new Thread(updater);
                        updaterThread.start();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    updater.setStatus("Freeze");
                    imageButtonStart.setBackgroundResource(R.drawable.playblu);

                }
            }
        });

        // bottone stop


        //BOTTONE trova il bottone//
        imageButtonStop = findViewById(R.id.imageButtonStop);
        imageButtonStop.bringToFront();
        patient = findViewById(R.id.patient);
        patient.bringToFront();
        ventilator = findViewById(R.id.ventilator);
        ventilator.bringToFront();
        // BOTTONE imposta azione quando il bottone viene cliccato tutto quello da qui in poi
        imageButtonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imageButtonStop = (ImageButton) findViewById(R.id.imageButtonStop);
                Intent i = new Intent(getApplicationContext(), PopActivity2.class);
                startActivity(i);

            }
        });
        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                patient = (Button) findViewById(R.id.patient);
                Intent j = new Intent(MainActivity3.this, PopActivityPatient.class);
                startActivityForResult(j, 2);
// PROVO PIZZA
            }
        });
        ventilator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ventilator = (Button) findViewById(R.id.ventilator);
                Intent k = new Intent(MainActivity3.this, PopActivityVentilator.class);
                startActivityForResult(k, 3);

            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent myIntent) {
        super.onActivityResult(requestCode, resultCode, myIntent);

        if (requestCode == 2 && resultCode == MainActivity3.RESULT_OK) {
            // VALORI DEL PAZIENTE

            if (myIntent.getExtras().getBoolean("Finished")) {
                FirstName = (EditText) findViewById(R.id.editTextFirstName);
                SecondName = (EditText) findViewById(R.id.editTextSecondName);
                Age = (EditText) findViewById(R.id.editTextAge);
                spinner = (Spinner) findViewById(R.id.spinner);
                Weight = (EditText) findViewById(R.id.editTextWeight);
                Height = (EditText) findViewById(R.id.editTextHeight);
                Comp = (EditText) findViewById(R.id.editTextComp);
                Res = (EditText) findViewById(R.id.editTextRes);
            }

        }
        if (requestCode == 3 && resultCode == MainActivity3.RESULT_OK) {
            // VALORI DEL PAZIENTE

            if (myIntent.getExtras().getBoolean("Finished")) {
                PEEP = (EditText) findViewById(R.id.editTextPEEP);
                fio2 = (EditText) findViewById(R.id.editTextfio2);
                pmax = (EditText) findViewById(R.id.editTextpmax);
                VM = (EditText) findViewById(R.id.editTextVM);
                VMAX = (EditText) findViewById(R.id.editTextVMAX);
                MinVolume = (EditText) findViewById(R.id.editTextMinVolume);
                IE = (EditText) findViewById(R.id.editTextIE);
                RR = (EditText) findViewById(R.id.editTextRR);

            }
        }

    }




}

