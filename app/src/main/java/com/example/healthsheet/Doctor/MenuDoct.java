package com.example.healthsheet.Doctor;

import android.content.Intent;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsheet.About;
import com.example.healthsheet.Patient.Account1;
import com.example.healthsheet.Patient.Menu;
import com.example.healthsheet.Patient.doctorsList;
import com.example.healthsheet.R;

public class MenuDoct extends AppCompatActivity {

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menudoct,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id =item.getItemId();
        if(id == R.id.Account){
            Intent intent = new Intent(MenuDoct.this, AccountDoc.class);
            startActivity(intent);
            return true ;

        }


        else
        if(id == R.id.Doctorslist){
            Intent intent = new Intent(MenuDoct.this, PatientListe.class);
            startActivity(intent);
            return true ;

        }
        return super.onOptionsItemSelected(item);
    }
}
