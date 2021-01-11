package com.example.healthsheet.Models;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.R;

import java.util.ArrayList;

public class AdpterRecyclerPatient extends RecyclerView.Adapter<AdpterRecyclerPatient.ViewHolder> {

    public AdpterRecyclerPatient(ArrayList<User> users) {
        this.users = users;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView FirstName ,LastName ;

        public ViewHolder(View itemView, AdpterRecyclerPatient adpterRecyler) {
            super(itemView);
            LastName = (TextView)itemView.findViewById(R.id.sender);
        }

        public void updateholder(User u)
        {
            LastName.setText(u.getLastName());
        }

    }

    private final ArrayList<User> users;
    private Context mContext;

    public AdpterRecyclerPatient(Context mContext, ArrayList<User> users) {
        this.mContext = mContext ;
        this.users = users;
    }

    @NonNull
    @Override
    public AdpterRecyclerPatient.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdpterRecyclerPatient.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
