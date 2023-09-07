package com.example.progetto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.io.IOException;


public class MainActivity3 extends AppCompatActivity {

    ImageButton imageButtonStop;
    ImageButton imageButtonStart;

    Button patient;
    Button ventilator;

    public LineChart chart;
    public LineChart chart3;
    public LineChart chart2;

    PlotUpdater updater;
    Thread updaterThread;

    public String FirstName;
    public String SecondName;
    public int Age;
    public String Gender;

    public float Weight;
    public float Height;
    public float Comp;
    public float Res;

    public float PEEP;
    public float VMAX;
    public float MinVolume;
    public float IE;
    public float RR;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        chart2 = (LineChart) findViewById(R.id.chart2);
        chart2.getLegend().setEnabled(false);

        chart3 = (LineChart) findViewById(R.id.chart3);
        chart3.getLegend().setEnabled(false);

        // start button
        imageButtonStart = findViewById(R.id.imageButtonStart);
        imageButtonStart.bringToFront();
        imageButtonStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                double r = ((MainActivity3) view.getContext()).Res;
                String gender = ((MainActivity3) view.getContext()).Gender;
                double c = ((MainActivity3) view.getContext()).Comp;
                double h = ((MainActivity3) view.getContext()).Height;
                double w = ((MainActivity3) view.getContext()).Weight;
                int age = ((MainActivity3) view.getContext()).Age;
                double RR = ((MainActivity3) view.getContext()).RR;
                double IE = ((MainActivity3) view.getContext()).IE;
                double PEEP = ((MainActivity3) view.getContext()).PEEP;
                double VMAX = ((MainActivity3) view.getContext()).VMAX;
                double MinVolume = ((MainActivity3) view.getContext()).MinVolume;

                try {
                    updater = new PlotUpdater(r, c, gender, age, h, w, 0.1, RR, IE, VMAX, PEEP, MinVolume, (MainActivity3) view.getContext());

                    updaterThread = new Thread(updater);
                    updaterThread.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // pause button
        ImageButton imageButtonPause = findViewById(R.id.imageButtonPause);
        imageButtonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updater.setStatus("Freeze");
            }
        });

        imageButtonStop = findViewById(R.id.imageButtonStop);
        imageButtonStop.bringToFront();
        patient = findViewById(R.id.patient);
        patient.bringToFront();
        ventilator = findViewById(R.id.ventilator);
        ventilator.bringToFront();
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

        SharedPreferences sharedPreferences = getSharedPreferences("ResetPrefs", MODE_PRIVATE);
        String resetFlag = sharedPreferences.getString("resetFlag", "");

        if ("reset".equals(resetFlag)) {
            // Reset the string values
            FirstName = "FirstName";
            SecondName = "SecondName";
            Gender = "Gender";
            Age = 18;
            Weight = 80;
            Height = 1.5f;
            Comp = 1;
            Res = 10;
            PEEP = 5;
            VMAX = 35;
            MinVolume = 9;
            IE = 0.5f;
            RR = 19;

            // Reset the flag
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("resetFlag", "");
            editor.apply();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent myIntent) {
        super.onActivityResult(requestCode, resultCode, myIntent);

        if (requestCode == 2 && resultCode == MainActivity3.RESULT_OK) {
            FirstName = myIntent.getStringExtra("FirstName");
            Gender = myIntent.getStringExtra("Gender");
            SecondName = myIntent.getStringExtra("SecondName");
            Age = myIntent.getIntExtra("Age", 18);
            Weight = myIntent.getFloatExtra("Weight", 80);
            Height = myIntent.getFloatExtra("Height", 1.5f);
            Comp = myIntent.getFloatExtra("Comp", 1);
            Res = myIntent.getFloatExtra("Res", 10);


            if (updater != null) {
                try {
                    if (updaterThread != null)
                        updater.killUpdater();
                    updater = new PlotUpdater(Res, Comp, Gender, Age, Height, Weight, 0.1, RR, IE, VMAX, PEEP, MinVolume, this);
                    updaterThread = new Thread(updater);
                    updaterThread.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (requestCode == 3 && resultCode == MainActivity3.RESULT_OK) {
            PEEP = myIntent.getFloatExtra("PEEP", 5);
            VMAX = myIntent.getFloatExtra("VMAX", 35);
            MinVolume = myIntent.getFloatExtra("MinVolume", 9);
            IE = myIntent.getFloatExtra("IE", 0.5f);
            RR = myIntent.getFloatExtra("RR", 19);

            if (updater != null) {
                try {
                    if (updaterThread != null)
                        updater.killUpdater();
                    updater = new PlotUpdater(Res, Comp, Gender, Age, Height, Weight, 0.1, RR, IE, VMAX, PEEP, MinVolume, this);
                    updaterThread = new Thread(updater);
                    updaterThread.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
