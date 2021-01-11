package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Email.GMailSender;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.nio.charset.Charset;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailForgot extends AppCompatActivity {

    EditText email ;


    Button send ;
    private Gson gson;
    private GsonBuilder gsonBuilder;
    public static String code ;
    UserServices us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_forgot);

        send = findViewById(R.id.btnEmail);
        email = findViewById(R.id.EmailF);
        us = ApiUtils.getUserServices();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String mail = email.getText().toString();

                //toastshow("oumaaaa"+mail);

                verif(mail);

            }
        });
    }


    public void toastshow(String s)
    {
        Toast t = Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }
    public static String codee;

 private void sendEmail(final String Email)
    { String Sender="mohamed.trabelsi@esprit.tn";
        String Password="success1623";
        String Title="test";
        UpdatePassword.email = email.getText().toString();
        String Message=Random();
        codee=Message;
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    GMailSender sender = new GMailSender(Sender,Password);
                    sender.sendMail(Title, "<b>"+Message+"</b>", Sender, Email);
                    makeAlert();

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }

        }).start();
    }private void makeAlert(){
        this.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(EmailForgot.this, "Mail Sent", Toast.LENGTH_SHORT).show();
            }
        });
    }






    public String Random () {
        Random r = new Random();
        String code = "" + r.nextInt(10000);
        while (code.length() < 4){
            code = "0" + code ;
        }
        return code ;

    }









    private void verif (String em){

        User u = new User();
        u.setEmail(em);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();


        Gson g = new Gson();
        String j = g.toJson(u);

        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call = us.getEmail(jo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
            if(response.isSuccessful()){

                if(response.body().toString().contains("not"))
                {
                    //  Toast.makeText(;,"invalide username or password ",Toast.LENGTH_SHORT).show();
                    toastshow("invalide email ");

                }
                else {
                    User.usercur = gson.fromJson(response.body().toString(),User.class);
                    sendEmail(email.getText().toString());
                    Intent intent = new Intent(EmailForgot.this, ValiderPassword.class);
                    startActivity(intent);
                }
            }
        }

        @Override
        public void onFailure(Call<JsonObject> call, Throwable t) {
            Log.v("Upload", "success");
        }
    });
    }

}
