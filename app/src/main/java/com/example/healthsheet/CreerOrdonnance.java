package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.Ordonnance;
import com.example.healthsheet.Services.MedicalSheetServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreerOrdonnance extends AppCompatActivity {
Button c ;
EditText m ;
MedicalSheetServices us ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_ordonnance);
        us = ApiUtils.getMedSheetServices();
        c=findViewById(R.id.ordc);
        m=findViewById(R.id.med);
        Ordonnance o = new Ordonnance(m.getText().toString());
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creerord(o);

            }
        });

    }


    public void creerord(Ordonnance o)
    {

        Gson g = new Gson();
        String j = g.toJson(o);
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call= us.createOrdennance(jo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    //User r = response.body();
                    System.out.println("res : "+response.body().toString());
                   /* MedicalSheet.lv.add(o);
                    Intent intent = new Intent(CreerOrdonnance.this ,MedicalSheet.class);
                    startActivity(intent);*/

                }
            }



            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                System.out.println("res : ");


            }
        });

    }

}