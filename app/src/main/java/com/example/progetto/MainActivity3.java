package com.example.progetto;
//agg iio
package com.techizvibe.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


public class MainActivity3 extends AppCompatActivity {

    Button button2;
    Button button6;
    //EditText FirstNameText;
    //TextView FirstNameView;

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
        LineChart chart2 = (LineChart) findViewById(R.id.chart2);

        List<Entry> entries2 = new ArrayList<Entry>();
        entries2.add(new Entry(0,1));
        entries2.add(new Entry(1,5));
        entries2.add(new Entry(2,3));
        entries2.add(new Entry(3,2));
        entries2.add(new Entry(4,6));

        LineDataSet dataSet2 = new LineDataSet(entries2, "Pressure / Time"); // add entries to dataset
        dataSet2.setColor(Color.GREEN);
        dataSet2.setValueTextColor(Color.BLACK);

        LineData lineData2 = new LineData(dataSet2);
        chart2.setData(lineData2);
        chart2.invalidate();

        // terza

        LineChart chart3 = (LineChart) findViewById(R.id.chart3);

        List<Entry> entries3 = new ArrayList<Entry>();
        entries3.add(new Entry(0,1));
        entries3.add(new Entry(1,5));
        entries3.add(new Entry(2,3));
        entries3.add(new Entry(3,2));
        entries3.add(new Entry(4,6));

        LineDataSet dataSet3 = new LineDataSet(entries3, "AirFlow / Time"); // add entries to dataset
        dataSet3.setColor(Color.BLUE);
        dataSet3.setValueTextColor(Color.BLACK);

        LineData lineData3 = new LineData(dataSet3);
        chart3.setData(lineData3);
        chart3.invalidate();

        /*
        // aggiungo qui dei valori per il grafico 1 la view
        GraphView graph = (GraphView) findViewById(R.id.Graphview1);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);*/
// bottone start
        button6 = findViewById(R.id.button6);
        button6.bringToFront();
        button6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                button6.setText("FREEZE");
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
