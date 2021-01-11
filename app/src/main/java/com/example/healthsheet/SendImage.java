package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.healthsheet.Adapter.FilePath;
import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.Analyse;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.Patient.AboutDoc;
import com.example.healthsheet.Services.ImagesServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendImage extends AppCompatActivity {


    public static final String KEY_User_Document1 = "doc1";
    ImageView IDProf,ous;
    Button Upload_Btn ;
    Bitmap b;
    private Gson gson;
    private GsonBuilder gsonBuilder;

    private String Document_img1="" ;
    String u = "http://192.168.1.93:3000/api/image/1610111086511-HealthSheet-file.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_image);


        ous = (ImageView)findViewById(R.id.sOussema);
        IDProf=(ImageView)findViewById(R.id.sIdProf);


        //IDProf.setImageURI(u);
        Upload_Btn=(Button)findViewById(R.id.sUploadBtn);


        IDProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testpick();

            }
        });

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();

                String filePath = getPath(selectedImage);
                String file_extn = filePath.substring(filePath.lastIndexOf(".") + 1);
                //  image_name_tv.setText(filePath);

                if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                    IDProf.setImageURI(selectedImage);
                } else {
                    //NOT IN REQUIRED FORMAT
                }
            }
        if (requestCode == 1623)
            if(resultCode == Activity.RESULT_OK)
                switch (requestCode){
                    case 1623:
                        Uri selectedImage = data.getData();

                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                            IDProf.setImageBitmap(bitmap);
                            System.out.println(selectedImage.toString());
                            System.out.println(selectedImage.getPath().substring(13));
                            uploadFile(selectedImage);

                        } catch (IOException e) {
                            Log.i("TAG", "Some exception " + e);
                        }
                        break;
                }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.MediaColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        cursor.moveToFirst();
        String imagePath = cursor.getString(column_index);

        return cursor.getString(column_index);
    }


    private void uploadFile(Uri fileUri) {
        ImagesServices service =
                ApiUtils.getImagesServices();

        String selectedFilePath = FilePath.getPath(SendImage.this, fileUri);
        final File f = new File(selectedFilePath);
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();




        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        f
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", f.getName(), requestFile);

        // add another part within the multipart request
        /*String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);*/

        // finally, execute the request
        Call<JsonObject> call = service.upload( body);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call,
                                   Response<JsonObject> response) {
                Log.v("Upload", "success");

                Analyse a = gson.fromJson(response.body().toString(), Analyse.class);
                System.out.println("hhhhhhh"+a.getFilename());
                a.setDoctor(AboutDoc.aboutuser.getUsername());
                a.setPatient(User.usercur.getUsername());
                send(a);
                //System.out.println("fvhr"+response.body().toString());

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }



        });
    }

    public void send(Analyse a){
        ImagesServices service =
                ApiUtils.getImagesServices();
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        Gson g = new Gson();
        String j = g.toJson(a);
        JsonParser jp = new JsonParser();
        JsonObject jo = (JsonObject) jp.parse(j);
        Call<JsonObject> call = service.sendAnalyse(jo);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });




    }


    public void testpick()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");

        startActivityForResult(photoPickerIntent, 1623);
    }


}