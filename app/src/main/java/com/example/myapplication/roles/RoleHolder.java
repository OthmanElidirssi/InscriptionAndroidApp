package com.example.myapplication.roles;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.roles.entity.Role;

import org.w3c.dom.Text;

public class RoleHolder extends RecyclerView.ViewHolder {

    public  TextView id;
    public TextView name;

    public RoleHolder(@NonNull View itemView) {
        super(itemView);
        this.id=itemView.findViewById(R.id.role_id);
        this.name=itemView.findViewById(R.id.role_name);
    }
    public void populateRole( Role role ){
        id.setText(String.valueOf(role.getId()));
        name.setText(role.getName());
    }
}
