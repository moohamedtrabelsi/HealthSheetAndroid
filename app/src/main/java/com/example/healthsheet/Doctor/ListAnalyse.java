package com.example.healthsheet.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.example.healthsheet.Adapter.AnalyseAdapter;
import com.example.healthsheet.Api.ApiUtils;
import com.example.healthsheet.Models.AdpterRecyler;
import com.example.healthsheet.Models.Analyse;
import com.example.healthsheet.Models.User;
import com.example.healthsheet.R;
import com.example.healthsheet.Services.ImagesServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListAnalyse extends AppCompatActivity {
        RecyclerView rc ;

    private AnalyseAdapter analyseAdapter;
    public static ArrayList<Analyse> analyses= new ArrayList<>();
    private Button ab ;
    private Gson gson;
    private GsonBuilder gsonBuilder;
    ImagesServices us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_analyse);
        rc = findViewById(R.id.rcanalyses);
        us= ApiUtils.getImagesServices();

        analyseAdapter = new AnalyseAdapter( ListAnalyse.analyses,this);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(analyseAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.hama, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                analyseAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}