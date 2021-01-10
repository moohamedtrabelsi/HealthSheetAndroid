package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassword extends AppCompatActivity {
    Button btnUpdate ;
    EditText nv ;
    UserServices us;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        btnUpdate = findViewById(R.id.btnUpdate);
        nv = findViewById(R.id.NvPassword);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            u.setPassword(nv.getText().toString());
            doUpdate(u);
                // Intent intent = new Intent(UpdatePassword.this, loginfrag.class);
                //startActivity(intent);
            }
        });
    }
        private void doUpdate(User u) {

            Gson g = new Gson();
            String j = g.toJson(u);

            JsonParser jp = new JsonParser();
            JsonObject jo = (JsonObject) jp.parse(j);
            Call<JsonObject> call = us.updatePass(jo);

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                    getIntent();
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {

                }
            });
    }}