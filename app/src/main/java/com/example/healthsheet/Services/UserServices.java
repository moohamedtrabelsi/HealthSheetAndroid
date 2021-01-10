package com.example.healthsheet.Services;

import com.example.healthsheet.Api.Responseobj;
import com.example.healthsheet.Models.User;
import com.google.gson.JsonObject;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserServices {

    @POST("auth/signin")
    Call<JsonObject> login(@Body JsonObject u) ;

    @POST("auth/signup")
    Call<JsonObject> signup(@Body JsonObject u);

    @POST("auth/updateuser")
    Call<JsonObject> update(@Body JsonObject u);


    @POST("auth/getuser")
    Call<JsonObject> getUser(@Body JsonObject u);

    @POST("auth/email")
    Call<JsonObject> getEmail(@Body JsonObject u);


    @PUT("auth/updatePass")
    Call<JsonObject> updatePass(@Body JsonObject u);

}
