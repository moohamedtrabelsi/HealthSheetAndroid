package com.example.healthsheet.Models;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.Doctor.PatientListe;

import java.util.ArrayList;

public class AdpterRecyclerPatient extends RecyclerView.Adapter<AdpterRecyler.ViewHolder> {
    public AdpterRecyclerPatient(PatientListe patientListe, ArrayList<User> users) {
    }

    @NonNull
    @Override
    public AdpterRecyler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterRecyler.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
