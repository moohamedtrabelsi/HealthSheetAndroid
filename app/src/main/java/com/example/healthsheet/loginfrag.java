package com.example.healthsheet;

import android.content.Context;
import android.content.Intent;
import android.icu.lang.UScript;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
//192.168.0.123
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.healthsheet.Api.ApiClient;
import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Api.Responseobj;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link loginfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class loginfrag extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    EditText username ;
    EditText passwor ;
    Button logbut ;
    UserServices us;

    private Gson gson;
    private GsonBuilder gsonBuilder;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment loginfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static loginfrag newInstance(String param1, String param2) {
        loginfrag fragment = new loginfrag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void toastshow(String s)
    {
        Toast t = Toast.makeText(this.getContext(),s,Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loginfrag, container, false);
        username = v.findViewById(R.id.logusername);
        passwor = v.findViewById(R.id.logpassword);
        logbut = v.findViewById(R.id.loglogin);
        us= ApiUtils.getUserServices();

        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernam = username.getText().toString();
                String userpas = passwor.getText().toString();

                if(validlogin(usernam,userpas)){
                  dologin(usernam,userpas);


                  /* User u = new User();
                    u.setUsername(usernam);
                    u.setPassword(userpas);

                    Gson g = new Gson();
                    String j = g.toJson(u);
                    Toast.makeText(v.getContext(),j,Toast.LENGTH_SHORT).show();*/

                }

            }
        });



        return v ;

    }
    private boolean validlogin(String un , String up)
    {
    if(un==null || un.trim().length()==0 ){
        Toast.makeText(this.getContext(),"usernameisrequired",Toast.LENGTH_SHORT).show();
    return false;
    }
        if(up==null || up.trim().length()==0 ){
            Toast.makeText(this.getContext(),"passwordisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void dologin (String un , String up){

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

                   }
                   else {
                       User.usercur = gson.fromJson(response.body().toString(),User.class);
                       System.out.println("hjbsl"+User.usercur.getUsername());

                       Intent intent = new Intent(loginfrag.this.getContext(),Menu.class);
                       startActivity(intent);
                   }
                }
            }



            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {



            }
        });

    }
}