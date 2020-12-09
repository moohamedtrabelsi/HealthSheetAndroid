package com.example.healthsheet.Api;

import com.example.healthsheet.Services.ImagesServices;
import com.example.healthsheet.Services.UserServices;

import retrofit2.Retrofit;

public class ApiUtils {

    //public static final String BASE_URL ="http://192.168.1.102:3000/api/";

    public static UserServices getUserServices(){
        return ApiClient.getRetrofit().create(UserServices.class);
    }
    public static ImagesServices getImagesServices(){

        return ApiClient.getRetrofit().create(ImagesServices.class);
    }
}
