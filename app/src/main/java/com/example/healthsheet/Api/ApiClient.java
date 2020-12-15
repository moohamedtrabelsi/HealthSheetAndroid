package com.example.healthsheet.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit getRetrofit(){

        HttpLoggingInterceptor httploginter = new HttpLoggingInterceptor();
        httploginter.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient clienthttp = new OkHttpClient.Builder().addInterceptor(httploginter).build();

        Retrofit retro = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://172.16.123.25:3000/api/")
                .client(clienthttp)
                .build();
        return retro ;
    }

}
