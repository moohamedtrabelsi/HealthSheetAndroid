package com.example.healthsheet.Models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.Patient.AboutDoc;
import com.example.healthsheet.Patient.Menu;
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

public class AdpterRecyler extends RecyclerView.Adapter<AdpterRecyler.ViewHolder> {

    private Gson gson;
    private GsonBuilder gsonBuilder;
    UserServices us;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView FirstName ,LastName ;


        public ViewHolder(View itemView, AdpterRecyler adpterRecyler) {
            super(itemView);
            FirstName = (TextView)itemView.findViewById(R.id.FirstName);
            LastName = (TextView)itemView.findViewById(R.id.LastName);
        }

        public void updateholder(User u)
        {
            FirstName.setText(u.getFirstName());
            LastName.setText(u.getLastName());
        }

    }

    private final ArrayList<User> users;
    private Context mContext;

    public AdpterRecyler(Context mContext, ArrayList<User> users) {
        this.mContext = mContext ;
        this.users = users;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.doctor, parent, false);

        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      User u = users.get(position);

      holder.FirstName.setText(u.firstname);
      holder .LastName.setText(u.lastname);
      holder.FirstName.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
         //   AboutDoc.aboutuser = thatUser(holder.FirstName.getText().toString());
          }
      });

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

    @Override
    public int getItemCount() {
        return users .size();
    }

}
