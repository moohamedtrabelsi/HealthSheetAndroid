package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signupp extends AppCompatActivity {



    EditText username;
    EditText frname;
    EditText lsname;
    EditText passw1;
    EditText passw2;
    EditText email;
    UserServices us ;
    Button signup ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupp);

        username = findViewById(R.id.signupusername1);

        frname = findViewById(R.id.signuppfirstname1);
        lsname = findViewById(R.id.signupplastname1);
        passw1 = findViewById(R.id.signuppassword11);
        passw2 = findViewById(R.id.signuppassword21);
        email = findViewById(R.id.signuppmail1);
        signup = findViewById(R.id.signupbtn1h);
        us= ApiUtils.getUserServices();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un=username.getText().toString() ;
                String up=passw1.getText().toString();
                String upp=passw2.getText().toString();
                String uf=frname.getText().toString();
                String ul=lsname.getText().toString();
                String ue=email.getText().toString();

                if(validsignup( un, up,upp,uf,ul,ue)==true);
                {
                    doSignup(un, up, uf, ul, ue);
                }

            }
        });


    }
    private boolean validsignup(String un , String up,String upp,String uf,String ul,String ue)
    {
        if(un==null || un.trim().length()==0 ){
            Toast.makeText(this,"username is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if((up==null || up.trim().length()==0 )&(upp.equals(up))&&(upp.trim().length()>0)&&(upp.trim().length()<8)){
            Toast.makeText(this,"passwordisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if((upp==null || upp.trim().length()==0 )&&(upp.equals(up))&&(upp.trim().length()>0)&&(upp.trim().length()<8)){
            Toast.makeText(this,"verify password",Toast.LENGTH_SHORT).show();
            return false;
        }
        if((uf==null || uf.trim().length()==0 )){
            Toast.makeText(this,"first name isrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ul==null || ul.trim().length()==0 ){
            Toast.makeText(this,"last name isrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if((ue==null || ue.trim().length()==0 )&&(ue.contains("@"))){
            Toast.makeText(this,"email isrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void doSignup (String un , String up,String uf,String ul,String ue){

        User u = new User();
        u.setUsername(un);
        u.setPassword(up);
        u.setEmail(ue);
        u.setFirstName(uf);
        u.setLastName(ul);


        Gson g = new Gson();
        String j = g.toJson(u);
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call= us.signup(jo);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    //User r = response.body();
                    System.out.println("res : "+response.body().toString());

                }
            }



            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {


            }
        });

    }

}