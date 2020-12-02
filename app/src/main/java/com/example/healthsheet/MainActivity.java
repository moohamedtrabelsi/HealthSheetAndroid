package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button login ;
Button signup ;
ConstraintLayout cl ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signup = findViewById(R.id.Signup);
        login = findViewById(R.id.Login);
        cl = findViewById(R.id.fragcont);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragcont,new loginfrag())
                .commit();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcont,new signupfrag())
                        .commit();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragcont,new loginfrag())
                        .commit();
            }
        });

    }
}