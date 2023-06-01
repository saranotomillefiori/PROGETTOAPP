package com.example.progetto;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Ventilator extends AppCompatActivity {
    private Button button6;
    private Button button2;
    //private TextView statusTextView;
    private Handler handler;
    private boolean isRunning;


    @SuppressLint("MissingInflatedId") //da sistemare!!!
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventilator);
        button6 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button2);
        handler = new Handler();



                button6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startVentilator();
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        stopVentilator();
                    }
                });
            }


            private void startVentilator() {
                isRunning = true;

                // Simulate ventilator operation
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Metto respiratory rate, tidal volume...



                        if (isRunning) {
                            handler.postDelayed(this, 1000); // Repeat after 1 second
                        }
                    }
                    }, 1000); // Start after 1 second
            }


            private void stopVentilator() {
                isRunning = false;
            }

        }








