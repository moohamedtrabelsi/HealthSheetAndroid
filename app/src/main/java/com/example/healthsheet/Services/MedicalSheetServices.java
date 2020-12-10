package com.example.healthsheet.Services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MedicalSheetServices {

    @POST("createordon")
    Call<JsonObject> createOrdennance(@Body JsonObject o);


}
