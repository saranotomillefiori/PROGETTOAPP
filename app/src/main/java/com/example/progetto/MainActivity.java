package com.example.progetto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.FloatingWindow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
//cambia nome al bottone qui:
Button startbutton;
Button buttoninfo;
FloatingActionButton information;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //BOTTONE trova il bottone//
        startbutton = findViewById(R.id.startbutton);

        // BOTTONE imposta azione quando il bottone viene cliccato tutto quello da qui in poi
        startbutton.setOnClickListener(new View.OnClickListener() {
     @Override
    public void onClick(View v) {
    Intent intent = new Intent(MainActivity.this, MainActivity3.class);
    startActivity(intent);
    }
        });
    // fino a qui ancora bottone


// BOTTONE INFO

    //BOTTONE trova il bottone//
   information = findViewById(R.id.information);
   information.bringToFront();
    // BOTTONE imposta azione quando il bottone viene cliccato tutto quello da qui in poi
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

information= (FloatingActionButton) findViewById(R.id.information);
               Intent i = new Intent(getApplicationContext(), PopActivity.class);
                startActivity(i);
            }
        });

}

}










