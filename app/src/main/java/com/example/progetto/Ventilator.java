package com.example.progetto;

/**
 * Class handling the ventilator. It is basically a loop that changes
 * the returned value based on the inspiratory/expiratory status
 */

public class Ventilator implements Runnable {
    int value;
    private final double vMax;
    private final double peep;
    private final double rr;
    private final double ie;
    private String status;

    public Ventilator(double rr, double ie, double vMax, double peep) {
        this.rr = rr;
        this.ie = ie;
        this.vMax = vMax;
        this.peep = peep;
    }

    public int getValue() {
        return value;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void run() {
        this.status = "START";
        while (this.status.equals("START")) {
            value = (int) this.vMax;
            try {
                // Thread.sleep receives the time expressed in milliseconds,
                // thus it must be multiplied per 1000

                // Inspiration
                Thread.sleep((int) ((60 / rr) / (1 + ie)) * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            value = (int) this.peep;
            try {
                // Expiration
                Thread.sleep((int) ((60 / rr) * (1 - (1 / (1 + ie)))) * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}






