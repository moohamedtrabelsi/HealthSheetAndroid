package com.example.healthsheet.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;

public class AboutDoc extends AppCompatActivity {

    public static User aboutuser ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_doc);
    }
}