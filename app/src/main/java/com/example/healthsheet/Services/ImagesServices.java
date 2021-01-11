package com.example.healthsheet.Services;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ImagesServices {


    @Multipart
    @POST("auth/uploadfile")
    Call<JsonObject> upload(
            @Part MultipartBody.Part file
    );

    @POST("auth/send")
    Call<JsonObject> sendAnalyse(@Body JsonObject o);

    @POST("auth/get")
    Call<JsonObject> getanalyse(@Body JsonObject o);

}
