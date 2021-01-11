package com.example.healthsheet.Models;

public class Visite {
    private String ordonnance ;
    private String analyse ;


    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public Visite(String ordonnance, String analyse) {
        this.ordonnance = ordonnance;
        this.analyse = analyse;
    }
}
