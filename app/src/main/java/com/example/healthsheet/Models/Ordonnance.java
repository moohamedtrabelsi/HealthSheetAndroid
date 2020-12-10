package com.example.healthsheet.Models;

public class Ordonnance {
    public Ordonnance(String medicamets) {
        this.medicaments = medicamets;
    }

    String medicaments ;

    public String getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(String medicaments) {
        this.medicaments = medicaments;
    }
}
