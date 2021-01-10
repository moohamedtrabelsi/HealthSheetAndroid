package com.example.healthsheet.Models;

public class Visite {
    private Analyse a ;
    private Ordonnance o;

    public Analyse getA() {
        return a;
    }

    public void setA(Analyse a) {
        this.a = a;
    }

    public Ordonnance getO() {
        return o;
    }

    public void setO(Ordonnance o) {
        this.o = o;
    }

    public Visite(Analyse a, Ordonnance o) {
        this.a = a;
        this.o = o;
    }
}
