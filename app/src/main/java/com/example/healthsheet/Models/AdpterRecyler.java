package com.example.healthsheet.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.R;

import java.util.ArrayList;

public class AdpterRecyler extends RecyclerView.Adapter<AdpterRecyler.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView FirstName ,LastName ;

        public ViewHolder(View itemView, AdpterRecyler adpterRecyler) {
            super(itemView);
            FirstName = (TextView)itemView.findViewById(R.id.FirstName);
            LastName = (TextView)itemView.findViewById(R.id.LastName);
        }
    }

    private final ArrayList<User> users;
    private Context mContext;

    public AdpterRecyler(Context mContext, ArrayList<User> users) {
        this.mContext = mContext ;
        this.users = users;
    }
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View mItemView = LayoutInflater.from(mContext).inflate(R.layout.activity_doctors_list, parent, false);

        return new ViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      User u = users.get(position);
      holder.FirstName.setText(u.firstName);
      holder .LastName.setText(u.lastName);
    }

    @Override
    public int getItemCount() {
        return users .size();
    }



}
