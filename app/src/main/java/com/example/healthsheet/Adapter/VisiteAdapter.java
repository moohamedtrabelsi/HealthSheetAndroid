package com.example.healthsheet.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsheet.Models.Ordonnance;
import com.example.healthsheet.R;

import java.util.List;

public class VisiteAdapter extends RecyclerView.Adapter<VisiteAdapter.MyViewHolder> {

    List<com.example.healthsheet.Models.Visite> l ;
    Context context ;

    public VisiteAdapter(List<com.example.healthsheet.Models.Visite> l)
    {

        this.l = l;
    }

    public void toastshow(String s,Context c)
    {
        Toast t = Toast.makeText(c,s,Toast.LENGTH_SHORT);
        t.show();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context c = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.visite, parent, false);
        MyViewHolder vh = new MyViewHolder(v);



        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {


       // holder.
        holder.t.setText(l.get(position).getA().getFilename());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastshow(l.get(position).getA().getFilename(),v.getContext());
            }
        });

    }

    @Override
    public int getItemCount() {
        return l.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

       // ImageView i ;
        TextView t ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //i = itemView.findViewById(R.id.imgrow);
            t = itemView.findViewById(R.id.Ordonnance);

        }
        public void updateholder(Ordonnance or )
        {

            //i.setImageResource(c.getImgs());
            t.setText(or.getMedicaments());
        }
    }
}
