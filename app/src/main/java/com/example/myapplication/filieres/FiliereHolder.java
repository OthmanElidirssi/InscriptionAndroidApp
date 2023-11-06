package com.example.myapplication.filieres;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.filieres.entity.Filiere;

import org.w3c.dom.Text;

public class FiliereHolder extends RecyclerView.ViewHolder {

    private TextView id;
    private TextView code;
    private TextView libelle;
    public FiliereHolder(@NonNull View itemView) {
        super(itemView);
        id=itemView.findViewById(R.id.filiere_id);
        code=itemView.findViewById(R.id.filiere_code);
        libelle=itemView.findViewById(R.id.filiere_libelle);
    }
    public void populate(Filiere filiere){
        id.setText(String.valueOf(filiere.getId()));
        code.setText(filiere.getCode());
        libelle.setText(filiere.getLibelle());
    }
}
