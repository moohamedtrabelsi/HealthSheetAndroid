package com.example.healthsheet.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.healthsheet.About;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;
import com.example.healthsheet.SendImage;
import com.example.healthsheet.UploadImage;

public class AboutDoc extends AppCompatActivity {

    public static User aboutuser ;
    Button send ;
    TextView a ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_doc);
        a = findViewById(R.id.aboutdoc);
        System.out.println(aboutuser.getUsername());
        a.setText(aboutuser.getFirstName());
        send = findViewById(R.id.analysedoc);
        send.setOnClickListener(v -> {
            System.out.println("OOOhhhhh");
            Intent intent = new Intent(AboutDoc.this, SendImage.class);
            startActivity(intent);
        });
    }
}