package com.example.myapplication.filieres;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.filieres.entity.Filiere;

import java.util.List;
import java.util.zip.Inflater;

public class FiliereAdapter extends RecyclerView.Adapter<FiliereHolder> {

    private Context context;
    private LayoutInflater inflater;
    private int layout;
    private List<Filiere> filieres;

    public FiliereAdapter(Context context, int layout, List<Filiere> filieres) {
        this.context = context;
        this.layout = layout;
        this.filieres = filieres;
        this.inflater=LayoutInflater.from(this.context);
    }


    @NonNull
    @Override
    public FiliereHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView=this.inflater.inflate(layout,parent,false);
        FiliereHolder holder=new FiliereHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FiliereHolder holder, int position) {
        Filiere filiere=filieres.get(position);
        holder.populate(filiere);
        holder.itemView.setOnClickListener(v -> {

            Intent intent=new Intent(context, FiliereUpdateActivity.class);
            intent.putExtra("filiere",filiere);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return filieres.size();
    }
}
