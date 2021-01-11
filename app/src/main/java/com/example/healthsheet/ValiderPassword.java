package com.example.healthsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ValiderPassword extends AppCompatActivity {

    public static String email;
    EditText code ;
    Button btnOk ;
    EmailForgot emailForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_password);
        code =findViewById(R.id.code);
         btnOk = findViewById(R.id.btnOk);



        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod = code.getText().toString();

             if (verifier(cod)==true){
                 Intent intent = new Intent(ValiderPassword.this, UpdatePassword.class);

                 startActivity(intent);
                 toastshow("code ");
             }else{
                 toastshow("invalide code ");
             }



            }
        });

    }

    public void toastshow(String s)
    {
        Toast t = Toast.makeText(this,s,Toast.LENGTH_SHORT);
        t.show();
    }

    public boolean verifier(String cod){

         boolean v   = false ;
        if ( cod .equals(emailForgot.codee)){
            v=true;
        }else {
            v = false ;
        }

        return v;
    }
}

