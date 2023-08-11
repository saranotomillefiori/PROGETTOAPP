package com.example.progetto;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PopActivityVentilator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_ventilator);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
    public void getResult(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("PEEP",  Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextPEEP)).getText())) );
        returnIntent.putExtra("fio2", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextfio2)).getText())) );
        returnIntent.putExtra("pmax", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextpmax)).getText())) );
        returnIntent.putExtra("VM", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextVM)).getText())) );
        returnIntent.putExtra("VMAX", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextVMAX)).getText())) );
        returnIntent.putExtra("MinVolume", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextMinVolume)).getText())) );
        returnIntent.putExtra("IE", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextIE)).getText())) );
        returnIntent.putExtra("RR", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextRR)).getText())) );
        setResult(MainActivity3.RESULT_OK, returnIntent);
        finish();
    }
}