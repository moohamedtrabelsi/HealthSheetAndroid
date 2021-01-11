package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.healthsheet.Adapter.ImageLoadTask;
import com.example.healthsheet.Models.Analyse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Ordonnance extends AppCompatActivity {
    public static Analyse curanalyse;
    Button c ;
    ImageView aff;
    String u = "http://192.168.1.102:3000/api/image/1610111086511-HealthSheet-file.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordonnance);
        aff = findViewById(R.id.afficheanalyse);
        c=findViewById(R.id.creer);
        new ImageLoadTask(u,aff).execute();
      //  new RetrieveFeedTask().execute(urlToRssFeed);

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ordonnance.this ,CreerOrdonnance.class);
                startActivity(intent);
            }
        });

    }

}


