package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthsheet.Models.AdpterRecyler;
import com.example.healthsheet.Models.User;

import java.util.ArrayList;

public class doctorsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdpterRecyler adpterRecyler;
    private ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list2);

        recyclerView = findViewById(R.id.recyclerDoctors);
        users = new ArrayList<>();

        for(int i= 0 ; i < 10 ; i++){
            users.add(new User("hama","hama"));
        }
        adpterRecyler = new AdpterRecyler(this, users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpterRecyler);


    }
}