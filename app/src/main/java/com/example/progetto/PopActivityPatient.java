package com.example.progetto;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class PopActivityPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_patient);

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
        returnIntent.putExtra("Finished", true); // Puoi passare dati aggiuntivi alla main activity tramite l'intent
        returnIntent.putExtra("FirstName",  ((EditText) findViewById(R.id.editTextFirstName)).getText());
        returnIntent.putExtra("Gender",  ((Spinner) findViewById(R.id.spinner)).getSelectedItem().toString());
        returnIntent.putExtra("SecondName",  ((EditText) findViewById(R.id.editTextSecondName)).getText());
        returnIntent.putExtra("Age",  Integer.parseInt(String.valueOf(((EditText) findViewById(R.id.editTextAge)).getText())));
        returnIntent.putExtra("Weight", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextWeight)).getText())) );
        returnIntent.putExtra("Height", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextHeight)).getText())) );
        returnIntent.putExtra("Comp",  Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextComp)).getText())) );
        returnIntent.putExtra("Res", Float.parseFloat(String.valueOf(((EditText) findViewById(R.id.editTextRes)).getText())) );
        setResult(MainActivity3.RESULT_OK, returnIntent);
        finish();
    }
}