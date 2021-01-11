package com.example.healthsheet.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.healthsheet.R;

public class AnalyseAffiche extends AppCompatActivity {

    ImageView a ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyse_affiche);
        a=findViewById(R.id.affana);
    }
}