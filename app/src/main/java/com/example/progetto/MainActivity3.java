package com.example.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;


public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

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
    }
}