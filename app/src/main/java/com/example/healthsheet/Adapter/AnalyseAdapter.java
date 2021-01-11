package com.example.healthsheet.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.Models.Analyse;
import com.example.healthsheet.Models.Visite;
import com.example.healthsheet.Ordonnance;
import com.example.healthsheet.R;

import java.util.ArrayList;
import java.util.List;

public class AnalyseAdapter extends RecyclerView.Adapter<AnalyseAdapter.MyViewHolder> implements Filterable {

    List<Analyse> l ;
    List<Analyse> l2 ;
    Context context ;

    public AnalyseAdapter(List<Analyse> l, Context context) {
        this.l = l;
        this.context = context;
        l2=new ArrayList<>(l);
    }

    @NonNull
    @Override
    public AnalyseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context c = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.analyse, parent, false);
        AnalyseAdapter.MyViewHolder vh = new AnalyseAdapter.MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyseAdapter.MyViewHolder holder, int position) {
        holder.t.setText(l.get(position).getPatient());
        holder.t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ordonnance.curanalyse = l.get(position);
                Intent i = new Intent(context,Ordonnance.class);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    @Override
    public Filter getFilter() {
        return analysefiltr;

    }
    private Filter analysefiltr = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Analyse> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(l2);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Analyse item : l2) {
                    if (item.getPatient().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            l.clear();
            l.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.sender);
        }
    }
}
