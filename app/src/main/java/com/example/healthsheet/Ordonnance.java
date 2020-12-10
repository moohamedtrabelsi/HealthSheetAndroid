package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ordonnance extends AppCompatActivity {

    Button c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordonnance);

        c=findViewById(R.id.creer);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ordonnance.this ,CreerOrdonnance.class);
                startActivity(intent);
            }
        });

    }
}