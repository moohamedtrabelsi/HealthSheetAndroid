package com.example.healthsheet.Patient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.widget.Button;
import android.widget.EditText;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Account1 extends AppCompatActivity {

    User u = User.usercur;
    EditText fname ;
    EditText lname ;
    EditText email ;
    Button update ;
    UserServices us ;
    //EditText pass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account1);

        try{
            this.getSupportActionBar().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }

        fname = findViewById(R.id.AccounttextNom);
        lname = findViewById(R.id.AccounttextPrenom);
        email = findViewById(R.id.AccounttextE);
        update = findViewById(R.id.AccountUpdate);
        us = ApiUtils.getUserServices();
        fname.setText(u.getFirstName());

        email.setText(u.getEmail());

        lname.setText(u.getLastName());


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                u.setEmail(email.getText().toString());
                u.setFirstName(fname.getText().toString());
                u.setLastName(lname.getText().toString());
                doUpdate(u);

            }
        });



    }

    private void doUpdate(User u) {
        Gson g = new Gson();
        String j = g.toJson(u);

        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call = us.update(jo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                finish();
                getIntent();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }
}