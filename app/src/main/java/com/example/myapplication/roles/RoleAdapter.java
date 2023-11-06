package com.example.myapplication.roles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.roles.entity.Role;

import java.util.List;

public class RoleAdapter extends RecyclerView.Adapter<RoleHolder> {


    private Context context;
    private List<Role> roles;
    private LayoutInflater inflater;
    private int layout;

    public RoleAdapter(Context context, List<Role> roles,int layout) {
        this.context = context;
        this.roles = roles;
        this.layout=layout;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public RoleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=this.inflater.inflate(this.layout,parent,false);
        RoleHolder holder=new RoleHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull RoleHolder holder, int position) {
        Role role=roles.get(position);
        holder.populateRole(role);
        holder.itemView.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return roles.size();
    }
}
