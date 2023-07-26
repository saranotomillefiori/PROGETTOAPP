package com.example.progetto;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import lungsimulator.exceptions.InspireException;
import lungsimulator.interfaceclass.LungSimulatorInterface;

public class Ventilator implements Runnable{
    LungSimulatorInterface simulator;
    Ventilator ventilator;
   // String status;

    MainActivity3 parentActivity;
    public Ventilator(double RR, double IE, double VMAX, double PEEP,  MainActivity3 parentActivity) throws
            IOException {
    }
    @Override
    public void run() {
        while (true) { // il true poi quando funziona lo cambiamo con una variabile boolean che richiama i bottoni, così da fare andare il run solo quando si preme il bottone e stopparlo se necessario
            ventilator = double.VMAX; //capiamo come si richiama il dato dall'altra pagina
            try {
                Thread.sleep(1250); // inspiro
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ventilator = double.PEEP;
            try {
                Thread.sleep(3750); // espiro
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        }
    }

//public class Ventilator extends AppCompatActivity {
//    private Button button6;
  //  private Button button2;
    //private TextView statusTextView;
 //   private Handler handler;
  //  private boolean isRunning;

  //  private EditText PEEP;
  //  private EditText RR;
  //  private EditText IE;
   // private EditText VMAX;
//   public static double ventoutput = 0;




  //  @SuppressLint("MissingInflatedId") //da sistemare!!!
//    @Override
  //  protected void onCreate(Bundle savedInstanceState) {
  //      super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_ventilator);
 //       button6 = findViewById(R.id.button6);
  //      button2 = findViewById(R.id.button2);
   //     handler = new Handler();



  //              button6.setOnClickListener(new View.OnClickListener() {
  //                  @Override
 //                  public void onClick(View v) {
  //                      startVentilator();
  //                  }
  //              });

    ////              button2.setOnClickListener(new View.OnClickListener() {
    //               @Override
                    //                  public void onClick(View v) {
//                     stopVentilator();
//                  }
                            //              });
    //          }


//       private void startVentilator() {
//             isRunning = true;
//
//              // Simulate ventilator operation
    //              handler.postDelayed(new Runnable() {
    //                   // Metto respiratory rate, vmax, peep, I/E -> messi sopra, servono anche qui?


    //                   @Override
 //v            //      public void run() {

         //                       EditText ventoutput = VMAX;
                        // per 1/3 dell'handler vai a vmax, per 2/3 successivi vai a PEEP

  //                      if (handler = 1250) { // come si scrive?
    //                          ventoutput = PEEP;
    //                      }
//

//                       if (isRunning) {
//                         handler.postDelayed(this, 3750); // Repeat after 1 second if 1000  (facciamolo diventare 3,75 secondi)
//                      }
//                  }
//  v//                 }, 3750); // Start after 1 second if 1000
//        }


//           private void stopVentilator() {
//                isRunning = false;
//            }

//       }







