package com.example.healthsheet;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
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
import com.example.healthsheet.Services.ImagesServices;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadImage extends AppCompatActivity {

    public static final String KEY_User_Document1 = "doc1";
    ImageView IDProf,ous;
    Button Upload_Btn ;
    Bitmap b;

    private String Document_img1="" ;
    String u = "http://192.168.1.93:3000/api/image/1610111086511-HealthSheet-file.jpg";
    protected Bitmap doInBackground(String urls) {
        String urldisplay = urls;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
           // Log.e("Error", e.getMessage());
          //  e.printStackTrace();
        }
        return mIcon11;
    }

    public void loadi()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {try {
                Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(u).getContent());
                b=bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            }
        });

        thread.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        ous = (ImageView)findViewById(R.id.Oussema);
        IDProf=(ImageView)findViewById(R.id.IdProf);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(u).getContent());
            ous.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
       /*Bitmap b = doInBackground(u);
       ous.setImageBitmap(b);*/

       /* try {
            InputStream is = (InputStream) new URL(u).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            ous.setImageDrawable(d);
        } catch (Exception e) {

        }*/
        //IDProf.setImageURI(u);
        Upload_Btn=(Button)findViewById(R.id.UploadBtn);

        Upload_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //uploadFile(IDProf.getIma);
                loadi();
            }
        });

        IDProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testpick();

            }
        });

       // Upload_Btn.setOnClickListener(this);
    }

    private void selectImage() {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(UploadImage.this);

       // android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(Uplode_Reg_Photo.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo"))
                {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, 1);
                }
                else if (options[item].equals("Choose from Gallery"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                }
                else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
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
        // create upload service client
        ImagesServices service =
                ApiUtils.getImagesServices();

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri

      //  File file = new File(fileUri.getPath().substring(13));
        String selectedFilePath = FilePath.getPath(UploadImage.this, fileUri);
        final File f = new File(selectedFilePath);

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
       Call<ResponseBody> call = service.upload( body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
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