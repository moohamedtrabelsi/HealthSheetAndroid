package com.example.healthsheet.Patient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.healthsheet.About;
import com.example.healthsheet.R;

public class Menu extends AppCompatActivity {

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu_patient,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.Account){
            Intent intent = new Intent(Menu.this, Account1.class);
            startActivity(intent);
            return true ;

        }
        else
        if(id == R.id.About){
            Intent intent = new Intent(Menu.this, About.class);
            startActivity(intent);
            return true ;

        }
        else
        if(id == R.id.Doctorslist){
            Intent intent = new Intent(Menu.this, doctorsList.class);
            startActivity(intent);
            return true ;

        }

        return super.onOptionsItemSelected(item);
    }
}