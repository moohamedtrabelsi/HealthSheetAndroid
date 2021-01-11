package com.example.healthsheet;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import retrofit2.Call;
import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Services.UserServices;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signupfrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signupfrag extends Fragment {

    EditText username;
    EditText frname;
    EditText lsname;
    EditText passw1;
    EditText passw2;
    EditText email;
    UserServices us ;
    Button signup ;

    public signupfrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signupfrag.
     */
    // TODO: Rename and change types and number of parameters
    public static signupfrag newInstance(String param1, String param2) {
        signupfrag fragment = new signupfrag();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signupfrag, container, false);
        username = v.findViewById(R.id.signupusername);

       /*  frname = v.findViewById(R.id.signuppfirstname1);
         lsname = v.findViewById(R.id.signupplastname1);
         passw1 = v.findViewById(R.id.signuppassword11);
         passw2 = v.findViewById(R.id.signuppassword21);
         email = v.findViewById(R.id.signuppmail1);
         signup =  v.findViewById(R.id.signupbtn);*/
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

                validsignup( un, up,upp,uf,ul,ue);
                doSignup(un, up,uf,ul,ue);

                Intent intent = new Intent(signupfrag.this.getContext(),MainActivity.class);
                startActivity(intent);

            }
        });
        return  v;
    }

    private boolean validsignup(String un , String up,String upp,String uf,String ul,String ue)
    {
        if(un==null || un.trim().length()==0 ){
            Toast.makeText(this.getContext(),"username is required",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(up==null || up.trim().length()==0 ){
            Toast.makeText(this.getContext(),"passwordisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(upp==null || upp.trim().length()==0 ){
            Toast.makeText(this.getContext(),"passwordisrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(uf==null || uf.trim().length()==0 ){
            Toast.makeText(this.getContext(),"first name isrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ul==null || ul.trim().length()==0 ){
            Toast.makeText(this.getContext(),"last name isrequired",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(ue==null || ue.trim().length()==0 ){
            Toast.makeText(this.getContext(),"email isrequired",Toast.LENGTH_SHORT).show();
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