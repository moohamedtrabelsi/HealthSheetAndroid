package com.example.healthsheet.Doctor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.Models.AdpterRecyclerPatient;
import com.example.healthsheet.Models.AdpterRecyler;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;

import java.util.ArrayList;

public class PatientListe extends AppCompatActivity {

    private RecyclerView recyclerView;

    private ArrayList<User> users;

    private AdpterRecyclerPatient adpterRecyler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientlist);

        recyclerView = findViewById(R.id.recyclerDoctors);
        users = new ArrayList<>();

        for(int i= 0 ; i < 10 ; i++){
            users.add(new User("khenine","oumayma"));
        }
        adpterRecyler = new AdpterRecyclerPatient(this,users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpterRecyler);


    }
}
