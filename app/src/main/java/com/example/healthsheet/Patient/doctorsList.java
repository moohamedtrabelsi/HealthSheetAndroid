package com.example.healthsheet.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.AdpterRecyler;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;
import com.example.healthsheet.Services.UserServices;
import com.example.healthsheet.loginfrag;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class doctorsList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdpterRecyler adpterRecyler;
    private ArrayList<User> users;
    private Button ab ;
    private Gson gson;
    private GsonBuilder gsonBuilder;
    UserServices us;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list2);
        users = new ArrayList<>();
        us= ApiUtils.getUserServices();


        ab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(doctorsList.this, AboutDoc.class);
                startActivity(intent);
            }
        });

        System.out.println("yee raabi"+User.usercur.getListdp().size());

       // System.out.println("doctor"+users.get(0).getEmail());
        recyclerView = findViewById(R.id.recyclerDoctors);
        for(int i = 0 ;i<User.usercur.getListdp().size();i++)
        {


            users.add(thatUser(User.usercur.getListdp().get(i)));



           System.out.println("hhhhhhhhhh"+users.get(i).getEmail());

        }

        adpterRecyler = new AdpterRecyler(this, users);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adpterRecyler);


    }



    public User thatUser(String s)
    {
        final User[] u = {new User()};
        u[0].setUsername(s);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        Gson g = new Gson();
        String jj = g.toJson(u[0]);
        //String js = '{'+"username"+':'+"x"+','+

        //"password"+':'+"x"+'}' ;
        JsonParser jp = new JsonParser();
        JsonObject joo = (JsonObject) jp.parse(jj);
        Call<JsonObject> call = us.getUser(joo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                u[0] = gson.fromJson(response.body().toString(),User.class);
                System.out.println("ggggggggggggggggg"+u[0].getEmail());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });


        return u[0];
    }

}