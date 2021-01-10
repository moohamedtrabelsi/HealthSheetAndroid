package com.example.healthsheet.Models;

public class Analyse {
    private String filename;
    private String doctor;
    private String patient;
    private String id;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Analyse(String filename, String doctor, String patient, String id) {
        this.filename = filename;
        this.doctor = doctor;
        this.patient = patient;
        this.id = id;
    }
}
