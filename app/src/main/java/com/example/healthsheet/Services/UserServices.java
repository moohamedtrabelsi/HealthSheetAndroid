package com.example.healthsheet.Services;

import com.example.healthsheet.Api.Responseobj;
import com.example.healthsheet.Models.User;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.POST;


public interface UserServices {
    @POST("auth/signin")
    Call<JsonObject> login(@Body JsonObject u) ;

    @POST("auth/signup")
    Call<JsonObject> signup(@Body JsonObject u);

}
