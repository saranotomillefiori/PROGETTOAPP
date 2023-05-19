package com.example.progetto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
//cambia nome al bottone qui:
Button startbutton;





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
        }
    }







