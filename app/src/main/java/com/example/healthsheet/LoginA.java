package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Patient.Menu;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginA extends AppCompatActivity {
    public static final String USERNAME_KEY = "FNAME";
    public static final String PWD_KEY = "PWD";
    public static final String PHONE_KEY = "PHONE";
    public static final String CHECKED_KEY = "CHECKED";

    public static final String sharedPrefFile = "com.example.healthsheet";
    EditText username ;
    EditText passwor ;
    Button logbut ;
    Button forg ,signup ;
    private CheckBox cbRememberMe;
    public static User currentUser;
    private SharedPreferences mPreferences;
    UserServices us;

    private Gson gson;
    private GsonBuilder gsonBuilder;
    Context c ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.logusername);
        passwor = findViewById(R.id.logpassword);
        logbut = findViewById(R.id.loglogin);
        forg = findViewById(R.id.forgotpassword);
        signup = findViewById(R.id.Signup);
        cbRememberMe = findViewById(R.id.cbRememberMe);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        username.setText( mPreferences.getString(USERNAME_KEY,"") );
        passwor.setText( mPreferences.getString(PWD_KEY,"") );
        cbRememberMe.setChecked( mPreferences.getBoolean(CHECKED_KEY, false) );
        us= ApiUtils.getUserServices();
        c = this;
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginA.this, Signupp.class);
                startActivity(intent);
            }
        });

        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernam = username.getText().toString();
                String userpas = passwor.getText().toString();

                if(validlogin(usernam,userpas)){
                    dologin(usernam,userpas);
                }

            }
        });



        forg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Forget();
            }
        });
    }



    public void doSignIn() {
        if(validlogin(username.getText().toString() , passwor.getText().toString())) {

            //TODO 3 Save SharedPref
            if (cbRememberMe.isChecked()){
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(USERNAME_KEY, username.getText().toString());
                preferencesEditor.putString(PWD_KEY, passwor.getText().toString());
                preferencesEditor.putBoolean(CHECKED_KEY, true);

                preferencesEditor.apply();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            }else {
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(USERNAME_KEY, "");
                preferencesEditor.putString(PWD_KEY, "");
                preferencesEditor.putBoolean(CHECKED_KEY, false);

                //preferencesEditor.clear();
                preferencesEditor.apply();
            }

        }
    }



    private boolean validlogin(String un , String up)
    {
        if(un==null || un.trim().length()==0 ){
            Toast.makeText(this,"usernameisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(up==null || up.trim().length()==0 ){
            Toast.makeText(this,"passwordisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void Forget (){
        Intent intent = new Intent(LoginA.this, EmailForgot.class);
        startActivity(intent);

    }
    private void dologin (String un , String up){


//        GMailSender sender = new GMailSender("username@gmail.com", "password");
        User u = new User();
        u.setUsername(un);
        u.setPassword(up);

        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();


        Gson g = new Gson();
        String j = g.toJson(u);
        //String js = '{'+"username"+':'+"x"+','+

        //"password"+':'+"x"+'}' ;
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call = us.login(jo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    //User r = response.body();
                    //System.out.println("res : "+response.body().toString());
                    if(response.body().toString().contains("Not"))
                    {
                        //  Toast.makeText(;,"invalide username or password ",Toast.LENGTH_SHORT).show();
                        toastshow("invalide username or password");
                        //Toast.makeText()


                    }
                    else {
                        toastshow("invalide ");

                        User.usercur = gson.fromJson(response.body().toString(),User.class);
                        System.out.println("hjbsl"+User.usercur.getUsername());
                        doSignIn();
                        Intent intent = new Intent(LoginA.this, Menu.class);
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });

    }


    public void toastshow(String s)
    {
        Toast t = Toast.makeText(c,s,Toast.LENGTH_SHORT);
        t.show();
    }

}