package com.example.healthsheet.Services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MedicalSheetServices {

    @POST("createordon")
    Call<JsonObject> createOrdennance(@Body JsonObject o);

    @POST("createvisite")
    Call<JsonObject> createVisite(@Body JsonObject o);

    @POST("auth/send")
    Call<JsonObject> sendAnalyse(@Body JsonObject o);



}
