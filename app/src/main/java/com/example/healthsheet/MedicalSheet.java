package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.healthsheet.Adapter.VisiteAdapter;
import com.example.healthsheet.Models.Ordonnance;
import com.example.healthsheet.Models.Visite;

import java.util.ArrayList;
import java.util.List;

public class MedicalSheet extends AppCompatActivity {
LinearLayout l ;
RecyclerView r;

static List<Visite>lv = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_sheet);

        l= findViewById(R.id.lo);
        r=l.findViewById(R.id.rcm);

        r.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        //  v.setBackgroundResource(R.drawable.algerie);

        VisiteAdapter c = new VisiteAdapter(lv);
        r.setAdapter(c);
    }
}